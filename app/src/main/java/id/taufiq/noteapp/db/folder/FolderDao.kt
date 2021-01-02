package id.taufiq.noteapp.db.folder

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

@Dao
interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(insertFolder: Folders)

    @Update
    suspend fun update(updateFolder: Folders)

    @Delete
    suspend fun delete(deleteFolders: Folders)

    @Query("SELECT * FROM folders_table")
    fun getAllFolder(): LiveData<List<Folders>>

    @Query("SELECT * FROM folders_table WHERE id= :id")
    fun getById(id: Int): LiveData<Folders>


}