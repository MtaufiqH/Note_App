package id.taufiq.noteapp.util

import id.taufiq.noteapp.db.data.DataFolders
import id.taufiq.noteapp.db.data.DataNotes
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.db.note.Notes

/**
 * Created By Taufiq on 1/1/2021.
 *
 */


object DataUtils {

    // folder converter
    fun convertFromFolderEntityList(folders: List<Folders>): List<DataFolders> {
        val list = mutableListOf<DataFolders>()
        for (folder in folders) {
            list.add(
                convertFromFolder(folder))
        }

        return list
    }


    fun convertFromFolder(folder: Folders): DataFolders {
        return DataFolders(
            folder.id,
            folder.title
        )
    }


    fun convertFromNotesEntityList(notes: List<Notes>): List<DataNotes> {
        val listOfNotes = mutableListOf<DataNotes>()
        for (notes in notes) {
            listOfNotes.add(
                convertFromNotesEntity(notes)
            )
        }

        return listOfNotes


    }

    fun convertFromNotesEntity(note: Notes): DataNotes {
        return DataNotes(
            note.id,
            note.title,
            note.body)

    }
}