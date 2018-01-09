package com.smp.msclibraryfolders.library.song;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.smp.msclibraryfolders.R;
import com.smp.msclibraryfolders.library.song.Song;


/**
 * Created by steve on 10/7/17.
 */

public class SongMenuHelper {
    //public static final int MENU_RES = R.menu.menu_item_song;

    public static boolean handleMenuClick(@NonNull FragmentActivity activity, @NonNull Song song, int menuItemId) {
        switch (menuItemId) {
            /*case R.id.action_add_to_playlist:
                //AddToPlaylistDialog.create(song).show(activity.getSupportFragmentManager(), "ADD_PLAYLIST");
                return true;
            case R.id.action_play_next:
                //MusicPlayerRemote.playNext(song);
                return true;
            case R.id.action_add_to_current_playing:
                //MusicPlayerRemote.enqueue(song);
                return true;
            case R.id.action_tag_editor:
                *//*Intent tagEditorIntent = new Intent(activity, SongTagEditorActivity.class);
                tagEditorIntent.putExtra(AbsTagEditorActivity.EXTRA_ID, song.id);
                if (activity instanceof PaletteColorHolder)
                    tagEditorIntent.putExtra(AbsTagEditorActivity.EXTRA_PALETTE, ((PaletteColorHolder) activity).getPaletteColor());
                activity.startActivity(tagEditorIntent);*//*
                return true;
            case R.id.action_details:
                //SongDetailDialog.create(song).show(activity.getSupportFragmentManager(), "SONG_DETAILS");
                return true;
            case R.id.action_go_to_album:
                //NavigationUtil.goToAlbum(activity, song.albumId);
                return true;
            case R.id.action_go_to_artist:
                //NavigationUtil.goToArtist(activity, song.artistId);
                return true;*/
        }
        return false;
    }
}
