package com.paparazziteam.customselectablemenu.composables.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paparazziteam.customselectablemenu.composables.MenuItem
import com.paparazziteam.customselectablemenu.domain.CategoryForm

@Composable
fun HomeScreen(
    onClickedCardItem: (CategoryForm) -> Unit,
    list: List<CategoryForm>
) {
    LazyRow(
        contentPadding = PaddingValues(10.dp)
    ){
        itemsIndexed(list) { index, item ->
            MenuItem(item = item, onClickCard = onClickedCardItem)
        }
    }

}


@Preview
@Composable
fun HomeSreenPrev() {
    HomeScreen(onClickedCardItem = {}, list = listOf(
        CategoryForm(
            id = 1,
            name = "Veg Dinner",
            isSelected = true
        ),
        CategoryForm(
            id = 2,
            name = "Non Veg Dinner",
            isSelected = false
        ),
    ))
}