package com.ricky.adocaoapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.ricky.adocaoapp.presentation.auth.login.LoginEvent
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

fun formatPhoneNumber(phoneNumber: String): String {
    val digits = phoneNumber.filter { it.isDigit() }
    val paddedDigits = digits.padEnd(11, '0')
    return buildString {
        for (i in paddedDigits.indices) {
            when (i) {
                0 -> append('(')
                2 -> append(") ")
                7 -> append('-')
            }
            append(paddedDigits[i])
            if (i == 10) break
        }
    }
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}


fun bitmapToByteArray(
    bitmap: Bitmap,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    maxFileSizeInBytes: Int = 2 * 1024 * 1024,
    maxWidth: Int = 800,
    maxHeight: Int = 800
): ByteArray {
    val resizedBitmap = resizeBitmap(bitmap, maxWidth, maxHeight)

    var quality = 100
    var byteArray: ByteArray

    do {
        val stream = ByteArrayOutputStream()
        resizedBitmap.compress(format, quality, stream)
        byteArray = stream.toByteArray()
        quality -= 5  // Reduz a qualidade em passos de 5
    } while (byteArray.size > maxFileSizeInBytes && quality > 0)

    return byteArray
}

fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val aspectRatio = width.toFloat() / height.toFloat()

    var newWidth = maxWidth
    var newHeight = maxHeight

    if (width > height) {
        newHeight = (newWidth / aspectRatio).toInt()
    } else if (height > width) {
        newWidth = (newHeight * aspectRatio).toInt()
    } else {
        newWidth = maxWidth
        newHeight = maxHeight
    }

    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
}

fun Context.getTempUri(): Uri? {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale("pt", "BR"))
    val imageFileName = "JPEG_" + timeStamp + "_"
    val file = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return FileProvider.getUriForFile(
        this,
        "com.ricky.controle_pet.components.fileprovider",
        file
    )
}
