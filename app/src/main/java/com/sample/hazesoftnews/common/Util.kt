package com.sample.hazesoftnews.common

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.sample.hazesoftnews.R

fun Activity.openUrl(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        showToast(R.string.toast_no_browser)
    }
}

fun Activity.showToast(
    stringId: Int,
    duration: Int = Toast.LENGTH_SHORT
) = Toast.makeText(this, stringId, duration).show()

fun Activity.showToast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT
) = Toast.makeText(this, text, duration).show()