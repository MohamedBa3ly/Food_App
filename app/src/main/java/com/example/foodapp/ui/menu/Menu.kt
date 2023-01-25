package com.example.foodapp.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentMenuBinding
import com.example.foodapp.ui.menu.adapter.MenuAdapter
import com.example.foodapp.ui.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Menu : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private val adapterMenu = MenuAdapter()
    private val listViewModel : ListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuRv.adapter = adapterMenu
        binding.menuRv.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRv.setHasFixedSize(true)

        listViewModel.menuData.observe(viewLifecycleOwner){
            adapterMenu.setData(it)
        }
        clickItemRv()
    }

    private fun clickItemRv(){
        adapterMenu.setOnItemClickListenerMenu(object :MenuAdapter.OnClick{
            override fun onItemClickListener(pos: Int) {
                findNavController().navigate(MenuDirections.actionMenuToAddToCart(adapterMenu.menuList[pos]))
            }
        })
    }

}