package me.tangobee.nivevpn.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.tangobee.nivevpn.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}