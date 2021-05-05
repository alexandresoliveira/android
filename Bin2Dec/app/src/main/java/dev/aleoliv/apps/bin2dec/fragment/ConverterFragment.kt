package dev.aleoliv.apps.bin2dec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.aleoliv.apps.bin2dec.R
import dev.aleoliv.apps.bin2dec.viewmodel.ConverterViewModel

class ConverterFragment : Fragment() {

    private lateinit var viewModel: ConverterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ConverterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_converter, container, false)
        val numberTextView: TextView = root.findViewById(R.id.textViewNumber)
        viewModel.number.observe(viewLifecycleOwner, Observer {
            numberTextView.text = it
        })
        return root
    }
}