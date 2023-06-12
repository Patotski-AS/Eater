package com.pas.eater.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pas.eater.domain.models.BasketItem
import com.pas.eater.domain.models.Dish
import com.pas.eater.domain.use_case.GetDishesUseCase
import com.pas.eater.domain.use_case.InsertBasketItemUseCase
import com.pas.eater.domain.use_case.UpdateDishesUseCase
import com.pas.eater.presentation.category.adapter.DishEaterItem
import com.pas.eater.presentation.category.adapter.TagEaterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getDishesUseCase: GetDishesUseCase,
    private val updateDishesUseCase: UpdateDishesUseCase,
    private val addBasketItem: InsertBasketItemUseCase,
): ViewModel() {
    private val _tag = MutableStateFlow<String?>(null)

    private val _tags = MutableStateFlow<List<TagEaterItem>>(emptyList())
    val tags: StateFlow<List<TagEaterItem>> = _tags.asStateFlow()

    val dishes =
        getDishesUseCase.invoke()
            .map {it.mapToDishItems()}
            .onEach {_tags.value = it.mapToTagItems()}
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
            .combine(_tag) {list, tag-> if(tag != null) list.filter {it.tegs.contains(tag)} else list}

    private val _dishDetail: MutableStateFlow<DishEaterItem?> = MutableStateFlow(null)
    val dishDetail: StateFlow<DishEaterItem?> = _dishDetail.asStateFlow()


    init {
        viewModelScope.launch {
            updateDishesUseCase.invoke().collect {}
        }
    }

    fun setDishDetail(dish: DishEaterItem?) {
        _dishDetail.value = dish
    }

    fun setTag(tag: String) {
        _tag.value = tag
        _tags.value = _tags.value.map {it.copy(onSelect = it.tag == tag)}
    }

    fun addToBasket() {
        viewModelScope.launch {
            dishDetail.value?.let {dish->
                addBasketItem.invoke(
                    BasketItem(
                        id = dish.id,
                        imageUrl = dish.imageUrl,
                        name = dish.name,
                        price = dish.price,
                        weight = dish.weight,
                        count = 1
                    )
                )
            }
        }
        setDishDetail(null)
    }
}

private fun List<DishEaterItem>.mapToTagItems() = this.map {it.tegs}
    .flatten()
    .distinct()
    .mapIndexed {index, string-> TagEaterItem(index, string, false)}

private fun List<Dish>.mapToDishItems() = this.map {
    DishEaterItem(
        id = it.id,
        description = it.description,
        imageUrl = it.imageUrl,
        name = it.name,
        price = it.price,
        tegs = it.tegs,
        weight = it.weight
    )
}