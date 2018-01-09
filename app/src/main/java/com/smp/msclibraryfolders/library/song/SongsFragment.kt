package com.smp.musicspeed.library.song

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import com.smp.msclibraryfolders.R
import com.smp.msclibraryfolders.library.BaseLibraryPageFragment
import com.smp.msclibraryfolders.library.song.Song


import com.smp.musicspeed.library.mediastore.MediaStore

/**
 * Created by steve on 10/5/17.
 */

class SongsFragment : BaseLibraryPageFragment<Song, SongAdapter.ViewHolder, SongAdapter>() {

    override fun getMediaStoreId(): MediaStore.Id {
        return MediaStore.Id.SONGS
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun setupRecyclerView() {
        super.setupRecyclerView()
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                layoutManager.orientation)

        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onViewCreated(view: View?, b: Bundle?) {
        super.onViewCreated(view, b)

    }

    override fun createAdapter(): SongAdapter {
        return SongAdapter(activity)
    }

    override fun getEmptyMessage(): Int {
        return R.string.label_no_songs
    }
}
