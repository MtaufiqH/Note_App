package id.taufiq.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.taufiq.noteapp.db.ApplicationDatabase
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.repository.FolderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created By Taufiq on 1/1/2021.
 *
 */

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    // if empty event
    private val _emptyDb = MutableLiveData(true)
    val emptyDb: LiveData<Boolean>
        get() = _emptyDb


    private val noteDao = ApplicationDatabase.getInstance(application).folderDao()
    private val folderRepo: FolderRepository
    val getAllFolder: LiveData<List<Folders>>

    init {
        folderRepo = FolderRepository(noteDao)
        getAllFolder = folderRepo.getAllFolders
    }

    fun checkIfEmptyFolder(folder: List<Folders>) {
        _emptyDb.value = folder.isEmpty()
    }

    fun insertFolder(folder: Folders) {
        viewModelScope.launch(Dispatchers.IO) {
            folderRepo.insertFolder(folder)
        }
    }

    fun deleteFolder(folder: Folders){
        viewModelScope.launch(Dispatchers.IO) {
            folderRepo.deleteFolder(folder)
        }
    }

    fun updateFolder(folder: Folders){
        viewModelScope.launch(Dispatchers.IO) {
            folderRepo.updateFolder(folder)
        }
    }


}