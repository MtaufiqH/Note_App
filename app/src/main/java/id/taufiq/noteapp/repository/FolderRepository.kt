package id.taufiq.noteapp.repository

import id.taufiq.noteapp.db.folder.FolderDao
import id.taufiq.noteapp.db.folder.Folders

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

class FolderRepository(private val folderDao: FolderDao) {


    val getAllFolders  = folderDao.getAllFolder()

    suspend fun insertFolder(folder: Folders){
        folderDao.insert(folder)
    }


}