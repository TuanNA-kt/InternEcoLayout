package com.eco.musicplayer.audioplayer.music.ui.component.paywall.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.ProductDetails
import com.eco.musicplayer.audioplayer.music.R
import com.eco.musicplayer.audioplayer.music.databinding.FragmentPaywallFeatureBinding
import com.eco.musicplayer.audioplayer.music.utils.setPolicyText
import com.eco.musicplayer.audioplayer.music.utils.underline


class PaywallFeatureFragment : Fragment() {

    private var _binding: FragmentPaywallFeatureBinding? = null
    private val binding get() = _binding!!

    private val CONSUMABLE_COUNTER_ID = "product_id_iap"
    private val SUBSCRIPTIONS_ID = "product_id_sub"

    private var billingClient: BillingClient? = null
    private var productDetailList = mutableListOf<ProductDetails>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaywallFeatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeData()
        registerListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeData() {
        setUpSubscriptionTextView()
        setUpPrivacyTextView()
        binding.tvLimited.underline()
    }

    private fun registerListeners() {
        setupToggleSubscriptionOptions()
        binding.ivClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setUpSubscriptionTextView() {
        binding.mFreeTrial.apply {
            tvTitle.text = getString(R.string.free_trial)
            tvDescription.text = getString(R.string.free_trial_description)
            tvPrice.text = getString(R.string.free_trial_price_dollar)
            ivCheckButton.setImageResource(R.drawable.bg_check_button_orange_selector)
            tvPeriod.text = getString(R.string.free_trial_price_period)
            tvPeriod.append(" ")

        }
        binding.mMonthly.apply {
            tvTitle.text = getString(R.string.monthly)
            tvDescription.text = getString(R.string.monthly_description)
            tvPrice.text = getString(R.string.monthly_price_dollar)
            ivCheckButton.setImageResource(R.drawable.bg_check_button_orange_selector)
            tvPeriod.text = getString(R.string.monthly_price_period)
            tvPeriod.append(" ")
        }
        binding.mLifetime.apply {
            tvTitle.text = getString(R.string.lifetime)
            tvDescription.text = getString(R.string.lifetime_description)
            tvPrice.text = getString(R.string.lifetime_price_dollar)
            ivCheckButton.setImageResource(R.drawable.bg_check_button_orange_selector)
            tvPeriod.text = getString(R.string.lifetime_price_period)
            tvPeriod.append(" ")
        }
    }

    private fun setUpPrivacyTextView() {
        val bulletStrings = resources.getStringArray(R.array.subscription_items).toList()

        binding.tvPolicy.setPolicyText(
            onTermsClick = {
                // handle terms click
            },
            onPrivacyClick = {
                // handle privacy click
            },
            bulletTextList = bulletStrings
        )
    }

    private fun setupToggleSubscriptionOptions() {
        val cards = listOf(binding.clFreeTrial, binding.clMonthly, binding.clLifetime)
        val cardsInner = listOf(binding.mFreeTrial, binding.mMonthly, binding.mLifetime)

        cards.forEachIndexed { index, card ->
            card.setOnClickListener {

                cards.forEach { it.isSelected = false }
                cardsInner.forEach { it.ivCheckButton.isSelected = false }

                card.isSelected = true
                cardsInner[index].ivCheckButton.isSelected = true
            }
        }
    }
}