package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.media.Image

class Pref(private val context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("pref_name", MODE_PRIVATE)


    fun isBoardingShow():Boolean{
        return pref.getBoolean(BOARDING_SHOW,false)
    }
    fun saveShowBoarding(isShow:Boolean){
        pref.edit().putBoolean(BOARDING_SHOW,isShow).apply()
    }
    fun savedText(name:String){
         pref.edit().putString( NAME_PROFILE ,name).apply()
    }
    fun getName(): String? {
        return pref.getString(NAME_PROFILE,"")
    }
    fun saveAge(age:String){
       pref.edit().putString(AGE_PROFILE,age).apply()
    }
    fun getAge(): String? {
        return pref.getString(AGE_PROFILE,"")
    }
    fun saveImage(image: String){
        pref.edit().putString(KEY_IMAGE_URL,image).apply()
    }
    fun getImage(): String? {
        return pref.getString(KEY_IMAGE_URL,"")

    }

    companion object{
        const val BOARDING_SHOW ="onBoardingShow"
        const val NAME_PROFILE="onName"
        const val  AGE_PROFILE= "onAge"
        const val KEY_IMAGE_URL = "Key_image_url"
    }
}