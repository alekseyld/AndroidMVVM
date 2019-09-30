package com.alekseyld.pet_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import com.alekseyld.pet_mvvm.R
import com.alekseyld.pet_mvvm.ui.base.BaseFragment
import com.alekseyld.pet_mvvm.ui.base.FragmentHolder
import com.alekseyld.pet_mvvm.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), FragmentHolder {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val fragment = supportFragmentManager.findFragmentById(R.id.container)

            (fragment as? BaseFragment<*, *>)?.onBackKeyPressed()
        }
        else {
            super.onBackPressed()
        }
    }

    override fun showProgressBar() {
        container.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        errorText.visibility = View.GONE
    }

    override fun hideProgressBar() {
        container.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun setErrorText(text: String) {
        errorText.text = text
        errorText.visibility = View.VISIBLE
    }
}
