package com.smp.musicspeed.library.mediastore

import android.content.Context
import android.database.ContentObserver
import android.provider.MediaStore.Audio;
import com.smp.msclibraryfolders.utils.SingletonHolder
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MediaStore private constructor(val context: Context) {

    enum class Id { SONGS, ALBUMS }

    val NUMBER_IDS = Id.values().size

    companion object : SingletonHolder<MediaStore, Context>(::MediaStore)

    val itemsSubjects = Array(NUMBER_IDS, { BehaviorSubject.create<List<MediaStoreItem>>() })

    val itemsChangedSubjects = Array(NUMBER_IDS, {BehaviorSubject.createDefault<Boolean>(true)})

    var observerInterested = Array(NUMBER_IDS, {false})

    init {
        registerContentObservers()
        registerChangedObservers()
    }

    private fun registerContentObservers() {
        context.contentResolver.registerContentObserver(Audio.Media.EXTERNAL_CONTENT_URI, true,
                object : ContentObserver(null) {
                    override fun onChange(selfChange: Boolean) {
                        itemsChangedSubjects[Id.SONGS.ordinal].onNext(true)
                    }
                })
    }

    private fun registerChangedObservers() {

        val loadFunction: (Id) -> (Boolean) -> List<MediaStoreItem> = { id ->
            when (id) {
                Id.SONGS -> {
                    { getAllSongs(context) }
                }
                Id.ALBUMS -> {
                    { getAllAlbums(context) }
                }
                else -> {
                    { getAllSongs(context) }
                }
            }
        }
        for (id in Id.values())
        {
            val i = id.ordinal
            itemsChangedSubjects[i]
                    .filter { changed -> changed && observerInterested[i] }
                    .map(loadFunction(id))
                    .subscribeOn(Schedulers.single())
                    .observeOn(Schedulers.single())
                    .subscribe(itemsSubjects[i])
        }
    }

    fun resumeObserver(id: Id) {
        val i = id.ordinal
        observerInterested[i] = true
        if (itemsChangedSubjects[i].value)
            itemsChangedSubjects[i].onNext(true)
    }

    fun pauseObserver(id: Id) {
        val i = id.ordinal
        observerInterested[i] = false
    }

    fun <MT : MediaStoreItem>subscribeObserver(id: Id, observer: Observer<List<MT>>) {
        val i = id.ordinal
        val specificSubject = itemsSubjects[i] as BehaviorSubject<List<MT>>
        specificSubject
                .skipWhile { !observerInterested[i] && itemsChangedSubjects[i].value }
                .doAfterNext { if (observerInterested[i]) itemsChangedSubjects[i].onNext(false) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}