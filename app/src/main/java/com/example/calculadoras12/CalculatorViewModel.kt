package com.example.calculadoras12
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double> get() = _result

    fun calculate(operation: String, num1: Double, num2: Double) {
        _result.value = when {
            operation.contains("+") -> num1 + num2
            operation.contains("-") -> num1 - num2
            operation.contains("*") -> num1 * num2
            operation.contains("/") -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }
    }
}