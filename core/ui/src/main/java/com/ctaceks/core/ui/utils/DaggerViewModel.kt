package com.ctaceks.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Provides view model from current dagger UiComponent
 */
@Composable
inline fun <reified T : ViewModel> daggerViewModel(
   key: String? = null,
   crossinline viewModelInstanceCreator: () -> T
): T =
   androidx.lifecycle.viewmodel.compose.viewModel(
       modelClass = T::class.java,
       key = key,
       factory = object : ViewModelProvider.Factory {
           override fun <T : ViewModel> create(modelClass: Class<T>): T {
               return viewModelInstanceCreator() as T
           }
       }
   )