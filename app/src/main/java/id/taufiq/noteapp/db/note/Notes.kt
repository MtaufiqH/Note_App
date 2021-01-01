package id.taufiq.noteapp.db.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import id.taufiq.noteapp.db.folder.Folders

/**
 * Created By Taufiq on 1/1/2021.
 *
 */


// relasi
@Entity(
    tableName = "notes_table", foreignKeys = [
        ForeignKey(
            entity = Folders::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("folder_id"),
            onDelete = CASCADE
        )]
)
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var body: String,
    @ColumnInfo(name = "folder_id")
    var folderId: Int
)