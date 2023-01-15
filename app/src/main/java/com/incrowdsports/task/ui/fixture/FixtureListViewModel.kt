package com.incrowdsports.task.ui.fixture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incrowdsports.task.data.models.Fixture
import com.incrowdsports.task.data.models.ServiceResult
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

    private var pageNumber = 0

    fun loadData() {
        updatePageNumber()
        viewModelScope.launch(Dispatchers.Main) {
            repository.getFixtureList(
                compId = Constants.COMP_ID,
                season = Constants.SEASON,
                size = Constants.PAGE_SIZE,
                pageNumber = pageNumber
            ).catch {
                it.printStackTrace()
            }.collect { response ->
                when (response) {
                    is ServiceResult.Success -> {
                        response.data?.let {
                            _fixtureList.value = it
                        }
                    }
                    is ServiceResult.Loading -> {
                        // TODO Implement Loading UI State
                    }
                    is ServiceResult.Failure -> {
                        // TODO Implement Error UI State
                        Exception(response.message).printStackTrace()
                    }
                }
            }
        }
    }

    private fun updatePageNumber() {
        if (_fixtureList.value.isNotEmpty()) {
            pageNumber++
        } else {
            pageNumber = 0
        }
    }

}