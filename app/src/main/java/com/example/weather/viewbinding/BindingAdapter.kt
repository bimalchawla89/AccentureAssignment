package com.example.weather.viewbinding

import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("weatherIcon")
fun setWeatherIcon(view: ImageView, iconPath: String?) {
    if (iconPath.isNullOrEmpty())
        return
    val newPath = iconPath.replace(iconPath, "a$iconPath")
    val imageId = view.context.resources.getIdentifier(newPath + "_svg", "drawable", view.context.packageName)
    val imageDrawable = ResourcesCompat.getDrawable(view.resources, imageId, null)
    view.setImageDrawable(imageDrawable)
}