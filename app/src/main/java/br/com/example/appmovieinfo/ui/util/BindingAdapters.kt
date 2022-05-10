package br.com.example.appmovieinfo.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.example.appmovieinfo.model.Actor
import br.com.example.appmovieinfo.ui.adapter.ActorListAdapter
import com.bumptech.glide.Glide


object BindingAdapters {
    // he we create our custom binding adapter like so

    @BindingAdapter("glide_poster")
    @JvmStatic
    fun loadPoster(viewImage: ImageView, url: String?) {
        //we will use glide library to load the image from a url

        Glide.with(viewImage.context).load(url).into(viewImage)

    }

    @BindingAdapter("glide_circular")
    @JvmStatic
    fun loadUserImg(view: ImageView, url: String?) {
        //glide can make any image in a circular format with ease
            Glide.with(view.context).load(url!!).circleCrop().into(view)

    }

    @BindingAdapter("glide_recyclerview")
    @JvmStatic
    fun loadRV(view: RecyclerView, data: List<Actor>) {
        //glide can make any image in a circular format with ease
        view.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        view.adapter = ActorListAdapter(data)

    }



}