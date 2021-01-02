package id.taufiq.noteapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.taufiq.noteapp.R
import id.taufiq.noteapp.databinding.ActivityDetailFolderBinding
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.viewmodel.MainActivityViewModel

class DetailFolderActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityDetailFolderBinding>()
    val MENU_ID_CREATE_NOTES = 0
    val MENU_ID_EDIT = 1
    val MENU_ID_HAPUS = 2

    private val viewModel by viewModels<MainActivityViewModel>()

    var folderId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_folder)

        folderId = intent.getIntExtra("ID", folderId)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.run {
            this.add(0, MENU_ID_CREATE_NOTES, 0, "Create Note")
            this.add(0, MENU_ID_EDIT, 0, "Edit Folder")
            this.add(0, MENU_ID_HAPUS, 0, "Hapus Folder")
        }
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ID_CREATE_NOTES -> {
                // CREATE NOTES
            }

            MENU_ID_EDIT -> {
                // EDIT NOTES
            Intent(this,FolderEditActivity::class.java).run {
                putExtra("FOLDER_ID", folderId)
                startActivity(this)
                finish()
            }


            }

            MENU_ID_HAPUS -> {
                // hapus folder
                viewModel.deleteFolder(Folders(folderId,""))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}