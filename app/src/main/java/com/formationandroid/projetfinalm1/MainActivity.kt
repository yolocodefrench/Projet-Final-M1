package com.formationandroid.projetfinalm1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.formationandroid.projetfinalm1.bdd.AppDatabase
import com.formationandroid.projetfinalm1.bdd.AppDatabaseHelper
import com.formationandroid.projetfinalm1.objects.MemoObject
import kotlinx.android.synthetic.main.include_main.*


class MainActivity : AppCompatActivity(), OnItemTouchListener {
    private var editTextMemo: EditText? = null
    private var frameLayoutConteneurDetail: FrameLayout? = null

    var recyclerView: RecyclerView? = null
    // Adapter :
    private var memosAdapter: MemoAdapter? = null
    // Gesture detector :
    private var gestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) { // init :
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listeMemos =
            AppDatabaseHelper.getDatabase(this).memosDAO()!!.getAll().toMutableList()
        // contenu d'exemple :
        // vues :
        editTextMemo = findViewById(R.id.saisie_memo)
        frameLayoutConteneurDetail = findViewById(R.id.conteneur_detail)
        recyclerView = findViewById(R.id.liste_memos)
        memosAdapter = MemoAdapter( listeMemos)
        recyclerView?.setAdapter(memosAdapter)
        // à ajouter pour de meilleures performances :
        recyclerView?.setHasFixedSize(true)

        // layout manager, décrivant comment les items sont disposés :
        val layoutManager = LinearLayoutManager(this)
        recyclerView?.setLayoutManager(layoutManager)

        val itemTouchHelper =
            ItemTouchHelper(ItemTouchHelperCallback(memosAdapter!!))
        itemTouchHelper.attachToRecyclerView(liste_memos)
        // listener :
        recyclerView?.addOnItemTouchListener(this)
        gestureDetector = GestureDetector(this,
            object : SimpleOnGestureListener() {
                override fun onSingleTapUp(event: MotionEvent): Boolean {
                    return true
                }
            })
    }

    override fun onInterceptTouchEvent(
        rv: RecyclerView,
        motionEvent: MotionEvent
    ): Boolean {
        if (gestureDetector!!.onTouchEvent(motionEvent)) { // récupération de l'item cliqué :
            val child =
                recyclerView!!.findChildViewUnder(motionEvent.x, motionEvent.y)
            if (child != null) { // position dans la liste d'objets métier (position à partir de zéro !) :
                val position = recyclerView!!.getChildAdapterPosition(child)
                // récupération du mémo à cette position :
                val memo: MemoObject? = memosAdapter!!.getItem(position)
                // affichage du détail :
                if (frameLayoutConteneurDetail != null) { // fragment :
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    if (memo != null) {
                        bundle.putString(DetailFragment.EXTRA_MEMO, memo.title)
                    }
                    fragment.arguments = bundle
                    // le conteneur de la partie détail est disponible, on est donc en mode "tablette" :
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.conteneur_detail, fragment).commit()
                } else { // le conteneur de la partie détail n'est pas disponible, on est donc en mode "smartphone" :
                    val intent = Intent(this, DetailActivity::class.java)
                    if (memo != null) {
                        intent.putExtra(DetailFragment.EXTRA_MEMO, memo.title)
                    }
                    startActivity(intent)
                }
                return true
            }
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    fun onClickBoutonValider(view: View?) {
        val memo = MemoObject(editTextMemo!!.text.toString())
        // ajout du mémo :
        Log.d("essai", memo.title)
        memosAdapter!!.ajouterMemo(memo)

        AppDatabaseHelper.getDatabase(this).memosDAO()!!.insertAll(memo)
        // animation de repositionnement de la liste (sinon on ne voit pas l'item ajouté) :
        recyclerView!!.smoothScrollToPosition(0)
        // on efface le contenu de la zone de saisie :
        editTextMemo!!.setText("")
    }
}
