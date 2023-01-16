package com.incrowdsports.task.ui.fixture

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.incrowdsports.task.R
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.databinding.FixtureDetailsFragmentBinding
import com.incrowdsports.task.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


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

        viewModel.fixtureDetails
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                it?.let {
                    setView(it)
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun setView(fixture: Fixture) {
        val homeTeamAdapter = TeamPlayersAdapter(fixture.homeTeam.players)
        binding.homeTeamList.adapter = homeTeamAdapter
        binding.homeTeamList.addItemDecoration(
            DividerItemDecoration(
                binding.homeTeamList.context,
                DividerItemDecoration.VERTICAL
            )
        )

        val awayTeamAdapter = TeamPlayersAdapter(fixture.awayTeam.players)
        binding.awayTeamList.adapter = awayTeamAdapter
        binding.awayTeamList.addItemDecoration(
            DividerItemDecoration(
                binding.awayTeamList.context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.apply {
            competition.text = fixture.competition
            season.text = fixture.season
            date.text = DateUtils.changeStringDateFormat(fixture.date)
            competition.text = fixture.competition
            homeTeam.text = fixture.homeTeam.name
            homeTeamScore.text = fixture.homeTeam.score
            awayTeam.text = fixture.awayTeam.name
            awayTeamScore.text = fixture.awayTeam.score
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