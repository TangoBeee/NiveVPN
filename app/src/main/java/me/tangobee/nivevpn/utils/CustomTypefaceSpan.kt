package me.tangobee.nivevpn.utils

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class CustomTypefaceSpan(private val typeface: Typeface) : MetricAffectingSpan() {
    override fun updateDrawState(tp: TextPaint) {
        applyCustomTypeface(tp)
    }

    override fun updateMeasureState(tp: TextPaint) {
        applyCustomTypeface(tp)
    }

    private fun applyCustomTypeface(paint: Paint) {
        val oldTypeface = paint.typeface
        val oldStyle = oldTypeface?.style ?: 0

        val fakeStyle = oldStyle and typeface.style.inv()
        if ((fakeStyle and Typeface.BOLD) != 0) {
            paint.isFakeBoldText = true
        }

        paint.typeface = typeface
    }
}
