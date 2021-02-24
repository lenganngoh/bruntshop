package com.lenganngoh.bruntshop.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.lenganngoh.bruntshop.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment() {

    private lateinit var binding: FragmentConfirmationBinding
    private lateinit var listener: Listener

    companion object {
        fun newInstance(): ConfirmationFragment {
            val args = Bundle()
            val fragment = ConfirmationFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentConfirmationBinding.inflate(LayoutInflater.from(context), container, false)

        initializeViews()

        return binding.root
    }

    private fun initializeViews() {
        val htmlText =  HtmlCompat.fromHtml(String.format("Your order ID is <b>#%s</b>", (100000..999999).random().toString()), HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.txtOrderID.text = htmlText

        binding.btnReturn.setOnClickListener {
            listener.onReturn()
        }
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    interface Listener {
        fun onReturn()
    }
}