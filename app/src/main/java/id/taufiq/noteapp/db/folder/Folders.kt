package id.taufiq.noteapp.db.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

@Entity(tableName = "folders_table")
data class Folders(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
)
