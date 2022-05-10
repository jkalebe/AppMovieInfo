package br.com.example.appmovieinfo.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.databinding.ActivityMainBinding
import br.com.example.appmovieinfo.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        binding.viewPager.adapter = MoviePagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position->
            tab.setText(
                if (position == 0)
                    R.string.tab_movies
                else
                    R.string.tab_favorites
            )
        }.attach()

    }

}