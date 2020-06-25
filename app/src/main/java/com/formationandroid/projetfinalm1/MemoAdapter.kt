package com.formationandroid.projetfinalm1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_transverse_memos.MemoViewHolder
import com.formationandroid.projetfinalm1.objects.MemoObject
import java.util.*

class MemoAdapter(listeMemos: MutableList<MemoObject>?) :
    RecyclerView.Adapter<MemoViewHolder>() {
    // Liste d'objets métier :
    private var listeMemos: MutableList<MemoObject>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val viewMemo: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return MemoViewHolder(viewMemo)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.textViewIntitule!!.text = this!!.listeMemos!![position].title
    }

    override fun getItemCount(): Int {
        return listeMemos!!.size
    }

    /**
     * Ajout d'un mémo à la liste.
     * @param memo Mémo
     */
    fun ajouterMemo(memo: MemoObject) {
        listeMemos?.add(0, memo)
        notifyItemInserted(0)
    }

    fun getItem(position: Int): MemoObject? {
        return listeMemos!![position]
    }

    fun onItemMove(positionDebut: Int, positionFin: Int): Boolean {
        Collections.swap(listeMemos, positionDebut, positionFin)
        notifyItemMoved(positionDebut, positionFin)
        return true
    }

    // Appelé une fois à la suppression.
    fun onItemDismiss(position: Int) {
        if (position > -1) {
            this.listeMemos?.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Constructeur.
     * @param listeMemos Liste de mémos
     */
    init {
        this.listeMemos = listeMemos
    }
}