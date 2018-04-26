package tk.wjclub.wjcalc

import android.net.Uri
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main_calculator_frame.*
import kotlinx.android.synthetic.main.app_bar_main_calculator_frame.*
import tk.wjclub.wjcalc.dummy.DummyContent
import kotlinx.coroutines.*

class MainCalculatorFrame() : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, PlebCalculator.OnFragmentInteractionListener, ScientificCalculator.OnFragmentInteractionListener, ProgrammingCalculator.OnFragmentInteractionListener, ToolsFragment.OnListFragmentInteractionListener {
    private var fragments : Array<Fragment> = arrayOf(PlebCalculator(), ScientificCalculator(), ProgrammingCalculator(), ToolsFragment())

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calculator_frame)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        onNavigationItemSelected(nav_view.menu.findItem(R.id.nav_pleb))
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        for (frag : Fragment in fragments) {
            supportFragmentManager.beginTransaction().attach(frag).commit()
        }
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
        menuInflater.inflate(R.menu.main_calculator_frame, menu)
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
            R.id.nav_pleb -> replaceFragment(fragment = this.fragments[0])
            R.id.nav_scientific -> replaceFragment(fragment = this.fragments[1])
            R.id.nav_programmer -> replaceFragment(fragment = this.fragments[2])
            R.id.nav_tools -> replaceFragment(fragment = this.fragments[3])
            R.id.nav_share -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replaceFragment(fragment : Fragment) {
        //thanks androidhumanrobot & duggu on StackOverflow
        val transaction = supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).show(fragment)
        transaction?.replace(R.id.mainFrameContainer, fragment)
        transaction?.commitAllowingStateLoss()
    }
}
