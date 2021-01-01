package id.taufiq.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.taufiq.noteapp.databinding.FolderRowItemBinding
import id.taufiq.noteapp.db.folder.Folders


/**
 * Created By Taufiq on 1/1/2021.
 *
 */


class FolderAdapter(private val onItemClick: (Folders) -> Unit) : ListAdapter<Folders, FolderAdapter.FolderViewHolder>(FolderDiffCallback()) {

    class FolderViewHolder constructor(private val binding: FolderRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Folders, itemClick: (Folders)->Unit) {
            binding.tvFolderName.text = item.title
            binding.rootId.setOnClickListener {
                itemClick(item)
            }

        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FolderRowItemBinding.inflate(layoutInflater, parent, false)
        return FolderViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onItemClick)
    }

    class FolderDiffCallback : DiffUtil.ItemCallback<Folders>() {
        override fun areItemsTheSame(oldItem: Folders, newItem: Folders): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Folders, newItem: Folders): Boolean =
            oldItem == newItem

    }




}

