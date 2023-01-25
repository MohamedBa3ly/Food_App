package com.example.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.model.MenuData
import com.example.foodapp.ui.home.adapter.SearchAdapter
import com.example.foodapp.ui.home.viewmodel.SearchViewModel
import com.example.foodapp.ui.viewmodel.ListViewModel
import com.example.foodapp.utils.MarketMenu
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val listViewModel : ListViewModel by activityViewModels()
    private lateinit var searchViewModel: SearchViewModel
    private val adapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize :
        binding.SearchHome.setIconifiedByDefault(false)
        binding.SearchHome.queryHint = "Search for your meal"
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        //Add Menu to data base to make a search from search view:
        searchViewModel.addMenu(MarketMenu.getMarketMenu())


        //Menu :
        burgerMenu()
        pizzaMenu()
        friedChickenMenu()
        salad()
        drinks()

        //Popular
        hickoryPopular()
        pizzaPipronyPopular()
        friedChickenPopular()
        saladPopular()

        //Offers
        cheeseAndSmoked()
        friedChickenAndSalad()


        binding.SearchHome.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText!!)
                return true
            }

        })

        //Fun to click on items that appear from your search and you will goto add to cart fragment:
        clickItemRv()
    }

    //Burger
    private fun burgerMenu(){
        binding.burgerMenu.setOnClickListener {
            listViewModel.apply {
                removeData()
                addList(MarketMenu.getMarketMenu()[0])
                addList(MarketMenu.getMarketMenu()[1])
                addList(MarketMenu.getMarketMenu()[2])
                addList(MarketMenu.getMarketMenu()[3])
                addList(MarketMenu.getMarketMenu()[4])
            }
            it.findNavController().navigate(HomeDirections.actionHome2ToMenu())
        }
    }

    //Pizza
    private fun pizzaMenu(){
        binding.pizzaMenu.setOnClickListener {
            listViewModel.apply {
                removeData()
                addList(MarketMenu.getMarketMenu()[5])
                addList(MarketMenu.getMarketMenu()[6])
                addList(MarketMenu.getMarketMenu()[7])
                addList(MarketMenu.getMarketMenu()[8])
                addList(MarketMenu.getMarketMenu()[9])

            }
            it.findNavController().navigate(HomeDirections.actionHome2ToMenu())
        }
    }

    //Fried chicken
    private fun friedChickenMenu(){
        binding.friedMenu.setOnClickListener {
            listViewModel.apply {
                removeData()
                addList(MarketMenu.getMarketMenu()[10])
                addList(MarketMenu.getMarketMenu()[11])
            }
            it.findNavController().navigate(HomeDirections.actionHome2ToMenu())
        }
    }

    //Salad
    private fun salad(){
        binding.saladMenu.setOnClickListener {
            listViewModel.apply {
                removeData()
                addList(MarketMenu.getMarketMenu()[12])
                addList(MarketMenu.getMarketMenu()[13])
            }
            it.findNavController().navigate(HomeDirections.actionHome2ToMenu())
        }
    }

    //Drinks
    private fun drinks(){
        binding.drinksMenu.setOnClickListener {
            listViewModel.apply {
                removeData()
                addList(MarketMenu.getMarketMenu()[14])
                addList(MarketMenu.getMarketMenu()[15])
            }
            it.findNavController().navigate(HomeDirections.actionHome2ToMenu())
        }
    }

    //Hickory Popular :
    private fun hickoryPopular(){
        binding.hickoryBurgerPop.setOnClickListener {
            val hickory = MenuData(18,R.drawable.cheeseburger,R.drawable.cheeseburger,"Hickory Burger","Hickory burger",80.0,100.0,"10%")
            findNavController().navigate(HomeDirections.actionHome2ToAddToCart(hickory))
        }
    }

    //Pizza Popular :
    private fun pizzaPipronyPopular(){
        binding.pizzaPipronyPop.setOnClickListener {
            val pizza = MenuData(19,R.drawable.pizzaapiprony,R.drawable.pizzaapiprony,"Our Pepperoni Pizza features our crispy thin crust topped with real cheese and scrumptious pepperoni for a classic taste that will never fade away.","Pizza Piprony",65.0,100.0,"10%")
            findNavController().navigate(HomeDirections.actionHome2ToAddToCart(pizza))
        }
    }

    //Fried Chicken Popular :
    private fun friedChickenPopular(){
        binding.friedPop.setOnClickListener {
            val fried = MenuData(20,R.drawable.friedchicken2,R.drawable.friedchicken2,"Fried Chicken","Fried Chicken",80.0,100.0,"10%")
            findNavController().navigate(HomeDirections.actionHome2ToAddToCart(fried))
        }
    }

    //Salad Popular :
    private fun saladPopular(){
        binding.saladPop.setOnClickListener {
            val salad = MenuData(21,R.drawable.fresh_salad,R.drawable.salad,"Salad","Salad",40.0,100.0,"5%")
            findNavController().navigate(HomeDirections.actionHome2ToAddToCart(salad))
        }
    }

    //Cheese and smoked offers :
    private fun cheeseAndSmoked(){
        binding.cheeseSmokedBurger.setOnClickListener {
            val smoked = MenuData(22,R.drawable.twoburger,R.drawable.twoburger,"Cheese And Smoked Burger","Cheese And Smoked Burger",120.0,100.0,"10%")
            findNavController().navigate(HomeDirections.actionHome2ToAddToCart(smoked))
        }
    }

    //
    //Fried chicken and chicken salad :
    private fun friedChickenAndSalad(){
        binding.chickenSaladOffer.setOnClickListener {
            val chickenSalad = MenuData(23,R.drawable.chicken_salad,R.drawable.friedchicken2,"Cheese And Smoked Burger","Cheese And Smoked Burger",120.0,100.0,"10%")
            findNavController().navigate(HomeDirections.actionHome2ToAddToCart(chickenSalad))
        }
    }

    //Click on items that appeared in search :
    private fun clickItemRv(){
        adapter.setOnItemClickListenerMenu(object : SearchAdapter.OnClick{
            override fun onItemClickListener(pos: Int) {
                findNavController().navigate(HomeDirections.actionHome2ToAddToCart(adapter.oldCartList[pos]))
            }
        })
    }

    //Fun to make search by name of your meal :
    private fun search(newText:String){
        binding.rvSearch.adapter = adapter
        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())

        if (newText.isNotEmpty()){
            searchViewModel.searchForMeal(newText).observe(viewLifecycleOwner){
                binding.rvSearch.visibility = View.VISIBLE
                adapter.setData(it)
            }
        }else{
            binding.rvSearch.visibility = View.GONE
        }
    }

    //To return search view to original state :
    override fun onPause() {
        super.onPause()
        if (!binding.SearchHome.isIconified){
            binding.SearchHome.isIconified = true
        }
    }



}