package com.smp.msclibraryfolders.library;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.smp.msclibraryfolders.R;
import com.smp.musicspeed.library.song.SongsFragment;

class LibraryPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public LibraryPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SongsFragment();
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.tab_songs);
            default:
                return "Page " + position;
        }
    }

}