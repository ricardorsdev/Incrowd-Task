package com.incrowdsports.task.ui.fixture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incrowdsports.task.data.models.Player
import com.incrowdsports.task.databinding.TeamPlayerLayoutBinding

class TeamPlayersAdapter(private val playersList: List<Player>) :
    RecyclerView.Adapter<TeamPlayersAdapter.TeamPlayersViewHolder>() {

    class TeamPlayersViewHolder(
        private val binding: TeamPlayerLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.apply {
                playerName.text = player.name
                playerPosition.text = player.position
                goalCounter.text = (player.playerStats.goals ?: 0).toString()
                shirtNumber.text = player.shirtNumber.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPlayersViewHolder {
        val binding =
            TeamPlayerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamPlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamPlayersViewHolder, position: Int) {
        holder.bind(playersList[position])
    }

    override fun getItemCount() = playersList.size
}