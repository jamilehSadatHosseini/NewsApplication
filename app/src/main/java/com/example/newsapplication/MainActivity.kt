package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.newsapplication.domain.useCases.AppEntryUseCases
import com.example.newsapplication.presentation.onbording.OnBoardingViewModel
import com.example.newsapplication.presentation.onbording.OnBoardingScreen
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("Test", it.toString())
            }
        }
        setContent {
            NewsApplicationTheme {

                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val viewModel:OnBoardingViewModel= hiltViewModel()
                    OnBoardingScreen(viewModel::onEvent)
                }

            }
        }
    }
}
