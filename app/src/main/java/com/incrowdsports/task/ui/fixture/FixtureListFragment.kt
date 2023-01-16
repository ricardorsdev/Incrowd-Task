package com.incrowdsports.task.ui.fixture

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.incrowdsports.task.R
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.databinding.FixtureListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class FixtureListFragment : Fragment(R.layout.fixture_list_fragment) {

    private val binding by lazy { FixtureListFragmentBinding.bind(requireView()) }
    private val viewModel: FixtureListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            viewModel.loadData()
        }

        val adapter = FixtureListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.fixtureList
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach {
                renderFixtureList(fixtureList = it, adapter = adapter)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun renderFixtureList(fixtureList: List<Fixture>, adapter: FixtureListAdapter) {
        binding.swipeRefreshLayout.isRefreshing = false
        val itemList = fixtureList.map {
            FixtureListAdapter.FixtureItem(
                fixture = it,
                onClick = {
                    goToFixtureDetails(it.feedMatchId)
                },
            )
        }
        adapter.submitList(itemList)
    }

    private fun goToFixtureDetails(feedMatchId: Long) {
        val fragmentManager = this.parentFragmentManager
        val fragment = FixtureDetailsFragment.newInstance(feedMatchId)
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = FixtureListFragment()
    }

}