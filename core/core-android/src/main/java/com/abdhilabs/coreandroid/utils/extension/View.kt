package com.abdhilabs.coreandroid.utils.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.setImageFromUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun ImageView.setImageFromDrawable(drawable: Int) {
    Glide.with(this).load(drawable).into(this)
}

fun ImageView.setBitmapToImages(src: Bitmap?) =
    Glide.with(this)
        .asBitmap()
        .load(src)
//        .error(R.drawable.ic_broken_image)
        .into(this)

fun Context.vectorToBitmap(drawableId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableId) ?: return null
    val bitmap =
        createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

fun List<View>.isValidField(
    doSomething: (() -> Unit)? = null,
    isUseErrorMessage: Boolean? = true
): Boolean {
    var status = true
    forEach { vi ->
        if (vi.visibility == View.VISIBLE) {
            if (vi is EditText && vi.text.toString().isEmpty()) {
                status = false
                if (isUseErrorMessage == true) {
                    vi.error = "This field is required!"
                }


            } else if (vi is TextInputEditText && vi.text.toString().isEmpty()) {
                status = false
                if (isUseErrorMessage == true) {
                    vi.error = "This field is required!"
                }

            }
        }
    }
    return status
}

fun EditText.isValidEmailFormat(isUseErrorMessage: Boolean? = true): Boolean {
    val EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    val pattern = Pattern.compile(EMAIL_PATTERN)
    val matcher = pattern.matcher(text.toString())
    val isValid = matcher.matches()
    if (isUseErrorMessage == true) {
        if (!isValid) error = "Invalid Email Format"
    }
    return isValid
}