package id.taufiq.noteapp.view

import android.os.Bundle
import android.text.TextUtils
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.noteapp.databinding.ActivityCreateFolderBinding
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_create_folder.*

class CreateFolderActivity : AppCompatActivity() {

    private val binding: ActivityCreateFolderBinding by viewBinding()
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnSave.setOnClickListener {
            createNewFolder()
        }
    }

    private fun createNewFolder() {
        val folderName = et_input_nama_folder.text.toString()
        val validation = verifyNotEmpty(folderName)
        if (validation) {
            // create new folder
            val createFolder = Folders(AUTO_GENERATE_ID, folderName)
            viewModel.insertFolder(createFolder)
            Toast.makeText(this, "folder was created!", Toast.LENGTH_SHORT).show()
            finish()

        }
    }

    private fun verifyNotEmpty(namaFolder: String): Boolean {
        return if (TextUtils.isEmpty(namaFolder)) {
            false
        } else {
            namaFolder.isNotEmpty()
        }
    }


    companion object {
        private const val AUTO_GENERATE_ID = 0
    }


}