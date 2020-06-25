package com.example.tp_transverse_memos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.formationandroid.projetfinalm1.R

class MemoViewHolder(itemView: View) : ViewHolder(itemView) {
    /**
     * Getter textView intitulé.
     * @return TextView intitulé
     */
    // Vue intitulé mémo :
    var textViewIntitule: TextView? = null

    /**
     * Constructeur.
     * @param itemView Vue item
     */
    init {
        textViewIntitule = itemView.findViewById(R.id.memo_intitule)
    }
}