package id.taufiq.noteapp.db.note

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(insertNote: Notes)

    @Update
    suspend fun update(updateNotes: Notes)

    @Delete
    suspend fun delete(deleteNotes: Notes)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE id= :id")
    fun getNoteById(id: Int): LiveData<Notes>
}