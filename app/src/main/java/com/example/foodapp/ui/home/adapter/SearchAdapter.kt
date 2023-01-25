package com.example.foodapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.SearchCardBinding
import com.example.foodapp.model.MenuData
import com.example.foodapp.ui.home.viewmodel.SearchViewModel



class SearchAdapter : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    var oldCartList = emptyList<MenuData>()
    private lateinit var searchViewModel: SearchViewModel

    //make a Listener :
    private lateinit var menuListener: OnClick

    //Make an OnClick interface :
    interface OnClick {
        fun onItemClickListener(pos: Int)
    }

    fun setOnItemClickListenerMenu(listener: OnClick) {
        menuListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SearchCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), menuListener
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menuNow = oldCartList[position]
        holder.binding.searchImageCard.setImageResource(menuNow.image)
        holder.binding.txtSearchCard.text = menuNow.name

    }

    override fun getItemCount(): Int {
        return oldCartList.size
    }

    class MyViewHolder(var binding: SearchCardBinding, listener: OnClick) :
        RecyclerView.ViewHolder(binding.root) {

        //init listener (when press on any position on card layout in recyclerView list) :
        init {
            binding.cardLayout.setOnClickListener {
                listener.onItemClickListener(bindingAdapterPosition)
            }
        }
    }


    fun setupViewModel(vm : SearchViewModel){
        searchViewModel = vm
    }

    fun setData(newCartList:List<MenuData>){
        val diffUtil = SearchDiffUtil(oldCartList = oldCartList, newCartList = newCartList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldCartList = newCartList
        diffResult.dispatchUpdatesTo(this)
    }




}