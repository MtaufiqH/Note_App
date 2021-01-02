package id.taufiq.noteapp.view

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import id.taufiq.noteapp.databinding.ActivityCreateNotesBinding
import id.taufiq.noteapp.viewmodel.NotesViewModel

class CreateNotesActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityCreateNotesBinding>()
    private val viewmodels by viewModels<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val isiText = binding.etInputIsiNote.text.toString()
        val judulText = binding.etInputJudulNote.text.toString()


        var success = true
        if (isiText.isEmpty()) {
            binding.etInputJudulNote.error = "Input Judul dulu"
            success = false
        }

        if (judulText.isEmpty()) {
            binding.etInputIsiNote.error = "Isi Notemu"
            success = false
        }


        if (success) {
            viewmodels.allNotes.observe(this, Observer {

            })
        }

    }
}