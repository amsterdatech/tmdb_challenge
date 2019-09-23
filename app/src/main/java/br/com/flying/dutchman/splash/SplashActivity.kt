package br.com.flying.dutchman.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.flying.dutchman.MainActivity

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_DURATION = 1500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed(
            {
                goToSearchResultsActivity(this)
            }, SPLASH_DURATION
        )
    }

    private fun goToSearchResultsActivity(activity: Activity) {
        startActivity(Intent(activity, MainActivity::class.java))
        finish()
    }
}