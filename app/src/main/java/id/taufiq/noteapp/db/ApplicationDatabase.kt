package id.taufiq.noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.taufiq.noteapp.db.folder.FolderDao
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.db.note.NoteDao
import id.taufiq.noteapp.db.note.Notes

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

@Database(entities = [Folders::class, Notes::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun folderDao(): FolderDao
    abstract fun noteDao(): NoteDao


    companion object DatabaseHelper {

        /*The value of a volatile variable will never be cached,
        and all writes and reads will be done to and from the main memory. */
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null
        private const val DB_NAME = "note_db"

        @Synchronized
        fun getInstance(context: Context): ApplicationDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    DB_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }


}