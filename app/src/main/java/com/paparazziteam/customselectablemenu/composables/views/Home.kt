package com.paparazziteam.customselectablemenu.composables.views

import android.net.wifi.hotspot2.pps.HomeSp
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.paparazziteam.customselectablemenu.R
import com.paparazziteam.customselectablemenu.composables.screens.HomeScreen

@Composable
fun Home() {
    val homeViewModel = hiltViewModel<ViewModelHome>()
    val states = homeViewModel.statesCategories.collectAsStateWithLifecycle()

    when(val currentState = states.value){
        StateCategories.HideLoading -> {
            //ScreenLoading()
        }
        is StateCategories.OnError -> {
            //ScreenError()
        }
        StateCategories.ShowLoading -> {
            //ScreenLoading()
            //Just we create a image for simulate loading // you can change using lottie loading or another custom view
            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Loading")
        }
        is StateCategories.Success -> {
            HomeScreen(
                onClickedCardItem = {
                    //handled click from cardItem
                    homeViewModel.submitCategoryClicked(it)
                },
                list = currentState.list
            )
        }
    }
}


@Preview
@Composable
fun HomePrev() {
    //to test in preview
    Home()
}