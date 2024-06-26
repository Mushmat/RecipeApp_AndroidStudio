package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewstate:MainViewModel.RecipeState,
                 navigateToDetail:(Category)->Unit
    ){

    //Now we will make the UI
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewstate.error != null -> {
                Text(text = "ERROR OCCURED")
            }

            else->{
                CategoryScreen(categories = viewstate.list,navigateToDetail)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetail:(Category)->Unit
    ){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categories){//The list contains a bunch of category items
            category ->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}

//How each item looks like
@Composable
fun CategoryItem(category: Category,
                 navigateToDetail:(Category)->Unit //For navigation to description
                 ){
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigateToDetail(category) }, //THE ENTIRE ITEM IS CLICKABLE FOR ON-CLICK ACTIONS
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb), //This is the content of the image
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f) //Just be as wide as it is high.
        )


        Text( //It is the text that displays the name of the category beneath the picture of the category. Eg: the name 'PASTA' in black bold.
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}