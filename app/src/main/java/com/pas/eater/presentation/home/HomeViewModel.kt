package com.pas.eater.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pas.eater.domain.models.Category
import com.pas.eater.domain.use_case.GetCategoriesUseCase
import com.pas.eater.domain.use_case.UpdateCategoriesUseCase
import com.pas.eater.presentation.home.adapter.CategoryEaterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val updateCategories: UpdateCategoriesUseCase,
    private val getCategories: GetCategoriesUseCase,
): ViewModel() {

    val categories =
        getCategories.invoke()
            .map {it.mapToCategoryItems()}
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    init {
        viewModelScope.launch {
            updateCategories.invoke().collect {}
        }
    }
}

private fun List<Category>.mapToCategoryItems() = this.map {
    CategoryEaterItem(id = it.id, imageUrl = it.imageUrl, name = it.name)
}