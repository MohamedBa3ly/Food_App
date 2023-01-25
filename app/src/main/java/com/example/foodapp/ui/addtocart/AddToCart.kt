package com.example.foodapp.ui.addtocart


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentAddToCartBinding
import com.example.foodapp.room.entity.CartData
import com.example.foodapp.ui.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddToCart : Fragment() {
    private lateinit var binding: FragmentAddToCartBinding
    private lateinit var addToCartViewModel: CartViewModel
    private var size = "small"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_to_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()

    }

    //Initialize :
    private fun initialize() {

        binding.lifecycleOwner = this
        addToCartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        val selected = arguments?.let { AddToCartArgs.fromBundle(it).selectedMeal }
        binding.meal = selected

        //Set image in slider:
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel( selected?.image,scaleType = ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel( selected?.image2,scaleType = ScaleTypes.CENTER_CROP))
        binding.imageSlider.setImageList(imageList)

        //Add data that user want to eat in room data base :
        val name = selected?.name.toString()
        val price = selected?.price
        val priceLarge = selected?.priceLarge
        var number = binding.numberOfMeal.text.toString().toInt()


        binding.btnMedium.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#B65E31"))
            binding.btnLarge.setTextColor(Color.WHITE)
            binding.btnLarge.setBackgroundColor(Color.parseColor("#EE8D5C"))
            size = binding.btnMedium.text.toString()
            binding.priceAdd.text = price.toString()
        }
        binding.btnLarge.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#B65E31"))
            binding.btnMedium.setTextColor(Color.WHITE)
            binding.btnMedium.setBackgroundColor(Color.parseColor("#EE8D5C"))
            size = binding.btnLarge.text.toString()
            binding.priceAdd.text = priceLarge.toString()
        }

        binding.imageAdd.setOnClickListener {
            number += 1
            binding.numberOfMeal.text = number.toString()

        }

        binding.imageMinus.setOnClickListener {
            if (number >1){
                number -= 1
                binding.numberOfMeal.text = number.toString()
            }
        }


        binding.btnAddToCart.setOnClickListener {

            if (size=="small"){
                Toast.makeText(requireContext(),"Choose your size",Toast.LENGTH_SHORT).show()
            }else {
                if (size == binding.btnMedium.text.toString()){
                    val priceFinal = price!!.times(number)
                    addToCartViewModel.addCart(CartData(0,name,size,priceFinal,number))
                    findNavController().navigate(AddToCartDirections.actionAddToCartToCart2())
                }else if (size== binding.btnLarge.text.toString()){
                    val priceFinal = priceLarge!!.times(number)
                    addToCartViewModel.addCart(CartData(0,name,size,priceFinal,number))
                    findNavController().navigate(AddToCartDirections.actionAddToCartToCart2())
                }
            }
        }
    }






}