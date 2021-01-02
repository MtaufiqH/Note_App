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


    private val _emptyNotes = MutableLiveData(true)
    val emptyNotes: LiveData<Boolean>
        get() = _emptyNotes


    private val noteDao = ApplicationDatabase.getInstance(application).noteDao()
    private val noteRepository: NoteRepository
    lateinit var  allNotes: LiveData<List<Notes>>
    lateinit var getNoteById: LiveData<Notes>

    init {
        noteRepository = NoteRepository(noteDao)
    }


    fun checkIfEmpty(notes: List<Notes>) {
        _emptyNotes.value = notes.isEmpty()

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

    fun getAllItemByFolderId(folderId: Int){
        val allItem = noteRepository.getAllById(folderId)
        allNotes = allItem

    }


}