package com.smp.musicspeed.library.model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smp.msclibraryfolders.R
import com.smp.musicspeed.library.BaseLibraryAdapter
import com.smp.musicspeed.library.mediastore.getSectionName


/**
 * Created by steve on 10/18/17.
 */
class AlbumAdapter internal constructor(context: Context) : BaseLibraryAdapter<AlbumAdapter.ViewHolder, Album>(context) {

    override fun getSectionName(position: Int): String =
            getSectionName(dataSet.get(position).albumName)

    override fun getItemCount() = dataSet.size

    override fun getItemId(position: Int) = dataSet[position].albumId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_library_songs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = dataSet[position]

        holder.title?.text = album.albumName
        holder.text?.text = album.artistName

        /*Glide
                .with(context)
                .load(AudioCover(context, album.location))
                .placeholder(R.drawable.defaultcover)
                .error(R.drawable.defaultcover)
                .into(holder.image)*/

    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var image: ImageView? = null
        var title: TextView? = null
        var text: TextView? = null

        init {
           // image = v.findViewById(R.id.image) as ImageView
           // title = v.findViewById(R.id.title) as TextView
           // text = v.findViewById(R.id.text) as TextView
        }
    }
}