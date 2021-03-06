package com.example.mvvptest1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val TAG = "MainActivity"
    }

    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        myNumberViewModel.currentValue.observe(this, Observer {
            Log.e(TAG, "onCreate: 라이브 데이터 값 변경 $it")
            number_textview.text = it.toString()

        })

        plus_btn.setOnClickListener(this)
        minus_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val userInput = userinput_edittext.text.toString().toInt()

        when(v){
            plus_btn ->
                myNumberViewModel.updateValue(ActionType.PLUS, userInput)
            minus_btn ->
                myNumberViewModel.updateValue(ActionType.MINUS, userInput)
        }
    }
}