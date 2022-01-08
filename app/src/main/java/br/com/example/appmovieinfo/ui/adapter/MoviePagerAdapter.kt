package br.com.example.appmovieinfo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.example.appmovieinfo.ui.fragment.MovieFavoritesFragment
import br.com.example.appmovieinfo.ui.fragment.MovieListFragment

class MoviePagerAdapter(fa: FragmentActivity):FragmentStateAdapter(fa){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position==0) MovieListFragment()
        else
            MovieFavoritesFragment()
    }

}