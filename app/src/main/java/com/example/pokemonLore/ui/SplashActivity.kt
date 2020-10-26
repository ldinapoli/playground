package com.example.pokemonLore.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.pokemonLore.R
import com.example.pokemonLore.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splashAnimation.setAnimation(R.raw.pikachu)
    }
    private val handler = Handler()

    private val runnable = Runnable {
        if(!isFinishing) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}