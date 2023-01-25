package com.example.foodapp.ui.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize view model :
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        loadData()
        saveData()

    }

    //Fun to save profile data by Data Store :
    private fun saveData(){
        binding.btnSave.setOnClickListener {
            val name = binding.txtProfileName.text.toString()
            val address = binding.txtProfileAddress.text.toString()
            val phone = binding.txtProfilePhone.text.toString()
            profileViewModel.saveData(requireContext(),name,address,phone)
            Toast.makeText(requireContext(),"Saved",Toast.LENGTH_SHORT).show()
        }
    }

    //Fun to read profile data by Data Store :
    private fun loadData(){
        profileViewModel.readData(requireContext())
        profileViewModel.profileData.observe(viewLifecycleOwner){
            binding.txtProfileName.setText(it.name)
            binding.txtProfileAddress.setText(it.address)
            binding.txtProfilePhone.setText(it.phone)
        }
    }


}