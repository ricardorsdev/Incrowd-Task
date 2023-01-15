package com.incrowdsports.task.data.models

data class Fixture(
    val id: String,
    val feedMatchId: Long,
    val competition: String,
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
)

data class Venue(
    val id: String,
    val name: String,
)