package com.example.lifecyclefragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_PARAM = "param"

//  Fragment class is created

class SampleFragment : Fragment() {

    private var param: Int? = null
    lateinit var fragmentText: TextView

    var msg: String = "Fragment lifecycle"

//    fragment is instantiated

    companion object {
        fun newInstance(param: Int) = SampleFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM, param)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getInt(ARG_PARAM)
            Log.d(msg, "onCreate invoked")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_sample, container, false)
        fragmentText = view.findViewById(R.id.fragmentTextView)
        fragmentText.text = getString(R.string.fragmentText, param)
        return view
    }
}