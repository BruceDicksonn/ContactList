package com.example.applistadecontatosktx.commons

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.applistadecontatosktx.R
import java.io.ByteArrayOutputStream

class Utils {

    companion object {

        private fun drawableToBitmap(drawable: Drawable): Bitmap {
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }

            val width = drawable.intrinsicWidth
            val height = drawable.intrinsicHeight

            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)

            return bitmap
        }

        fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            return outputStream.toByteArray()
        }

        fun defaultBitmap(context: Context): Bitmap {
            val defaultDrawable =
                ContextCompat.getDrawable(context, R.drawable.ic_default_contact)!!
            return BitmapDrawable(context.resources, drawableToBitmap(defaultDrawable)).bitmap
        }


    }

}