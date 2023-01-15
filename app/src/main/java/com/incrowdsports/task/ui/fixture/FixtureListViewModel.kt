package com.incrowdsports.task.ui.fixture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.NetworkResponse
import com.incrowdsports.task.repository.FixtureRepository
import com.incrowdsports.task.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixtureListViewModel @Inject constructor(
    private val repository: FixtureRepository
) : ViewModel() {

    private var _fixtureList = MutableStateFlow<List<Fixture>>(emptyList())
    val fixtureList: StateFlow<List<Fixture>> = _fixtureList

    fun loadData(compId: Int = Constants.COMP_ID, season: Int = Constants.SEASON) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getFixtureList(compId = compId, season = season, size = 10).catch {
                it.printStackTrace()
            }.collect { response ->
                when (response) {
                    is NetworkResponse.Success -> {
                        _fixtureList.value = response.data
                    }
                    is NetworkResponse.Loading -> {
                        // TODO Implement Loading UI State
                    }
                    is NetworkResponse.Failure -> {
                        // TODO Implement Error UI State
                    }
                }
            }
        }
    }

}