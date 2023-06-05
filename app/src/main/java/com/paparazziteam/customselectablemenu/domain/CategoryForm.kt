package com.paparazziteam.customselectablemenu.domain

data class CategoryForm(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)
