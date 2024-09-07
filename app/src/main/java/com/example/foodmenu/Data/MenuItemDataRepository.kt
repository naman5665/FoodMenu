package com.example.foodmenu.Data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MenuItemDataRepository {

    fun getMenuItemList(context: Context): List<MenuItemDataModel> {
        val jsonString = readJsonFromAssets(context, "data.json")
        return parseJsonToModel(jsonString)
    }

    private fun parseJsonToModel(jsonString: String): List<MenuItemDataModel> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<MenuItemDataModel>>() {}.type)
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}