package com.lenganngoh.bruntshop.util

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityUtil {
    companion object {
        fun renderAsFullscreenAdjustResize(
            activity: AppCompatActivity,
            rootView: View
        ) {
            renderFullScreen(activity)
            fixAdjustResize(rootView)
        }

        fun renderFullScreen(activity: AppCompatActivity) {
            val window = activity.window
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

        private fun fixAdjustResize(rootView: View) {
            rootView.fitsSystemWindows = true
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, insets ->
                ViewCompat.onApplyWindowInsets(
                    view,
                    WindowInsetsCompat.Builder(insets).setSystemWindowInsets(
                            Insets.of(0, 0, 0, insets.systemWindowInsetBottom)
                    ).build()
                )
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun setStatusBarTextColor(activity: AppCompatActivity, isDark: Boolean) {
            val flags = activity.window.decorView.systemUiVisibility
            if (isDark) {
                activity.window.decorView.systemUiVisibility =
                    flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                activity.window.decorView.systemUiVisibility =
                    flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}