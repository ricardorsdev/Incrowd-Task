package com.incrowdsports.task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import com.incrowdsports.task.databinding.ActivityMainBinding
import com.incrowdsports.task.ui.fixture.FixtureListFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(binding.fragmentContainerView.id, FixtureListFragment.newInstance())
            }
        }
    }
}