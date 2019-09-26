package io.github.gustavobarbosab.contacts.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["sourceURI", "defaultImg"], requireAll = false)
        fun imageResourceWithURI(
            image: ImageView,
            sourceURI: String?,
            defaultImg: Drawable?
        ) {
            when (sourceURI?.isNotEmpty()) {
                true -> image.setImageURI(Uri.parse(sourceURI))
                else -> image.setImageDrawable(defaultImg)
            }
        }
    }
}
