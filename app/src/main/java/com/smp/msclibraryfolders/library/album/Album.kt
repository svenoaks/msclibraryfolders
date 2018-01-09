package com.smp.musicspeed.library.model

import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.provider.MediaStore
import com.smp.msclibraryfolders.R

import com.smp.musicspeed.library.mediastore.MediaStoreItem
import com.smp.musicspeed.library.mediastore.parseUnknown
import java.util.ArrayList

/**
 * Created by steve on 10/18/17.
 */
class Album() : MediaStoreItem {

    var albumId: Long = 0
        protected set
    var albumName: String = ""
        protected set
    var artistId: Long = 0
        protected set
    var artistName: String? = null
        protected set
    var year: Int = 0
        protected set
    var artUri: String? = null
        protected set


    constructor(context: Context, cur: Cursor) : this(context.resources, cur) {}

    constructor(res: Resources, cur: Cursor) : this() {
        albumId = cur.getLong(cur.getColumnIndex(MediaStore.Audio.Albums._ID))
        albumName = parseUnknown(
                cur.getString(cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM)),
                res.getString(R.string.unknown_album))
        artistName = parseUnknown(
                cur.getString(cur.getColumnIndex(MediaStore.Audio.Albums.ARTIST)),
                res.getString(R.string.unknown_artist))
        artistId = cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID))
        year = cur.getInt(cur.getColumnIndex(MediaStore.Audio.Albums.LAST_YEAR))
        artUri = cur.getString(cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
    }

    companion object {
        fun buildAlbumList(cur: Cursor, res: Resources): List<Album> {
            val albums = ArrayList<Album>(cur.count)

            val idIndex = cur.getColumnIndex(MediaStore.Audio.Albums._ID)
            val albumIndex = cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM)
            val artistIndex = cur.getColumnIndex(MediaStore.Audio.Albums.ARTIST)
            val artistIdIndex = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID)
            val yearIndex = cur.getColumnIndex(MediaStore.Audio.Albums.LAST_YEAR)
            val artIndex = cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)

            val unknownAlbum = res.getString(R.string.unknown_album)
            val unknownArtist = res.getString(R.string.unknown_artist)

            for (i in 0 until cur.count) {
                cur.moveToPosition(i)
                val next = Album()
                next.albumId = cur.getLong(idIndex)
                next.albumName = parseUnknown(cur.getString(albumIndex), unknownAlbum)
                next.artistName = parseUnknown(cur.getString(artistIndex), unknownArtist)
                next.artistId = cur.getLong(artistIdIndex)
                next.year = cur.getInt(yearIndex)
                next.artUri = cur.getString(artIndex)

                albums.add(next)
            }

            return albums
        }
    }



    override fun equals(other: Any?): Boolean {
        return this === other || other != null && other is Album && albumId == other.albumId
    }

    override fun toString(): String {
        return albumName
    }

}