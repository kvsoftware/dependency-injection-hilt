package com.kvsoftware.dependencyinjectionhilt.presentation.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class DIHiltImageView : AppCompatImageView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
    }

    fun downloadImage(thumbImageUrl: String, imageURL: String) {
        Glide.with(context)
            .load(imageURL)
            .thumbnail(Glide.with(context).load(thumbImageUrl))
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
            .into(this)
    }

}