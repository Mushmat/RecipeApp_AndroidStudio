package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categorieState = mutableStateOf(RecipeState()) //Private Variable
    val categoriesState: State<RecipeState> = _categorieState    //Public variable accessed by all classes //User interface updated when the recipe state changes

    init {
        fetchCategories() //Whenever MainViewModel.kt is used, execute the fetchCategories() function to get the categories.
    }

    private fun fetchCategories(){
        //A viewModelScope provides lounge for the suspend functions to execute successfully. It is a coroutine.
        viewModelScope.launch {
            try{
                val response = recipeService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories, //Store the response of the api to the list
                    loading = false, //It is no more loading
                    error = null //there was no error
                )

            }catch(e: Exception){
                _categorieState.value = _categorieState.value.copy(
                loading = false,
                error = "Error fetching Categories ${e.message}" //if there is an error, display this.
                )
            }
        }
    }
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null //String is nullable i.e. it can be a null value. (Null-able)
    )


}
