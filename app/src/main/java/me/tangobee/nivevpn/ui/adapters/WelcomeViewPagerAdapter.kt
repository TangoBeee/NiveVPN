package me.tangobee.nivevpn.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import me.tangobee.nivevpn.R

class WelcomeViewPagerAdapter(private val context: Context) : PagerAdapter() {

    private val image: ArrayList<Int> = arrayListOf(
        R.drawable.no_spy,
        R.drawable.secure_location,
        R.drawable.internet_symbol
    )

    private val header: ArrayList<Int> = arrayListOf(
        R.string.onboard_head1,
        R.string.onboard_head2,
        R.string.onboard_head3
    )

    private val description: ArrayList<Int> = arrayListOf(
        R.string.onboard_description1,
        R.string.onboard_description2,
        R.string.onboard_description3
    )

    override fun getCount(): Int {
        return image.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.welcome_layout_items, container, false) as ConstraintLayout

        // Setting up the image, header, and description using layout.findViewById()
        val imageView: ImageView = layout.findViewById(R.id.imageHead)
        val headerView: TextView = layout.findViewById(R.id.onboardingHeaderTV)
        val descriptionView: TextView = layout.findViewById(R.id.onboardingDescriptionTV)

        imageView.setImageResource(image[position])
        headerView.setText(header[position])
        descriptionView.setText(description[position])

        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}