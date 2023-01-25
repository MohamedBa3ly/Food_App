package com.example.foodapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.ui.cart.adapter.CartAdapter
import com.example.foodapp.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Cart : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private val adapter = CartAdapter()
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Initialize view model :
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        // add viewModel in adapter:
        adapter.setupViewModel(cartViewModel)

        //Set data in recycler view :
        setUpData()

        //Set delivery details :
        addDeliveryData()

        //To change delivery details :
        changeDeliveryData()

        //To make Your Order :
        binding.btnReadyToEat.setOnClickListener {
            if(binding.addressCart.text.isNotEmpty()||binding.phoneCart.text.isNotEmpty()){
                Toast.makeText(requireContext(),"Your Order is Done",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Enter your Address and Phone",Toast.LENGTH_SHORT).show()
            }
        }

    }

    //Set data in recycler view :
    private fun setUpData(){
        binding.rvCart.adapter = adapter
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        cartViewModel.readAllCart().observe(viewLifecycleOwner) {
            adapter.setData(it)

            //to calculate sub total :
            val subTotal = adapter.totalPrice(it)
            binding.txtSubPrice.text = subTotal

            //to calculate fee :
            val fee = 15.0
            binding.txtFee.text = fee.toString()

            //to calculate final total :
            val subTotalDouble = binding.txtSubPrice.text.toString().toDouble()
            val feeAndDelivery = binding.txtFee.text.toString().toDouble()
            val totalFinal = subTotalDouble + feeAndDelivery
            binding.txtFinalTotal.text = totalFinal.toString()

            //If cart is empty so cart is empty will show in screen :
            visibility()
        }
    }

    //Set delivery details :
    private fun addDeliveryData(){
        profileViewModel.readData(requireContext())
        profileViewModel.profileData.observe(viewLifecycleOwner){
            binding.addressCart.text = it.address
            binding.phoneCart.text = it.phone
        }
    }

    //To change delivery details :
    private fun changeDeliveryData(){
        binding.changeCart.setOnClickListener {
            findNavController().navigate(CartDirections.actionCart2ToProfile2())
        }
    }

    //visibility :
    private fun visibility() {
        if (adapter.oldCartList.isEmpty()) {
            binding.txtCartEmpty.visibility = View.VISIBLE
            binding.layoutCartt.visibility = View.GONE
        } else {
            binding.txtCartEmpty.visibility = View.GONE
            binding.layoutCartt.visibility = View.VISIBLE

        }
    }


}