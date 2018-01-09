package com.smp.musicspeed.library

import android.content.Context
import android.support.v7.widget.RecyclerView

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView
import com.smp.musicspeed.library.mediastore.MediaStoreItem

import java.util.ArrayList

/**
 * Created by steve on 10/7/17.
 */

abstract class BaseLibraryAdapter<VT : RecyclerView.ViewHolder, MT : MediaStoreItem>
internal constructor(context: Context) : RecyclerView.Adapter<VT>(), FastScrollRecyclerView.SectionedAdapter {

    protected var dataSet: List<MT>

    protected var context: Context

    init {
        dataSet = ArrayList()
        this.context = context.applicationContext
    }

    fun swapDataSet(dataSet: List<MT>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    abstract override fun getSectionName(position: Int): String
}