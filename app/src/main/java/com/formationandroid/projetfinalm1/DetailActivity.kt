package com.formationandroid.projetfinalm1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // init :
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // fragment :
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString(
            DetailFragment.EXTRA_MEMO,
            intent.getStringExtra(DetailFragment.EXTRA_MEMO)
        )
        fragment.arguments = bundle
        // ajout / remplacement fragment :
        supportFragmentManager.beginTransaction().replace(R.id.layout_detail, fragment)
            .commit()
    }
}
