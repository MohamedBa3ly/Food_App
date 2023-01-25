package com.example.foodapp.ui.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.model.ProfileData
import com.example.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    var profileData: MutableLiveData<ProfileData> = MutableLiveData()

    //Fun to save profile data by Data Store :
    fun saveData(context:Context,name:String, address: String, phone: String){
        viewModelScope.launch {
            repository.saveData(context,
                ProfileData(
                    name = name,
                    address = address,
                    phone = phone
                )
            )
        }
    }

    //Fun to read profile data by Data Store :
    fun readData(context:Context){
        viewModelScope.launch {
            repository.readData(context).collect{
                profileData.postValue(it)
            }
        }
    }


}