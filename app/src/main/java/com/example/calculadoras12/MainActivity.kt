package com.example.calculadoras12

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.calculadoras12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el spinner con las operaciones
        setupSpinner()

        viewModel.result.observe(this, Observer { result ->
            binding.textViewResult.text = if (result.isNaN()) {
                "Error: División por cero"
            } else {
                "Resultado: ${if (result % 1.0 == 0.0) result.toInt() else result}"
            }
        })

        binding.buttonCalculate.setOnClickListener {
            val num1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
            val num2 = binding.editTextNumber2.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                binding.textViewResult.text = "Error: Ingresa números válidos"
                return@setOnClickListener
            }

            val operation = binding.spinnerOperations.selectedItem.toString()
            viewModel.calculate(operation, num1, num2)
        }
    }

    private fun setupSpinner() {
        val operations = arrayOf("+ Suma", "- Resta", "* Multiplicación", "/ División")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOperations.adapter = adapter
    }
}