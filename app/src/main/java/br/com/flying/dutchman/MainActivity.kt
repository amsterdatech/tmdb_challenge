package br.com.flying.dutchman

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.appbar.AppBarLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat.setActivated




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favourites
            )
        )
        navView.setupWithNavController(navController)
//        activity_movies_detail_collapsing.setupWithNavController(
//            movies_toolbar,
//            navController,
//            appBarConfiguration
//        )
        movies_toolbar.setupWithNavController(navController, appBarConfiguration)
//        expandEnabled(false)
    }

//    fun stopScroll() {
//        val toolbarLayoutParams =
//            activity_movies_detail_collapsing.layoutParams as AppBarLayout.LayoutParams
//        toolbarLayoutParams.scrollFlags = 0
//        activity_movies_detail_collapsing.layoutParams = toolbarLayoutParams
//
//        val appBarLayoutParams = activity_app_bar.layoutParams as CoordinatorLayout.LayoutParams
//        appBarLayoutParams.behavior = null
//        activity_app_bar.layoutParams = appBarLayoutParams
//
//        activity_app_bar.setExpanded(false, true)
//        activity_app_bar.isActivated = false
//    }
//
//    fun startScroll() {
//        val toolbarLayoutParams =
//            activity_movies_detail_collapsing.getLayoutParams() as AppBarLayout.LayoutParams
//        toolbarLayoutParams.scrollFlags =
//            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
//        activity_movies_detail_collapsing.setLayoutParams(toolbarLayoutParams)
//
//        val appBarLayoutParams = activity_app_bar.layoutParams as CoordinatorLayout.LayoutParams
//        appBarLayoutParams.behavior = AppBarLayout.Behavior()
//        activity_app_bar.layoutParams = appBarLayoutParams
//
//        activity_app_bar.setExpanded(true, true)
//        activity_app_bar.isActivated = true
//    }
//
//    private fun expandEnabled(enabled: Boolean) {
//        activity_app_bar.setExpanded(enabled, false)
//        activity_app_bar.isActivated = enabled
//        val params = activity_movies_detail_collapsing.layoutParams as AppBarLayout.LayoutParams
//        if (enabled)
//            params.scrollFlags =
//                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
//        else
//            params.scrollFlags =
//                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
//        activity_movies_detail_collapsing.layoutParams = params
//    }
}
