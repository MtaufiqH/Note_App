package id.taufiq.noteapp.view

import android.os.Bundle
import android.text.TextUtils
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.noteapp.databinding.ActivityCreateNotesBinding
import id.taufiq.noteapp.db.note.Notes
import id.taufiq.noteapp.viewmodel.NotesViewModel

class CreateNotesActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityCreateNotesBinding>()
    private val viewmodels by viewModels<NotesViewModel>()

    var folderId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get folder id
        folderId = intent.getIntExtra("ID_NOTE", -1)

        binding.btnCreate.setOnClickListener {
            createNewNotes()
        }
    }


    private fun createNewNotes() {
        val titleNote = binding.etInputJudulNote.text.toString()
        val isiNote = binding.etInputIsiNote.text.toString()

        val validation = verifyNotEmpty(titleNote, isiNote)
        if (validation) {
            // create new folder
            val createFolder = Notes(0, titleNote, isiNote, folderId)
            viewmodels.createNote(createFolder)
            Toast.makeText(this, "folder was created!", Toast.LENGTH_SHORT).show()
            finish()

        }
    }

    private fun verifyNotEmpty(judul: String, isi: String): Boolean {
        return if (TextUtils.isEmpty(judul) && TextUtils.isEmpty(isi)) {
            binding.etInputJudulNote.error = "Insert judul dulu"
            binding.etInputIsiNote.error = "Isi Note dulu"
            false
        } else {
            judul.isNotEmpty()
            isi.isNotEmpty()
        }
    }
}