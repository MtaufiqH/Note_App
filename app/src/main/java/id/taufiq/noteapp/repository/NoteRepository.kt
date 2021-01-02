package id.taufiq.noteapp.repository

import id.taufiq.noteapp.db.note.NoteDao
import id.taufiq.noteapp.db.note.Notes

/**
 * Created By Taufiq on 1/2/2021.
 *
 */

class NoteRepository(private val noteDao: NoteDao){

    //read note
    val allNotes = noteDao.getAllNotes()

    // insert Note
    suspend fun insert(notes: Notes){
        noteDao.insert(notes)
    }

    // delete
    suspend fun delete(notes: Notes){
        noteDao.delete(notes)
    }

    // update
    suspend fun update(notes: Notes){
        noteDao.update(notes)
    }

    fun editById(id: Int) = noteDao.getNoteById(id)

}