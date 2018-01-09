package com.smp.msclibraryfolders.library;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import com.smp.msclibraryfolders.R;
import com.smp.musicspeed.library.BaseLibraryAdapter;
import com.smp.musicspeed.library.mediastore.MediaStore;
import com.smp.musicspeed.library.mediastore.MediaStoreItem;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.smp.msclibraryfolders.utils.Attributes.fetchAccentColor;
import static com.smp.msclibraryfolders.utils.Attributes.getPrimaryTextColor;


/**
 * Created by steve on 10/7/17.
 */

public abstract class BaseLibraryPageFragment<MT extends MediaStoreItem, VH extends RecyclerView.ViewHolder, AT extends BaseLibraryAdapter<VH, MT>> extends Fragment {

    private Disposable disposable;
    protected RecyclerView recyclerView;
    private TextView empty;

    private AT adapter;
    protected LinearLayoutManager layoutManager;

    protected abstract MediaStore.Id getMediaStoreId();

    public MediaStore mediaStore() {
        return MediaStore.Companion.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity_recycler_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        empty = (TextView) view.findViewById(android.R.id.empty);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());

        setupAdapter();
        setupRecyclerView();
        setupObserver();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mediaStore().pauseObserver(getMediaStoreId());
        Log.i("SOUNDP", "PAUSE");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        mediaStore().resumeObserver(getMediaStoreId());
    }

    private void setupObserver() {
        Observer<List<MT>> obs = new Observer<List<MT>>() {

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                BaseLibraryPageFragment.this.disposable = d;
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull List<MT> items) {
                getAdapter().swapDataSet(items);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        mediaStore().subscribeObserver(getMediaStoreId(), obs);
    }

    protected void setupRecyclerView() {
        int accentColor = fetchAccentColor(getActivity());
        FastScrollRecyclerView frv = (FastScrollRecyclerView) recyclerView;
        frv.setPopupBgColor(accentColor);
        frv.setPopupTextColor(getPrimaryTextColor(getActivity()));
        frv.setThumbColor(accentColor);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = createAdapter();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                checkIsEmpty();
            }
        });
    }

    protected AT getAdapter() {
        return adapter;
    }

    private void checkIsEmpty() {
        if (empty != null) {
            empty.setText(getEmptyMessage());
            empty.setVisibility(adapter == null || adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
        }
    }

    @StringRes
    protected int getEmptyMessage() {
        return R.string.empty;
    }

    @NonNull
    protected abstract AT createAdapter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }
}

