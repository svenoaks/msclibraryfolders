package com.smp.msclibraryfolders.library.song;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;



import com.smp.msclibraryfolders.library.song.Song;

import java.util.ArrayList;

/**
 * Created by steve on 10/7/17.
 */

public class SongsMenuHelper {
    public static boolean handleMenuClick(@NonNull FragmentActivity activity, @NonNull ArrayList<Song> songs, int menuItemId) {
        switch (menuItemId) {
            /*case R.id.action_play_next:
                //MusicPlayerRemote.playNext(songs);
                return true;
            case R.id.action_add_to_current_playing:
                //MusicPlayerRemote.enqueue(songs);
                return true;
            case R.id.action_add_to_playlist:
                //AddToPlaylistDialog.create(songs).show(activity.getSupportFragmentManager(), "ADD_PLAYLIST");
                return true;*/
        }
        return false;
    }
}
