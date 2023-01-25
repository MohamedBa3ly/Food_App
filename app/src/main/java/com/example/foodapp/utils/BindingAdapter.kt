package com.example.foodapp.utils


import androidx.databinding.BindingAdapter
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

//Just for learning : (not used)

@BindingAdapter("app:showImage")
fun showImageMovie(imageView: ImageSlider, imageOne:Int, imageTwo:Int){
    var imageList = ArrayList<SlideModel>()
    imageList.add(SlideModel(imageOne,scaleType = ScaleTypes.CENTER_CROP))
    imageList.add(SlideModel(imageTwo,scaleType = ScaleTypes.CENTER_CROP))
imageView.setImageList(imageList)
}