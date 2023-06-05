package com.paparazziteam.customselectablemenu.composables.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paparazziteam.customselectablemenu.domain.CategoryForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ViewModelHome @Inject constructor(

) : ViewModel(){

    private val categoriesList = mutableListOf<CategoryForm>()

    private val _statesCategories = MutableStateFlow<StateCategories>(StateCategories.HideLoading)
    val statesCategories = _statesCategories.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000,1),
        initialValue = StateCategories.HideLoading
    )

    init {
        getCategoriesList()
    }

    private fun getCategoriesList() = viewModelScope.launch{
        //simulated flow data streams
        //show loading screen
        _statesCategories.value = StateCategories.ShowLoading
        //wait 2 minutes
        delay(2.seconds)
        //hide loading screen
        _statesCategories.value = StateCategories.HideLoading

        //show data sucess
        //save category list
        val list = createListCategories()
        categoriesList.addAll(list)
        //emit to the ui
        _statesCategories.value = StateCategories.Success(list)

    }

    fun submitCategoryClicked(category:CategoryForm) = viewModelScope.launch {
        //create map with category selected
        val mapList = categoriesList.map {
            if(it.id == category.id){
                it.copy(isSelected = true)
            }else  {
                it
            }
        }

        //update new list with selected id == true
        _statesCategories.value = StateCategories.Success(mapList)
    }


    ///we gonna create a mock for list of categories
    fun createListCategories():List<CategoryForm>{
        return listOf(
            CategoryForm(
                id = 1,
                name = "Veg Dinner",
                isSelected = false
            ),
            CategoryForm(
                id = 2,
                name = "Non Veg Dinner",
                isSelected = false
            ),

            CategoryForm(
                id = 3,
                name = "Chinese Dinner",
                isSelected = false
            ),
            CategoryForm(
                id = 4,
                name = "Italian Dinner",
                isSelected = false
            )


        )
    }

}



sealed class StateCategories(){
    object ShowLoading : StateCategories()
    object HideLoading : StateCategories()
    data class Success(val list:List<CategoryForm>) : StateCategories()
    data class OnError(val message:String) : StateCategories()
}