package id.taufiq.noteapp.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.taufiq.noteapp.db.folder.FolderDao
import id.taufiq.noteapp.viewmodel.NotesViewModel

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

class NotesViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class!")
    }

}