package me.tangobee.nivevpn.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.tangobee.nivevpn.R

class AppStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_start)
    }
}