package id.taufiq.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.taufiq.noteapp.db.ApplicationDatabase
import id.taufiq.noteapp.db.note.Notes
import id.taufiq.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created By Taufiq on 1/2/2021.
 *
 */

class NotesViewModel(application: Application) : AndroidViewModel(application) {


    private val _emptyDb = MutableLiveData(true)
    val emptyDb: LiveData<Boolean>
        get() = _emptyDb


    private val noteDao = ApplicationDatabase.getInstance(application).noteDao()
    private val noteRepository: NoteRepository
    val allNotes: LiveData<List<Notes>>
    lateinit var getNoteById: LiveData<Notes>

    init {
        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.allNotes
    }


    fun checkIfEmpty(notes: List<Notes>) {
        _emptyDb.value = notes.isEmpty()

    }

    fun createNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO){
            noteRepository.insert(note)
        }
    }


    fun updateNote(note: Notes){
        viewModelScope.launch {
            noteRepository.update(note)
        }
    }


    fun deleteNote(note: Notes){
        viewModelScope.launch {
            noteRepository.delete(note)
        }
    }


    fun setId(id: Int) {
        val item = noteRepository.editById(id)
        getNoteById = item
    }


}