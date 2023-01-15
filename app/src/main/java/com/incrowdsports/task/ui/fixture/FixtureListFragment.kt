package com.incrowdsports.task.ui.fixture

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.incrowdsports.task.R
import com.incrowdsports.task.data.FixtureService
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.databinding.FixtureListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@AndroidEntryPoint
class FixtureListFragment : Fragment(R.layout.fixture_list_fragment) {

    private val binding by lazy { FixtureListFragmentBinding.bind(requireView()) }
    private val viewModel: FixtureListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val compId = 8
        val season = 2021

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadData(compId = compId, season = season)
        }

        val adapter = FixtureListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.fixtureList
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { renderFixtureList(fixtureList = it, adapter = adapter) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadData(compId = compId, season = season)
    }

    private fun renderFixtureList(fixtureList: List<Fixture>, adapter: FixtureListAdapter) {
        binding.swipeRefreshLayout.isRefreshing = false
        val itemList = fixtureList.map {
            FixtureListAdapter.FixtureItem(
                fixture = it,
                onClick = {
                    Toast.makeText(context, "TODO: Navigate to ${it.feedMatchId}", LENGTH_SHORT).show()
                },
            )
        }
        adapter.submitList(itemList)
    }

    companion object {
        fun newInstance() = FixtureListFragment()
    }

}