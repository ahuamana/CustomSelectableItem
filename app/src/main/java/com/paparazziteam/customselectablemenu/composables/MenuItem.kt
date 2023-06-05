package com.paparazziteam.customselectablemenu.composables

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paparazziteam.customselectablemenu.domain.CategoryForm

@Composable
fun MenuItem(
    item: CategoryForm,
    onClickCard: (CategoryForm) -> Unit,
) {
    //we add ripple when click on card
    val interactionSource = remember { MutableInteractionSource()}
    Card(
        border = if(!item.isSelected) BorderStroke(1.dp, Color(0xFFF9BC1F)) else null,
        modifier = Modifier
            .widthIn(min = 122.dp)
            .padding(end = 10.dp)
            .clickable(
                onClick = { onClickCard(item) },
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true, radius = 24.dp, color = Color.Gray)
            ),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(item.isSelected) Color(0xFFF9BC1F) else Color.White,
            contentColor = if(item.isSelected) Color.White else Color(0xFFF9BC1F)
        )
    ){
        Box(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)){
            Text(
                fontWeight = FontWeight.SemiBold,
                text = item.name,
                modifier = Modifier
                    .padding(top = 11.dp, bottom = 11.dp, start = 22.dp, end = 22.dp)
            )
        }

    }
}

@Preview
@Composable
fun MenuItemUnselectedPrev() {
    MenuItem(
        item = CategoryForm(
            id = 1,
            name = "Category 1",
            isSelected = false
        ),
        onClickCard = {}
    )
}

@Preview
@Composable
fun MenuItemSelectablePrev() {
    MenuItem(
        item = CategoryForm(
            id = 1,
            name = "Category 1",
            isSelected = true
        ),
        onClickCard = {}
    )
}