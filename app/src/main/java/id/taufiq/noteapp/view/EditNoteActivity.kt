package id.taufiq.noteapp.view

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import id.taufiq.noteapp.databinding.ActivityEditNoteBinding
import id.taufiq.noteapp.db.note.Notes
import id.taufiq.noteapp.viewmodel.NotesViewModel

class EditNoteActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityEditNoteBinding>()
    private val viewModelNotes by viewModels<NotesViewModel>()


    var noteId = -1
    var foreignKeyId = -2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteId = intent.getIntExtra("EDIT_NOTE_ID", -1)
        foreignKeyId = intent.getIntExtra("FOREIGN_ID", -2)


        viewModelNotes.setId(noteId)
        viewModelNotes.getNoteById.observe(this, Observer { notes ->
            notes?.let {
                binding.etInputJudulNoteEdit.setText(it.title)
                binding.etInputIsiNoteEdit.setText(it.body)
            }

            binding.btnEdit.setOnClickListener {
                val title = binding.etInputJudulNoteEdit.text.toString()
                val isi = binding.etInputIsiNoteEdit.text.toString()

                if (title.isEmpty() && isi.isEmpty()) {
                    binding.etInputJudulNoteEdit.error = "Title is empty"
                    binding.etInputIsiNoteEdit.error = "your note empty"
                } else {
                    viewModelNotes.updateNote(Notes(noteId, title, isi, foreignKeyId))
                    finish()
                }

            }

        })


    }
}