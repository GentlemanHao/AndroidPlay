package com.lbxtech.androidplay.comm;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class GlideConfig extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        long memoryCache = 1024 * 1024 * 10;
        long diskCache = 1024 * 1024 * 60;
        builder.setMemoryCache(new LruResourceCache(memoryCache))
                .setDiskCache(new InternalCacheDiskCacheFactory(context, diskCache));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}