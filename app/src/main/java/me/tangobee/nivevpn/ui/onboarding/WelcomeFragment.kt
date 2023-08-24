package me.tangobee.nivevpn.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.viewpager.widget.ViewPager
import me.tangobee.nivevpn.R
import me.tangobee.nivevpn.databinding.FragmentWelcomeBinding
import me.tangobee.nivevpn.ui.adapters.WelcomeViewPagerAdapter
import me.tangobee.nivevpn.ui.base.DashboardActivity
import me.tangobee.nivevpn.utils.SharedPrefsManager

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //This will tell us if user is new or not and do we need to show welcome screen to user or not.
        setWelcomeFlag()

        setAdapter()

        binding.welcomeCardViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {
                val lastItemPosition = binding.welcomeCardViewPager.adapter?.count?.minus(1) ?: 0
                if (position == lastItemPosition) {
                    binding.nextButton.visibility = View.VISIBLE
                    setButtonFadeInAnimation()
                    binding.cardIndicator.visibility = View.GONE
                } else {
                    binding.nextButton.visibility = View.GONE
                    binding.cardIndicator.visibility = View.VISIBLE
                }
            }
        })

        binding.nextButton.setOnClickListener{
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun setButtonFadeInAnimation() {
        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.nextButton.animation = fadeInAnimation
    }

    private fun setAdapter() {
        val adapter = WelcomeViewPagerAdapter(requireContext())
        binding.welcomeCardViewPager.adapter = adapter
        binding.cardIndicator.attachTo(binding.welcomeCardViewPager)
    }

    private fun setWelcomeFlag() {
        val sharedPref = SharedPrefsManager.getSharedPref(requireContext())
        val sharedPrefEditor = sharedPref.edit()
        sharedPrefEditor.putBoolean("newUser", false)
        sharedPrefEditor.apply()
    }
}