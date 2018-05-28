package com.example.henry.gwenthelpmate

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.henry.gwenthelpmate.Containers.TabCard
import com.example.henry.gwenthelpmate.Containers.TabDeck
import com.example.henry.gwenthelpmate.Containers.TabNews
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.app_bar_start.*

class StartActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mSectionsPagerAdapter: StartActivity.SectionsPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        containerStart.adapter = mSectionsPagerAdapter
        containerStart.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabItems))
        tabItems.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(containerStart))
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action11", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.start, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.nav_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position){
                0-> {
                    var tab1: TabCard = TabCard()
                    return tab1
                }
                1-> {
                    var tab2: TabDeck = TabDeck()
                    return tab2
                }
                2-> {
                    var tab3: TabNews = TabNews()
                    return tab3
                }
                else -> {
                    return null
                }
            }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }
}
