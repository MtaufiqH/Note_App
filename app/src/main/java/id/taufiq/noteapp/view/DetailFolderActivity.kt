package id.taufiq.noteapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import id.taufiq.noteapp.R
import id.taufiq.noteapp.adapter.NoteAdapter
import id.taufiq.noteapp.adapter.SwipeToDelete
import id.taufiq.noteapp.databinding.ActivityDetailFolderBinding
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.db.note.Notes
import id.taufiq.noteapp.viewmodel.MainActivityViewModel
import id.taufiq.noteapp.viewmodel.NotesViewModel


private const val MENU_ID_EDIT = 0
private const val MENU_ID_HAPUS = 1
class DetailFolderActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityDetailFolderBinding>()
    private val viewModel by viewModels<MainActivityViewModel>()
    private val noteViewModel by viewModels<NotesViewModel>()




    // adapter Note
     val noteAdapter: NoteAdapter by lazy {
        NoteAdapter {
            Intent(this,DetailNoteActivity::class.java).run {
                startActivity(this)
            }
        }
    }


    private var folderId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_folder)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // id of intent
        folderId = intent.getIntExtra("ID", -1)

        // get data by id from database and then set title to action bar
        viewModel.setId(folderId)
        viewModel.getDataId.observe(this, Observer {
            title = it.title
        })

        // event empty notes visibility
        noteViewModel.emptyNotes.observe(this, Observer {
            showEmptyNotesView(it)
        })

        // get all note by id
        noteViewModel.getAllItemByFolderId(folderId)



        binding.fabAddNote.setOnClickListener {
            // CREATE NOTES
            Intent(this, CreateNotesActivity::class.java).run {
                putExtra("ID_NOTE", folderId)
                startActivity(this)
            }
        }


        // get All Note and show to Recyclerview
        noteViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let {
                Log.i("Detail Folder", "list of note: $notes")
                binding.rvDetailNotes.also {
                    it.adapter = noteAdapter
                    it.clipToPadding = false
                    it.addItemDecoration(
                        DividerItemDecoration(
                            this,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    it.swipeToDelete()
                }
                noteAdapter.submitList(notes)

            }

            noteViewModel.checkIfEmpty(notes)

        })

    }

    /*
    * swipe to delete note
    * */
    private fun RecyclerView.swipeToDelete() {
        val simpleCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = noteAdapter.currentList[viewHolder.adapterPosition]
                noteViewModel.deleteNote(itemToDelete)
                // show snackbar
                restoreDeleteData(viewHolder.itemView, itemToDelete)
            }

        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvDetailNotes)
    }

    // restore delete notes
    private fun restoreDeleteData(view: View, deleteItem: Notes) {
        val snackBar = Snackbar.make(view, "Deleted ${deleteItem.title}", Snackbar.LENGTH_LONG)
        snackBar.setAction("Undo") {
            noteViewModel.createNote(deleteItem)
        }.show()
    }

    private fun showEmptyNotesView(isEmpty: Boolean) {
        if (isEmpty) {
            binding.ivEmptyNotes.visibility = View.VISIBLE
            binding.tvEmptyNotes.visibility = View.VISIBLE
        } else {
            binding.ivEmptyNotes.visibility = View.INVISIBLE
            binding.tvEmptyNotes.visibility = View.INVISIBLE
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.run {
            this.add(0, MENU_ID_EDIT, 0, "Edit Folder")
            this.add(0, MENU_ID_HAPUS, 0, "Hapus Folder")
        }
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            MENU_ID_EDIT -> {
                // EDIT NOTES
                Intent(this, FolderEditActivity::class.java).run {
                    putExtra("FOLDER_ID", folderId)
                    startActivity(this)
                    finish()
                }


            }

            MENU_ID_HAPUS -> {
                // Delete folder
                viewModel.deleteFolder(Folders(folderId, ""))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}