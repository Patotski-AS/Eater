package com.pas.eater.presentation.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.pas.eater.databinding.FragmentBasketBinding
import com.pas.eater.presentation.basket.adapter.BasketAdapter
import com.pas.eater.presentation.basket.adapter.BasketClickListener
import com.pas.eater.presentation.basket.adapter.BasketEaterItem
import com.pas.eater.presentation.util.toPrice
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment: Fragment(),
    BasketClickListener {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BasketViewModel by viewModels()
    private val adapter = BasketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnClickListener(this)

        binding.rvBasket.adapter = adapter

        binding.button.setOnClickListener {
            viewModel.payOrder()
        }

        lifecycleScope.launch {
            viewModel.basket.collectLatest {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            viewModel.prise.collectLatest {
                binding.button.text = "Оплатить ${it.toPrice()} "
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickPlus(item: BasketEaterItem) {
        viewModel.updateBasketItem(item.copy(count = item.count + 1))
    }

    override fun onClickMinus(item: BasketEaterItem) {
        if(item.count == 1) viewModel.deleteItem(item.id)
        else viewModel.updateBasketItem(item.copy(count = item.count - 1))
    }
}
