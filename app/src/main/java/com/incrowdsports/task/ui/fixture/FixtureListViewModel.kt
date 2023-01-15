package com.incrowdsports.task.ui.fixture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incrowdsports.task.data.FixtureService
import com.incrowdsports.task.data.models.Fixture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FixtureListViewModel(private val service: FixtureService) : ViewModel() {

    val fixtureList = MutableStateFlow<List<Fixture>>(emptyList())

    fun loadData(compId: Int, season: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val data = service.getFixtureList(compId = compId, season = season, size = 10).data
            fixtureList.value = data
        }
    }

}