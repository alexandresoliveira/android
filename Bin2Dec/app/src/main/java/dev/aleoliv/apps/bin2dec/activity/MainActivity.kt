package dev.aleoliv.apps.bin2dec.activity

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.aleoliv.apps.bin2dec.R
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var imageViewConverterDirecional: ImageView
    private lateinit var textViewNumber: TextView
    private lateinit var buttonConverter: Button
    private lateinit var textViewResult: TextView

    private var converterStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewConverterDirecional = findViewById(R.id.imageViewConverterDirecional)
        textViewNumber = findViewById(R.id.textViewNumber)
        buttonConverter = findViewById(R.id.buttonConverter)
        textViewResult = findViewById(R.id.textViewResult)

        imageViewConverterDirecional
            .setOnClickListener { image ->
                if (converterStatus) {
                    image.animate().apply {
                        duration = 1000
                        rotationY(360f)
                    }.start()

                    textViewNumber.hint = getString(R.string.text_view_binary_hint)
                } else {
                    image.animate().apply {
                        duration = 1000
                        rotationY(180f)
                    }.start()

                    textViewNumber.hint = getString(R.string.text_view_number_hint)
                }

                converterStatus = !converterStatus
            }

        textViewNumber.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    converter()
                    true
                }
                else -> false
            }
        }

        buttonConverter.setOnClickListener { button ->
            converter()
        }
    }

    private fun converter() {
        var number = textViewNumber.text
        if (number.isNullOrBlank()) {
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show()
            return
        }

        textViewResult.text = if (!converterStatus) toDecimal(textViewNumber.text) else toBinary(textViewNumber.text)
    }

    private fun toDecimal(binaryNumber: CharSequence) : String {
        var sum = 0
        binaryNumber.reversed().forEachIndexed {
            k, v -> sum += v.toString().toInt() * pow(2.0, k.toDouble()).toInt()
        }
        return sum.toString()
    }

    private fun toBinary(decimalNumber: CharSequence) : String {
        return Integer.toBinaryString(Integer.valueOf(decimalNumber.toString()))
    }
}