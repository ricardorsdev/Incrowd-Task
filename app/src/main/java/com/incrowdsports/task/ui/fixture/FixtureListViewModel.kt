package com.incrowdsports.task.ui.fixture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.repository.FixtureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixtureListViewModel @Inject constructor(
    private val repository: FixtureRepository
) : ViewModel() {

    val fixtureList = MutableStateFlow<List<Fixture>>(emptyList())

    fun loadData(compId: Int, season: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val data =
                repository.getFixtureList(compId = compId, season = season, size = 10).collect {

                }
        }
    }

}