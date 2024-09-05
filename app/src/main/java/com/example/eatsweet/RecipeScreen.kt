package com.example.eatsweet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewState:MainViewModel.RecipeState,
                 navigateToDetail: (Category)-> Unit){



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        when{
            viewState.loading->
                {
                    //here i want to use circularProgressor
                    Text(text = "Loading>>>>", textAlign = TextAlign.Center, fontSize = 24.sp)
            }
            viewState.error != null ->{
                Text(text = "Error Occurred")

            }
            else->{
                // display categories
                CategoryScreen(categories = viewState.list, navigateToDetail)
            }
        }

    }

}

@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category)-> Unit){
    LazyVerticalGrid(GridCells.Fixed(2),modifier = Modifier.fillMaxSize()){
        items(categories){
           category->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}
// How each Item looks like
@Composable
fun CategoryItem(category: Category, navigateToDetail: (Category)-> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}


