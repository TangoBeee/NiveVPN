package me.tangobee.nivevpn.ui.onboarding

import android.content.Intent
import me.tangobee.nivevpn.utils.CustomTypefaceSpan
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import me.tangobee.nivevpn.R
import me.tangobee.nivevpn.databinding.FragmentSplashScreenBinding
import me.tangobee.nivevpn.ui.base.DashboardActivity
import me.tangobee.nivevpn.utils.NavigateFragmentUtil
import me.tangobee.nivevpn.utils.SharedPrefsManager

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        styleTextView()

        Handler(Looper.getMainLooper()).postDelayed({
            if(getIfUserNew()) {
                val navigateFragmentUtil = NavigateFragmentUtil()
                navigateFragmentUtil.navigateToFragment(
                    requireView(),
                    R.id.action_splashFragment_to_welcomeFragment
                )
            } else {
                val intent = Intent(requireActivity(), DashboardActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }, 3000)
    }

    private fun styleTextView() {
        val extraBoldTypeface = ResourcesCompat.getFont(requireContext(), R.font.poppins_bold)

        val appName = getString(R.string.app_name)
        val vpnSpannableString = SpannableString(appName)
        vpnSpannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.primaryGreenColor)), appName.length-3, appName.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        vpnSpannableString.setSpan(extraBoldTypeface?.let { CustomTypefaceSpan(it) }, appName.length-3, appName.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.vpnNameTV.text = vpnSpannableString


        val splashFooter = getString(R.string.splash_screen_footer)
        val footerSpannableString = SpannableString(splashFooter)
        footerSpannableString.setSpan(Color.BLACK, splashFooter.length-4, splashFooter.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        footerSpannableString.setSpan(extraBoldTypeface?.let { CustomTypefaceSpan(it) }, splashFooter.length-4, splashFooter.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.footerTV.text = footerSpannableString
    }

    private fun getIfUserNew(): Boolean {
        val sharedPref = SharedPrefsManager.getSharedPref(requireContext())
        return sharedPref.getBoolean("newUser", true)
    }
}