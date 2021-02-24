package com.lenganngoh.bruntshop.util

import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation

class AnimationUtil {
    companion object {
        private const val millisNormal = 500L

        fun animateViewTransY(
            view: View,
            startY: Float,
            targetY: Float,
            delay: Long,
            listener: AnimatorListenerAdapter?
        ) {
            val valueAnimator = ValueAnimator.ofFloat(startY, targetY)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                view.translationY = value
            }

            valueAnimator.interpolator = AccelerateDecelerateInterpolator()
            valueAnimator.duration = millisNormal
            if (listener != null) valueAnimator.addListener(listener)
            valueAnimator.startDelay = delay
            valueAnimator.start()
        }

        fun animateAlpha(view: View, startAlpha: Float, targetAlpha: Float, delay: Long) {
            view.alpha = startAlpha
            view.visibility = View.VISIBLE
            val viewAnimator = view.animate().alpha(targetAlpha).setDuration(millisNormal)
            viewAnimator.startDelay = delay
            viewAnimator.start()
        }

        fun inFromRightAnimation(): Animation {
            val inFromRight: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            inFromRight.duration = 200
            inFromRight.interpolator = AccelerateDecelerateInterpolator()
            return inFromRight
        }

        fun inFromRightAnimation(millis: Long): Animation {
            val inFromRight: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            inFromRight.duration = millis
            inFromRight.interpolator = AccelerateDecelerateInterpolator()
            return inFromRight
        }

        fun outToLeftAnimation(): Animation {
            val outtoLeft: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            outtoLeft.duration = 200
            outtoLeft.interpolator = AccelerateDecelerateInterpolator()
            return outtoLeft
        }

        fun outToLeftAnimation(millis: Long): Animation {
            val outtoLeft: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            outtoLeft.duration = millis
            outtoLeft.interpolator = AccelerateDecelerateInterpolator()
            return outtoLeft
        }

        fun inFromLeftAnimation(): Animation {
            val inFromLeft: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            inFromLeft.duration = 200
            inFromLeft.interpolator = AccelerateDecelerateInterpolator()
            return inFromLeft
        }

        fun inFromLeftAnimation(millis: Long): Animation {
            val inFromLeft: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            inFromLeft.duration = millis
            inFromLeft.interpolator = AccelerateDecelerateInterpolator()
            return inFromLeft
        }

        fun outToRightAnimation(): Animation {
            val outtoRight: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            outtoRight.duration = 200
            outtoRight.interpolator = AccelerateDecelerateInterpolator()
            return outtoRight
        }

        fun outToRightAnimation(millis: Long): Animation {
            val outtoRight: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            outtoRight.duration = millis
            outtoRight.interpolator = AccelerateDecelerateInterpolator()
            return outtoRight
        }

        fun outToTopAnimation(): Animation {
            val outtoTop: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f
            )
            outtoTop.duration = 200
            outtoTop.interpolator = DecelerateInterpolator()
            return outtoTop
        }

        fun outToTopAnimation(millis: Long): Animation {
            val outtoTop: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f
            )
            outtoTop.duration = millis
            outtoTop.interpolator = DecelerateInterpolator()
            return outtoTop
        }

        fun inFromTopAnimation(): Animation {
            val inFromTop: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            inFromTop.duration = 200
            inFromTop.interpolator = DecelerateInterpolator()
            return inFromTop
        }

        fun inFromTopAnimation(millis: Long): Animation {
            val inFromTop: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            inFromTop.duration = millis
            inFromTop.interpolator = DecelerateInterpolator()
            return inFromTop
        }

        fun outToBottomAnimation(): Animation {
            val outtoTop: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f
            )
            outtoTop.duration = 1000
            outtoTop.interpolator = DecelerateInterpolator()
            return outtoTop
        }

        fun inFromBottomAnimation(): Animation {
            val inFromBottom: Animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
            )
            inFromBottom.duration = 200
            inFromBottom.interpolator = DecelerateInterpolator()
            return inFromBottom
        }
    }
}