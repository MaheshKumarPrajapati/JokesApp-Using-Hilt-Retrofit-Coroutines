package com.example.jokesapp.other

import android.app.Activity
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


const val SAVED_JOKES="saved_jokes"
class Utils {

    public fun saveJokesToPrefrence(context: Context,jokesList:List<String>){
        val jokesJson:String= Gson().toJson(jokesList)
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(SAVED_JOKES, jokesJson)
            commit()
        }
    }

    public fun getJokesFromPrefrence(context: Context): List<String>{
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
        val jokesJson = sharedPref.getString(SAVED_JOKES,null)
        return if(jokesJson!=null) {
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            return Gson().fromJson(jokesJson, type)
        }else {
            ArrayList<String>()
        }
    }
}