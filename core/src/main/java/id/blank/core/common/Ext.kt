package id.blank.core.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun ImageView.load(imageUri: Any) {
    Glide.with(context)
        .load(imageUri)
        .apply(RequestOptions())
        .into(this)
}


fun ImageView.loadToBackground(imageUri: Any){
    Glide.with(context)
        .load(imageUri)
        .apply(RequestOptions())
        .into(object : CustomTarget<Drawable>() {
            override fun onLoadCleared(placeholder: Drawable?) {

            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                this@loadToBackground.background = resource
            }
        }
        )
}