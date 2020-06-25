package com.formationandroid.projetfinalm1

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ItemTouchHelperCallback // Constructeur.
    (private val adapter: MemoAdapter) :
    ItemTouchHelper.Callback() {
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlagsUpDown = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val dragFlagsLeftRight = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(
            dragFlagsUpDown,
            dragFlagsLeftRight
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }

}