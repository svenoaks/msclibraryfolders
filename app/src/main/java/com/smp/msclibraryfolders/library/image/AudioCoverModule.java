package com.smp.msclibraryfolders.library.image;

import android.content.Context;
import android.media.MediaMetadataRetriever;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.module.GlideModule;
import com.smp.msclibraryfolders.R;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.smp.msclibraryfolders.utils.FileMethods.getExtension;
import static com.smp.msclibraryfolders.utils.FileMethods.isAudioForMetadata;

/**
 * Created by Steve on 11/1/16.
 */


public class AudioCoverModule implements GlideModule
{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }
    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(AudioCover.class, InputStream.class, new AudioCoverLoader.Factory());
    }
}

class AudioCoverLoader implements StreamModelLoader<AudioCover>
{
    @Override
    public DataFetcher<InputStream> getResourceFetcher(AudioCover model, int width, int height) {
        return new AudioCoverFetcher(model);
    }

    static class Factory implements ModelLoaderFactory<AudioCover, InputStream>
    {
        @Override
        public ModelLoader<AudioCover, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new AudioCoverLoader();
        }
        @Override
        public void teardown() {
        }
    }
}

class AudioCoverFetcher implements DataFetcher<InputStream> {
    private final AudioCover model;
    private InputStream stream;

    public AudioCoverFetcher(AudioCover model) {
        this.model = model;
    }

    @Override
    public String getId() {
        return model.path;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        String ext = getExtension(model.path).replace(".", "");
        if (isAudioForMetadata(ext))
        {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            try {
                retriever.setDataSource(model.path);
                byte[] picture = retriever.getEmbeddedPicture();
                if (picture != null) {
                    return new ByteArrayInputStream(picture);
                } else {
                    return fallback(model.path);
                }
            }
            catch (Exception e) {
                return fallback(model.path);
            }
            finally {
                retriever.release();
            }
        }
        return fallback(model.path);
    }

    @SuppressWarnings("ResourceType")
    private InputStream fallback(String path) throws FileNotFoundException
    {
        return stream = model.context.getResources().openRawResource(R.drawable.defaultcover);
    }

    @Override
    public void cleanup() {
        // already cleaned up in loadData and ByteArrayInputStream will be GC'd
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException ignore) {
                // can't do much about it
            }
        }
    }
    @Override
    public void cancel() {
        // cannot cancel
    }
}