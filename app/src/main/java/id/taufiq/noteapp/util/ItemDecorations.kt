package id.taufiq.noteapp.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Created By Taufiq on 1/1/2021.
 *
 */
class ItemDecorations(private val itemOffset: Int) : ItemDecoration() {
    constructor(
        context: Context,
        @DimenRes itemOffsetId : Int
    ) : this(context.resources.getDimensionPixelSize(itemOffsetId))


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(itemOffset,itemOffset,itemOffset,itemOffset)
    }



}