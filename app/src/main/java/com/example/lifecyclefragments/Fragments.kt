package com.example.lifecyclefragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager

class Fragments : AppCompatActivity() {

    var msg2: String = "Fragment lifecycle"
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    var count = 0

    private val fragmentManager = supportFragmentManager //create a fragment manager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)
        Log.d(msg2, "onCreate invoked")

        btn1 = findViewById(R.id.addFragmentBtn)
        btn2 = findViewById(R.id.removeFragmentBtn)

//        button is set to add fragments to backstack

        btn1.setOnClickListener {
            count = fragmentManager.backStackEntryCount + 1
            fragmentManager.beginTransaction().apply {
                val fragment = SampleFragment.newInstance(count) // create an instance of the fragment you want to stack
                add(R.id.fragmentContainer, fragment) //integrate the fragment to the view
                addToBackStack(null)
                commit()
            }

        }
// button removes fragment

        btn2.setOnClickListener {
            fragmentManager.popBackStack()
        }
    }

}