package com.lenganngoh.bruntshop.ui.main.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.databinding.DialogBottomNotificationBinding

class BottomNotificationDialog : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(
        context: Context, attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    private var binding: DialogBottomNotificationBinding =
        DialogBottomNotificationBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        removeAllViews()
        addView(binding.root)
    }

    private var product: Product? = null

    fun setProduct(
        product: Product
    ) {
        this.product = product
        updateView()
    }

    private fun updateView() {
        if (product != null) {
            setNotificationBackground(product!!.bgColor!!)
            setNotificationText()
        }
    }

    private fun setNotificationText() {
        binding.txtDetail.text =
            HtmlCompat.fromHtml(String.format("<b>%s</b> has been added to your cart.", product!!.name), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun setNotificationBackground(color: String) {
        val drawable = binding.bgNotification.background as GradientDrawable
        drawable.setColor(Color.parseColor(color))
        binding.bgNotification.background = drawable
    }
}