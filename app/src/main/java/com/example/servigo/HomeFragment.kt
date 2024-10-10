package com.example.servigo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: FragmentPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views here, not in onCreate
        viewPager2 = view.findViewById(R.id.viewpager2)
        tabLayout = view.findViewById(R.id.tabLayout)

        // Set up the adapter
        adapter = FragmentPageAdapter(childFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        // Add tabs
        tabLayout.addTab(tabLayout.newTab().setText("Service"))
        tabLayout.addTab(tabLayout.newTab().setText("Job"))

        // Link TabLayout and ViewPager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "Service"
                1 -> "Job"
                else -> null
            }
        }.attach()

        // Set up Tab selected listener
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { viewPager2.currentItem = it.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Do nothing or implement if necessary
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Do nothing or implement if necessary
            }
        })

        // Sync TabLayout with ViewPager2
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}