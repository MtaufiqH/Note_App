package id.taufiq.noteapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import id.taufiq.noteapp.R
import id.taufiq.noteapp.databinding.ActivityDetailFolderBinding
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.viewmodel.MainActivityViewModel

class DetailFolderActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityDetailFolderBinding>()
    private val MENU_ID_CREATE_NOTES = 0
    private val MENU_ID_EDIT = 1
    private val MENU_ID_HAPUS = 2

    private val viewModel by viewModels<MainActivityViewModel>()

    private var folderId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_folder)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // id of intent
        folderId = intent.getIntExtra("ID", folderId)

        // get data by id from database and then set title to action bar
        viewModel.setId(folderId)
        viewModel.getDataId.observe(this, Observer {
            title = it.title
        })



        viewModel.emptyDb.observe(this, Observer {
            showEmptyFolderView(it)
        })

    }

    private fun showEmptyFolderView(isEmpty: Boolean) {
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
            this.add(0, MENU_ID_CREATE_NOTES, 0, "Create Note")
            this.add(0, MENU_ID_EDIT, 0, "Edit Folder")
            this.add(0, MENU_ID_HAPUS, 0, "Hapus Folder")
        }
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ID_CREATE_NOTES -> {
                // CREATE NOTES
                Intent(this, CreateNotesActivity::class.java).run {
                    putExtra("ID_NOTE", folderId)
                    startActivity(this)
                }
            }

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