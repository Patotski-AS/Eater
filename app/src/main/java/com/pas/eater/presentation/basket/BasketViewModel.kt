package com.pas.eater.presentation.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pas.eater.domain.models.BasketItem
import com.pas.eater.domain.use_case.DeleteBasketItemUseCase
import com.pas.eater.domain.use_case.DeleteBasketUseCase
import com.pas.eater.domain.use_case.GetBasketUseCase
import com.pas.eater.domain.use_case.InsertBasketItemUseCase
import com.pas.eater.presentation.basket.adapter.BasketEaterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val deleteItem: DeleteBasketItemUseCase,
    private val insertItem: InsertBasketItemUseCase,
    private val getBasket: GetBasketUseCase,
    private val deleteBasket: DeleteBasketUseCase,
): ViewModel() {

    private val _prise = MutableStateFlow(0)
    val prise: StateFlow<Int> = _prise.asStateFlow()

    val basket =
        getBasket.invoke()
            .map {it.mapToBasketEaterItems()}
            .onEach {list-> _prise.value = list.sumOf {it.price * it.count}}
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun deleteItem(id: Int) {
        viewModelScope.launch {deleteItem.invoke(id)}
    }

    fun updateBasketItem(item: BasketEaterItem) {
        viewModelScope.launch(Dispatchers.IO) {
            insertItem.invoke(item.mapToBasketItem())
        }
    }

    fun payOrder() {
        viewModelScope.launch(Dispatchers.IO) {deleteBasket.invoke()}
    }
}

private fun List<BasketItem>.mapToBasketEaterItems() = this.map {
    BasketEaterItem(
        id = it.id,
        imageUrl = it.imageUrl,
        name = it.name,
        price = it.price,
        weight = it.weight,
        count = it.count
    )
}

private fun BasketEaterItem.mapToBasketItem() = BasketItem(
    id = this.id,
    imageUrl = this.imageUrl,
    name = this.name,
    price = this.price,
    weight = this.weight,
    count = this.count
)

