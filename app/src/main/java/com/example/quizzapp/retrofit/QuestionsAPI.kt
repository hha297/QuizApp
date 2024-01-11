package com.example.quizzapp.retrofit

import com.example.quizzapp.model.Question
import com.example.quizzapp.model.QuestionsList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsAPI {
    @GET("question_api.php")
    suspend fun getQuestions(): Response<QuestionsList>
}