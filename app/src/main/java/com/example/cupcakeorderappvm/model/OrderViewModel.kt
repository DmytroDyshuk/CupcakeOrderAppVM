package com.example.cupcakeorderappvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {

    private val _orderQuantity = MutableLiveData<Int>()
    var orderQuantity: LiveData<Int> = _orderQuantity

    private val _cupcakeFlavor = MutableLiveData<String>()
    var cupcakeFlavor: LiveData<String> = _cupcakeFlavor

    private val _date = MutableLiveData<String>()
    var date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    var price: LiveData<Double> = _price

    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int) {
        _orderQuantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _cupcakeFlavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    fun hasNoFlavorSet(): Boolean {
        return _cupcakeFlavor.value.isNullOrEmpty()
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Create a list of dates starting with the current date and the following 3 dates
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    private fun resetOrder() {
        _orderQuantity.value = 0
        _cupcakeFlavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

}