package com.incrowdsports.task.ui.fixture

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.incrowdsports.task.R
import com.incrowdsports.task.databinding.FixtureDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FixtureDetailsFragment : Fragment(R.layout.fixture_details_fragment) {

    private val binding by lazy { FixtureDetailsFragmentBinding.bind(requireView()) }
    private val viewModel: FixtureListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var feedMatchId: Long? = null
        val bundle = this.arguments
        bundle?.let {
            feedMatchId = bundle.getLong(FEED_MATCH_ID)
        }

        feedMatchId?.let {
            viewModel.getFixtureDetails(it)
        }
    }

    companion object {
        private const val FEED_MATCH_ID = "FEED_MATCH_ID"

        fun newInstance(feedMatchId: Long): Fragment {
            val arguments = Bundle()
            arguments.putLong(FEED_MATCH_ID, feedMatchId)

            val fragment = FixtureDetailsFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}