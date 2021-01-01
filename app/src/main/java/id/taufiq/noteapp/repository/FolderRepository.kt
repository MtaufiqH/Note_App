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

    suspend fun deleteFolder(folder: Folders){
        folderDao.delete(folder)
    }

    suspend fun updateFolder(folder: Folders){
        folderDao.update(folder)
    }


}