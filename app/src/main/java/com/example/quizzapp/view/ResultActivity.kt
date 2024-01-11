package com.example.quizzapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.databinding.DataBindingUtil
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding:ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        binding.textAnswer.text = "Your score is: " + MainActivity.result + "/" + MainActivity.totalQuestion

        binding.buttonBack.setOnClickListener(){
            var intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }





    }
}