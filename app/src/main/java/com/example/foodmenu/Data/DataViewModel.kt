package com.example.foodmenu.Data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataViewModel: ViewModel(){

    private val repository = MenuItemDataRepository()

    private val _menuItemDataList = MutableLiveData<List<MenuItemDataModel>>()
    val menuItemDataList: LiveData<List<MenuItemDataModel>>
        get() = _menuItemDataList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getMenuListItemData(context: Context){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val menuListItemsFromRepo = repository.getMenuItemList(context)
                _menuItemDataList.value = menuListItemsFromRepo
                _isLoading.value = false
            }catch (e: Exception) {
                // Handle error
            }
        }
    }
}