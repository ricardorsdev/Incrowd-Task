package com.incrowdsports.task.data.models

import com.google.gson.annotations.SerializedName

data class Fixture(
    val id: String,
    val feedMatchId: Long,
    val competition: String,
    val season: String,
    val period: String,
    val date: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val venue: Venue,
)

data class Team(
    val id: String,
    val name: String,
    val score: String,
    val players: List<Player>
)

data class Venue(
    val id: String,
    val name: String,
)

data class Player(
    @SerializedName("known")
    val name: String,
    val position: String,
    val shirtNumber: Int,
    val playerStats: Stats
)

data class Stats(
    val goals: Int?
)