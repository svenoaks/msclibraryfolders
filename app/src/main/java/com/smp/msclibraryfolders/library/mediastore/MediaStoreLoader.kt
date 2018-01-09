package com.smp.musicspeed.library.mediastore

import android.content.Context
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
import android.provider.MediaStore.Audio.AudioColumns
import com.smp.msclibraryfolders.utils.SortOrder
import com.smp.msclibraryfolders.library.song.Song
import com.smp.musicspeed.library.model.Album


/**
 * Created by steve on 10/16/17.
 */
private val SONG_PROJECTION = arrayOf(MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.YEAR,
        MediaStore.Audio.Media.DATE_ADDED,
        MediaStore.Audio.Media.ALBUM_ID,
        MediaStore.Audio.Media.ARTIST_ID,
        MediaStore.Audio.Media.TRACK)

private val ARTIST_PROJECTION = arrayOf(MediaStore.Audio.Artists._ID,
        MediaStore.Audio.Artists.ARTIST)

private val ALBUM_PROJECTION = arrayOf(MediaStore.Audio.Albums._ID,
        MediaStore.Audio.Albums.ALBUM,
        MediaStore.Audio.Media.ARTIST_ID,
        MediaStore.Audio.Albums.ARTIST,
        MediaStore.Audio.Albums.LAST_YEAR,
        MediaStore.Audio.Albums.ALBUM_ART)

private val PLAYLIST_PROJECTION = arrayOf(MediaStore.Audio.Playlists._ID,
        MediaStore.Audio.Playlists.NAME)

/*fun getAllMediaItems(context: Context, id: Id) : List<MediaStoreItem> {
    when (id) {
        Id.SONGS -> getAllSongs(context)
    }
}*/


fun getAllSongs(context: Context): List<Song> {
    var musicSelection = MediaStore.Audio.Media.IS_MUSIC + " != 0" + " AND " + AudioColumns.TITLE + " != ''"

    val cur = context.contentResolver.query(
            EXTERNAL_CONTENT_URI, SONG_PROJECTION, musicSelection, null, SortOrder.SongSortOrder.SONG_A_Z) ?: return emptyList()

    val songs = Song.buildSongList(cur, context.resources)
    cur.close()

    return songs
}

fun getAllAlbums(context: Context): List<Album> {
    val cur = context.contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            ALBUM_PROJECTION, null, null, SortOrder.AlbumSortOrder.ALBUM_A_Z) ?: return emptyList<Album>()

    val albums = Album.buildAlbumList(cur, context.resources)

    cur.close()

    return albums
}

