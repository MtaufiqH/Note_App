package id.taufiq.noteapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import id.taufiq.noteapp.R
import id.taufiq.noteapp.adapter.FolderAdapter
import id.taufiq.noteapp.adapter.SwipeToDelete
import id.taufiq.noteapp.databinding.ActivityMainBinding
import id.taufiq.noteapp.db.folder.Folders
import id.taufiq.noteapp.util.ItemDecorations
import id.taufiq.noteapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    val folderAdapter: FolderAdapter by lazy {
        FolderAdapter {
            Toast.makeText(this, "Clicked ${it.title}", Toast.LENGTH_SHORT).show()

        }
    }

    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.idCreateFolder.setOnClickListener {
            Intent(this, CreateFolderActivity::class.java).run {
                startActivity(this)
            }

        }


        val padding = resources.getDimensionPixelSize(R.dimen.padding) / 4


        // get All data folders
        viewModel.getAllFolder.observe(this, Observer { folderList ->
            folderList.let {
                Log.i("MainActivity", "data $folderList")
                binding.rvListFolder.run {
                    clipToPadding = false
                    this.adapter = folderAdapter
                    addItemDecoration(ItemDecorations(padding))
                    folderAdapter.submitList(it)
                    swipeToDelete(this)

                }

                viewModel.checkIfEmptyFolder(folderList)

            }
        })


        viewModel.emptyDb.observe(this, Observer {
            showEmptyFolderView(it)
        })


    }


    private fun swipeToDelete(recyclerView: RecyclerView) {
        val simpleCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = folderAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteFolder(itemToDelete)
                // show snackbar
                restoreDeleteData(viewHolder.itemView,itemToDelete)
            }

        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvListFolder)
    }

    private fun restoreDeleteData(view: View,deleteItem: Folders){
        val snackBar = Snackbar.make(view,"Deleted ${deleteItem.title}", Snackbar.LENGTH_LONG)
        snackBar.setAction("Undo"){
            viewModel.insertFolder(deleteItem)
        }.show()
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