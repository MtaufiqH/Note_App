package id.taufiq.noteapp.view

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import id.taufiq.noteapp.R
import id.taufiq.noteapp.databinding.ActivityFolderEditBinding
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.viewmodel.MainActivityViewModel
import id.taufiq.noteapp.viewmodel.factory.MainActivityViewModelFactory

class FolderEditActivity : AppCompatActivity() {

    private val binding: ActivityFolderEditBinding by viewBinding()
    private val viewmodel by viewModels<MainActivityViewModel>()

    // id default
    private var folderIdEdit = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folder_edit)

        // get intent from detail folder activity
        folderIdEdit = intent.getIntExtra("FOLDER_ID", folderIdEdit)
        viewmodel.setId(folderIdEdit)

        viewmodel.getDataId.observe(this, Observer {
            binding.etInputNamaFolderEdit.setText(it.title)

        })

        binding.btnSaveEdit.setOnClickListener {
            val title = binding.etInputNamaFolderEdit.text.toString()
            if (title.isEmpty()) {
                binding.etInputNamaFolderEdit.error = "Input Judul Dulu"
            } else {
                viewmodel.updateFolder(Folders(folderIdEdit, title))
                finish()
            }
        }


    }


}