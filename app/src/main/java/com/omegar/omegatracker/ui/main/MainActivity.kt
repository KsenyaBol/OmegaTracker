package com.omegar.omegatracker.ui.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.omega_r.libs.extensions.fragment.edit
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity
import com.omegar.omegatracker.ui.base.BaseFragment

class MainActivity : BaseActivity(R.layout.activity_main), MainView {

    companion object {

        private const val EXTRA_AUTHORIZATION_TOKEN = "token"

        fun createLauncher(authToken: String) = createActivityLauncher(
            EXTRA_AUTHORIZATION_TOKEN put authToken
        )
    }

    override val presenter: MainPresenter by providePresenter {
        MainPresenter(this[EXTRA_AUTHORIZATION_TOKEN]!!)
    }

    private val bottomNavigationView: BottomNavigationView by bind(R.id.bottom_navigation) {
        setOnItemSelectedListener { item ->
            this@MainActivity.presenter.requestChangeMainPage(mainPages.first { it.id == item.itemId })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMenu(R.menu.menu_toolbar, R.id.action_logout to presenter::onLogoutClicked)
    }

    override var mainPages: List<MainPage> = emptyList()
        set(pages) {
            field = pages
            if(pages.size >= 2) {
                bottomNavigationView.isVisible = true
                bottomNavigationView.menu.clear()
                pages.forEach { page ->
                    bottomNavigationView.menu.add(0, page.id, 0, null).also {
                        it.icon = page.icon.getDrawable(this)
                    }
                }
            } else {
                bottomNavigationView.isVisible = false
            }
        }
    override var currentMainPage: MainPage? = null
        set(value) {
            if (field != value && value != null) {
                field = value
                supportFragmentManager.edit {
                    //etCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    val hideFragments = getActiveFragments() - showOrAdd(value)
                    hideFragments.forEach { hide(it) }
                    if (bottomNavigationView.selectedItemId != value.id) {
                        bottomNavigationView.selectedItemId = value.id
                    }
                    if(value.id == 1) presenter.showComingSoon()
                }
            }
        }

    override var notificationPages: List<MainPage> = emptyList()
        set(value) {
            field = value
            mainPages.forEach {
                bottomNavigationView.removeBadge(it.id)
            }
            notificationPages
                .groupBy { it }
                .forEach { group ->
                    bottomNavigationView.getOrCreateBadge(group.key.id)
                }
        }

    private fun getActiveFragments() = supportFragmentManager.fragments.filter { !it.isHidden && it is BaseFragment }

    private fun FragmentTransaction.showOrAdd(page: MainPage): Fragment {
        return page.findFragmentOrNull()
            ?.also(::show)
            ?: run {
                val newFragment = page.launcher.createFragment()
                add(R.id.layout_container, newFragment, page.id.toString())
                newFragment
            }
    }

    private fun MainPage.findFragmentOrNull() = supportFragmentManager.fragments.firstOrNull { fragment ->
        fragment.tag?.toIntOrNull() == id && launcher.isOurFragment(fragment)
    }

}