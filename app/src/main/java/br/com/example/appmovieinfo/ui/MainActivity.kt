package br.com.example.appmovieinfo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = MoviePagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){tab, position->
            tab.setText(
                if (position == 0)
                    R.string.tab_movies
                else
                    R.string.tab_favorites
            )
        }.attach()

    }

}