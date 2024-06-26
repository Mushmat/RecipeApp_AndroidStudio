package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val idCategory:String,
    val strCategory:String,
    val strCategoryThumb:String,
    val strCategoryDescription:String
):Parcelable

data class CategoriesResponse(val categories: List<Category>) //The 'categories' is a list in TheMealDB which has the list of all the objects.
