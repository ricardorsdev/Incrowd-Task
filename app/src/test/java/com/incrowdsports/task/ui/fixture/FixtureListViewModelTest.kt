package com.incrowdsports.task.ui.fixture

import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.Player
import com.incrowdsports.task.data.models.ServiceResult
import com.incrowdsports.task.data.models.Stats
import com.incrowdsports.task.data.models.Team
import com.incrowdsports.task.data.models.Venue
import com.incrowdsports.task.repository.FixtureRepository
import com.incrowdsports.task.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FixtureListViewModelTest {

    private val mockFixtureList = listOf(
        Fixture(
            id = "",
            feedMatchId = 0,
            competition = "",
            period = "",
            season = "",
            date = "",
            homeTeam = Team(
                id = "",
                name = "",
                score = "",
                players = listOf(
                    Player(
                        name = "",
                        shirtNumber = 0,
                        position = "",
                        playerStats = Stats(
                            goals = null
                        )
                    )
                )
            ),
            awayTeam = Team(
                id = "",
                name = "",
                score = "",
                players = listOf(
                    Player(
                        name = "",
                        shirtNumber = 0,
                        position = "",
                        playerStats = Stats(
                            goals = null
                        )
                    )
                )
            ),
            venue = Venue(
                id = "",
                name = "",
            ),
        )
    )

    private val repository = mock<FixtureRepository>()
    private lateinit var viewModel: FixtureListViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = FixtureListViewModel(repository)
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

        whenever(
            repository.getFixtureList(
                Constants.COMP_ID,
                Constants.SEASON,
                Constants.PAGE_SIZE,
                0
            )
        ).thenReturn(
            flowOf(ServiceResult.Success(mockFixtureList))
        )

        val job = launch(dispatcher) {
            viewModel.fixtureList.toList(actual)
        }

        viewModel.loadData()
        assertEquals(actual[1], expected[1])
        job.cancel()
    }

    @Test
    fun `check when service returns fixture details then state flow is updated`() = runTest {
        val expected = mockFixtureList[0]
        val actual = mutableListOf<Fixture?>()

        whenever(repository.getFixtureDetails(0)).thenReturn(
            flowOf(ServiceResult.Success(mockFixtureList[0]))
        )

        val job = launch(dispatcher) {
            viewModel.fixtureDetails.toList(actual)
        }

        viewModel.getFixtureDetails(0)
        assertEquals(actual[1], expected)
        job.cancel()
    }

}