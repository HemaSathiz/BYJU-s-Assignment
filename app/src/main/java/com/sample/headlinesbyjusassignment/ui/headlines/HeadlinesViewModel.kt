package com.sample.headlinesbyjusassignment.ui.headlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.headlinesbyjusassignment.data.HeadlinesRepository
import com.sample.headlinesbyjusassignment.model.HeadlinesResponse
import com.sample.headlinesbyjusassignment.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel

class HeadlinesViewModel @Inject constructor(private val headlinesRepository: HeadlinesRepository) :
    ViewModel() {

    private val headlines = MutableLiveData<Result<HeadlinesResponse>>()

    val headlinesList = headlines

    init {
        fetchHeadlines()
    }

    @InternalCoroutinesApi
    private fun fetchHeadlines() {
        viewModelScope.launch {
            headlinesRepository.fetchHeadlines().collect {
                headlinesList.value = it
            }
        }
    }
}
