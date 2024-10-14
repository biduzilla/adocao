package com.ricky.adocaoapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.core.content.FileProvider
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

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

fun byteArrayToBitmap(img: String): Bitmap {
    val cleanBase64 = img.replace("data:image/jpeg;base64,", "")
    val byteArray = Base64.decode(cleanBase64, Base64.DEFAULT)

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
        "com.ricky.adocaoapp.fileprovider",
        file
    )
}

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val r = 6371.0

    val lat1Rad = Math.toRadians(lat1)
    val lon1Rad = Math.toRadians(lon1)
    val lat2Rad = Math.toRadians(lat2)
    val lon2Rad = Math.toRadians(lon2)

    val deltaLat = lat2Rad - lat1Rad
    val deltaLon = lon2Rad - lon1Rad

    val a = sin(deltaLat / 2).pow(2) + cos(lat1Rad) * cos(lat2Rad) * sin(deltaLon / 2).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return r * c
}