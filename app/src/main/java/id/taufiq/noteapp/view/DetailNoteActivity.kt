package id.taufiq.noteapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import id.taufiq.noteapp.databinding.ActivityDetailNoteBinding
import id.taufiq.noteapp.db.note.Notes
import id.taufiq.noteapp.viewmodel.NotesViewModel

class DetailNoteActivity : AppCompatActivity() {


    private val binding: ActivityDetailNoteBinding by viewBinding()
    private val noteViewModel by viewModels<NotesViewModel>()


    private var noteId = -1
    private var foreingId = -2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteId = intent.getIntExtra("NOTES_ID", -1)
        foreingId = intent.getIntExtra("NOTE_FOREIGN", -2)
        noteViewModel.setId(noteId)
        noteViewModel.getNoteById.observe(this, Observer { notes ->
            notes?.run {
                binding.tvJudulDetailId.text = this.title
                binding.tvIsiDetailId.text = this.body
            }

        })


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu.run {
            this?.add(0, MENU_ID_EDIT_NOTE, 0, "Edit Note")
            this?.add(0, MENU_ID_HAPUS_NOTE, 0, "hapus Note")
        }

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ID_EDIT_NOTE -> {
                Intent(this, EditNoteActivity::class.java).run {
                    putExtra("FOREIGN_ID",foreingId)
                    putExtra("EDIT_NOTE_ID", noteId)
                    startActivity(this)
                }
            }

            MENU_ID_HAPUS_NOTE -> {
                val noteToDelete = Notes(noteId, "", "", 0)
                noteViewModel.deleteNote(noteToDelete)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MENU_ID_EDIT_NOTE = 1
        private const val MENU_ID_HAPUS_NOTE = 2
    }
}