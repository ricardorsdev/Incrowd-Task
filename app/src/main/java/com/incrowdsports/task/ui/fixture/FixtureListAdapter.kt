package com.incrowdsports.task.ui.fixture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.databinding.FixtureLayoutBinding
import com.incrowdsports.task.utils.DateUtils
import java.util.Date

class FixtureListAdapter : ListAdapter<FixtureListAdapter.FixtureItem, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FixtureViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FixtureViewHolder).bind(getItem(position))
    }

    private class FixtureViewHolder(private val binding: FixtureLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FixtureItem) {
            binding.apply {
                root.setOnClickListener { item.onClick.invoke() }
                competition.text = item.fixture.competition
                period.text = item.fixture.period
                venue.text = item.fixture.venue.name
                homeName.text = item.fixture.homeTeam.name
                homeScore.text = item.fixture.homeTeam.score
                awayName.text = item.fixture.awayTeam.name
                awayScore.text = item.fixture.awayTeam.score
                dateTime.text = DateUtils.changeStringDateFormat(item.fixture.date)
            }
        }

        companion object {
            fun create(parent: ViewGroup) = FixtureViewHolder(FixtureLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    }

    data class FixtureItem(
        val fixture: Fixture,
        val onClick: () -> Unit,
    )

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<FixtureItem>() {
            override fun areItemsTheSame(oldItem: FixtureItem, newItem: FixtureItem): Boolean {
                return oldItem.fixture.id == newItem.fixture.id
            }

            override fun areContentsTheSame(oldItem: FixtureItem, newItem: FixtureItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

}