package com.example.foodapp.ui.menu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.MenuCardBinding
import com.example.foodapp.model.MenuData


class MenuAdapter: RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    var menuList = emptyList<MenuData>()

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
            MenuCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), menuListener
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menuNow = menuList[position]
        holder.binding.imageMenu.setImageResource(menuNow.image)
        holder.binding.txtName.text = menuNow.name
        holder.binding.txtPrice.text = menuNow.price.toString()
        holder.binding.txtDiscount.text = menuNow.discount

        }

    override fun getItemCount(): Int {
        return menuList.size
    }


    class MyViewHolder(var binding: MenuCardBinding, listener:OnClick) :
        RecyclerView.ViewHolder(binding.root) {

        //init listener (when press on any position on card layout in recyclerView list) :
        init {
            binding.cardLayout.setOnClickListener {
                listener.onItemClickListener(bindingAdapterPosition)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(menuData:List<MenuData>){
            this.menuList = menuData
            notifyDataSetChanged()
    }


}




