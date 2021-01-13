package com.example.lifecyclefragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
//the activity class is been created and it extends a parent class

class MainActivity : AppCompatActivity() {

    var msg: String = "LifeCycle Events"

    lateinit var textView : TextView
    lateinit var textView2 : TextView
    lateinit var btn : Button

    val handler : Handler = Handler()

    var count = 0

//    the view is being set in the onCreate as the activity is oly created during its lifecycle
/*  the log.d is added to all the callbacks in the lifecycle in order to monitor
    how each callbacks are invoked.
 */
//    handler is added in order to allow communication between the UI and background thread.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(msg, "onCreate invoked")
        handler.postDelayed({textView.text = "onCreate"}, 1000)

//     TextView and button is initialized so that it can manipulated later
        textView = findViewById(R.id.text2)
        textView2 = findViewById(R.id.text1)

        textView.text = "onCreate"

        btn = findViewById(R.id.btn_intent)

//     button is set to setOnClickListener in order to specif the button it would perform
/*     the button was set to have the intent so as to make navigation from the first activity
        to the other, it links both activity together
 */
        btn.setOnClickListener {
            val intent = Intent(this,Fragments::class.java)
            startActivity(intent)
        }

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY, 0)
        }

        myConfig()
    }
    @SuppressLint("SetTextI18n")
     fun myConfig() {
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            textView2.text = "Portrait: $count"
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            textView2.text = "Landscape: $count"
        }
        count += 1
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY, count)
        super.onSaveInstanceState(outState)
    }

//    the other callbacks in the activity lifecycle

    override fun onStart() {
        super.onStart()
        handler.postDelayed({textView.text = "onStart"}, 1000)

    }

    override fun onResume() {
        super.onResume()
        Log.d(msg, "onResume invoked")

        handler.postDelayed({textView.text = "onResume"}, 2000)
    }

    override fun onPause() {
        super.onPause()
        Log.d(msg, "onPause invoked")
        textView.text = "onPause"
    }

    override fun onStop() {
        super.onStop()
        Log.d(msg, "onStop invoked")
        textView.text = "onStop"
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(msg, "onRestart invoked")
        textView.text = "onRestart"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(msg, "onDestroy invoked")
        textView.text = "onDestroy"
    }

    fun print(msg: String) {
        Log.d("Activity State ", msg)
    }

    companion object {
        const val KEY = "key"
    }
}