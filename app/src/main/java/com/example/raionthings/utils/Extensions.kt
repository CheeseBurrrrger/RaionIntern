package com.example.raionthings.utils

import android.net.Uri
import android.content.Context
import io.ktor.utils.io.errors.IOException

@Throws(IOException::class)
fun Uri.uriToByteArray(context: Context) =
    context.contentResolver.openInputStream(this)?.use {it.buffered().readBytes()}