package com.incrowdsports.task

import com.incrowdsports.task.data.FixtureService
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.NetworkResponse
import com.incrowdsports.task.data.models.Team
import com.incrowdsports.task.data.models.Venue
import com.incrowdsports.task.ui.fixture.FixtureListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FixtureListViewModelTest {

    private val mockFixtureList = listOf(
        Fixture(
            id = "",
            feedMatchId = 0,
            competition = "",
            period = "",
            date = "",
            homeTeam = Team(
                id = "",
                name = "",
                score = "",
            ),
            awayTeam = Team(
                id = "",
                name = "",
                score = "",
            ),
            venue = Venue(
                id = "",
                name = "",
            ),
        )
    )
    private val service = object : FixtureService {
        override suspend fun getFixtureList(compId: Int, season: Int, size: Int): NetworkResponse<List<Fixture>> {
            return NetworkResponse(data = mockFixtureList)
        }
    }
    private val viewModel = FixtureListViewModel(service = service)

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `check when service returns a list of fixtures then state flow is updated`() = runTest {
        val expected = listOf(
            emptyList(),
            mockFixtureList,
        )
        val actual = mutableListOf<List<Fixture>>()
        val job = launch(dispatcher) {
            viewModel.fixtureList.toList(actual)
        }
        job.cancel()
        assertEquals(expected, actual)
    }

}