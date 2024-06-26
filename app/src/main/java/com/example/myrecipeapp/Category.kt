package com.example.myrecipeapp

data class Category(
    val idCategory:String,
    val strCategory:String,
    val strCategoryThumb:String,
    val strCategoryDescription:String
)

data class CategoriesResponse(val categories: List<Category>) //The 'categories' is a list in TheMealDB which has the list of all the objects.
