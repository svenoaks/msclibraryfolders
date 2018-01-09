package com.smp.musicspeed.library.song

import android.content.Context
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.smp.msclibraryfolders.R
import com.smp.msclibraryfolders.library.image.AudioCover
import com.smp.msclibraryfolders.library.song.Song
import com.smp.musicspeed.library.BaseLibraryAdapter
import com.smp.musicspeed.library.mediastore.getSectionName


/**
 * Created by steve on 10/7/17.
 */

class SongAdapter internal constructor(context: Context) : BaseLibraryAdapter<SongAdapter.ViewHolder, Song>(context) {

    override fun getSectionName(position: Int): String =
            getSectionName(dataSet[position].songName)

    override fun getItemCount() = dataSet.size

    override fun getItemId(position: Int) = dataSet[position].songId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_library_songs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = dataSet[position]

        holder.title?.text = song.songName
        holder.text?.text = song.artistName

        Glide
                .with(context)
                .load(AudioCover(context, song.location))
                .placeholder(R.drawable.defaultcover)
                .error(R.drawable.defaultcover)
                .into(holder.image)

    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var image: ImageView? = null
        var title: TextView? = null
        var text: TextView? = null

        init {
            image = v.findViewById(R.id.image) as ImageView?
            title = v.findViewById(R.id.title) as TextView?
            text = v.findViewById(R.id.text) as TextView?
        }
    }
}

