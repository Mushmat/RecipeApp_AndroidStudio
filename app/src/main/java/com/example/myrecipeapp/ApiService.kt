package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build() //This establishes a connection to the BaseURL and does it in such a way that we have the information as Kotlin Objects.

    val recipeService = retrofit.create(ApiService::class.java) //We want a service that gets the categories.php file that then converts to JSON object for our use.
//We will create an interface
interface ApiService {
    //This will have one function SUSPEND
    @GET("categories.php") //The APIService should go to categories.php to fetch for the data
    suspend fun getCategories():CategoriesResponse //This is a part of the coroutine API that provide a way to manage concurrency of tasks.
}