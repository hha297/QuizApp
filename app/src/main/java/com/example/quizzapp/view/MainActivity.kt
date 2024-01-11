package com.example.quizzapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizzapp.R
import com.example.quizzapp.viewmodel.QuizViewModel
import com.example.quizzapp.databinding.ActivityMainBinding
import com.example.quizzapp.model.Question
import com.example.quizzapp.model.QuestionsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    lateinit var binding: ActivityMainBinding



    lateinit var  quizViewModel: QuizViewModel
    lateinit var questionsList: List<Question>

    companion object {
        var result = 0
        var totalQuestion = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Reset
        result = 0
        totalQuestion = 0


        //Get the response
        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        //Display question
        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {
                if (it.size > 0) {
                   questionsList = it
                   Log.i("TaGY", "This is first question: ${questionsList[0]}")

                    binding.apply {
                        textQuestion.text = "Question ${1}:\n\n${questionsList[0].question}"


                        radioButtonOption1.text = questionsList!![0].option1
                        radioButtonOption2.text = questionsList!![0].option2
                        radioButtonOption3.text = questionsList!![0].option3
                        radioButtonOption4.text = questionsList!![0].option4


                    }
                }
            })
        }
    //Add functionality to BN
        var i = 1
        binding.apply {
            buttonNext.setOnClickListener(View.OnClickListener {
                val selectedOption = radioGroupOptions?.checkedRadioButtonId
                if (selectedOption != -1) {
                    val radbutton = findViewById<View>(selectedOption!!) as RadioButton

                    questionsList.let {
                        if (i < it.size!!) {
                            //Get number of option
                            totalQuestion = it.size
                            //Check if it is correct
                            if (radbutton.text.toString().equals(it[i - 1].correct_option)) {
                                result++
                                txtResult?.text = "Correct Answer: $result"
                            }

                            //Display next question
                            textQuestion.text = "Question ${i+1}:\n\n${questionsList[i].question}"

                            radioButtonOption1.text = it[i].option1
                            radioButtonOption2.text = it[i].option2
                            radioButtonOption3.text = it[i].option3
                            radioButtonOption4.text = it[i].option4


                            //Check if it's last question
                            if(i == it.size!!.minus(1)){
                                buttonNext.text = "FINISH"
                            }

                            radioGroupOptions?.clearCheck()
                            i++
                        } else{
                            if (radbutton.text.toString().equals(it[i-1].correct_option)) {
                                result++
                                txtResult?.text= "Correct Answer: ${result}"
                            } else {

                            }

                            val intent = Intent(this@MainActivity, ResultActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                } else {

                    Toast.makeText(this@MainActivity, "Please select one option", Toast.LENGTH_LONG).show()
                }


            })
        }
    }
}


