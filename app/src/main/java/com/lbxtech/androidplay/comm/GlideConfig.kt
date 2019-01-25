package com.lbxtech.androidplay.comm

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideConfig : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCache: Long = 1024 * 1024 * 10
        val diskCache: Long = 1024 * 1024 * 60
        builder.setMemoryCache(LruResourceCache(memoryCache))
            .setDiskCache(InternalCacheDiskCacheFactory(context, diskCache))
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}