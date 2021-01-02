package id.taufiq.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.taufiq.noteapp.databinding.NoteRowItemBinding
import id.taufiq.noteapp.db.note.Notes

/**
 * Created By Taufiq on 1/2/2021.
 *
 */

class NoteAdapter(private val onItemClick: (Notes) -> Unit) :
    ListAdapter<Notes, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    class NoteViewHolder constructor(private val binding: NoteRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes, itemClick: (Notes) -> Unit) {
            binding.tvNoteTitle.text = item.title
            binding.tvIsiNote.text = item.body
            binding.rootIdNote.setOnClickListener {
                itemClick(item)
            }

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NoteRowItemBinding.inflate(layoutInflater, parent, false)
        return NoteViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean =
            oldItem == newItem

    }


}