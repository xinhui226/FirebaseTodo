package com.xinhui.mob201.ui.tabContainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.xinhui.mob201.ui.adapter.FragmentAdapter
import com.xinhui.mob201.ui.home.HomeFragment
import com.xinhui.mob201.ui.profile.ProfileFragment
import com.xinhui.mob21firebase.databinding.FragmentTabContainerBinding

class TabContainerFragment : Fragment() {
    private lateinit var binding: FragmentTabContainerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpContainer.adapter = FragmentAdapter(
            this,
            listOf(HomeFragment(), ProfileFragment())
        )

        TabLayoutMediator(binding.tlTabs, binding.vpContainer) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Home"
                }
                else -> {
                    tab.text = "Profile"
                }
            }
        }.attach()
    }
}