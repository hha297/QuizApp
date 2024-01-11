package com.example.quizzapp.repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizzapp.model.Question
import com.example.quizzapp.model.QuestionsList
import com.example.quizzapp.retrofit.QuestionsAPI;
import com.example.quizzapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {
    var questionsAPI: QuestionsAPI

    init {
        questionsAPI = RetrofitInstance().getRetrofitInstance().create(QuestionsAPI::class.java)
    }

    fun getQuestionsFromAPI():LiveData<QuestionsList> {
        var data = MutableLiveData<QuestionsList>()

        var quesionList: QuestionsList

        GlobalScope.launch( Dispatchers.IO) {
            //Return the Response<QuestionList>
            val response = questionsAPI.getQuestions()

            if (response != null) {
                quesionList = response.body()!!

                data.postValue(quesionList)
                Log.i("TAGY", "" + data.value)

            }

        }

        return data;

    }


}