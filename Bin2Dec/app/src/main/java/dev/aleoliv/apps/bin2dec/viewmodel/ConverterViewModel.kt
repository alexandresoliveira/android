package dev.aleoliv.apps.bin2dec.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel() : ViewModel() {
    private val _number = MutableLiveData<String>().apply {
        value = ""
    }
    val number: LiveData<String> = _number
}