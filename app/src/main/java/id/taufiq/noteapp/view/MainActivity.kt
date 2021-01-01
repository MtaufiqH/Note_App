package id.taufiq.noteapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import id.taufiq.noteapp.R
import id.taufiq.noteapp.adapter.FolderAdapter
import id.taufiq.noteapp.databinding.ActivityMainBinding
import id.taufiq.noteapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewmodel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.idCreateFolder.setOnClickListener {
            Intent(this, CreateFolderActivity::class.java).run {
                startActivity(this)
            }

        }

        // get All data folders
        viewmodel.getAllFolder.observe(this, Observer { folderList ->
            folderList.let {
                binding.rvListFolder.run {
                    val adapter = FolderAdapter()
                    adapter.submitList(folderList)

                }

                viewmodel.checkIfEmptyFolder(folderList)

            }
        })


        viewmodel.emptyDb.observe(this, Observer {
            showEmptyFolderView(it)
        })


    }

    private fun showEmptyFolderView(emptyDb: Boolean) {
        if (emptyDb) {
            binding.ivEmptyFolder.visibility = View.VISIBLE
            binding.tvEmptyFolder.visibility = View.VISIBLE
        } else {
            binding.ivEmptyFolder.visibility = View.INVISIBLE
            binding.tvEmptyFolder.visibility = View.INVISIBLE
        }
    }
}