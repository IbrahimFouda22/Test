package com.example.test.util


import android.widget.ProgressBar
import androidx.databinding.BindingAdapter



@BindingAdapter("bindProgress")
fun bindProgress(progressBar: ProgressBar, boolean: Boolean) {
    if (boolean) progressBar.visible()
    else progressBar.gone()
}