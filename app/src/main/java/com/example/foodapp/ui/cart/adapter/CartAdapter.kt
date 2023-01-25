package com.example.foodapp.ui.cart.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.CartCardBinding
import com.example.foodapp.room.entity.CartData
import com.example.foodapp.ui.cart.CartViewModel

class CartAdapter: RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    var oldCartList = emptyList<CartData>()
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CartCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cartNow = oldCartList[position]
        holder.binding.nameCard.text = cartNow.name
        holder.binding.priceCard.text = cartNow.price.toString()
        holder.binding.numberCard.text = cartNow.number.toString()
        holder.binding.sizeCard.text = cartNow.size


        //button add and minus :
        val price = holder.binding.priceCard.text.toString().toDouble()
        var number = holder.binding.numberCard.text.toString().toInt()
        val change = price.div(number)


        holder.binding.add.setOnClickListener {

            number += 1
            holder.binding.numberCard.text = number . toString()
            holder.binding.priceCard.text = number.times(change).toString()

            //update price when price increase: (update num and price)
            val numberIncreased = holder.binding.numberCard.text.toString().toInt()
            val priceIncreased = holder.binding.priceCard.text.toString().toDouble()
            val nameConst = holder.binding.nameCard.text.toString()
            val sizeConst = holder.binding.sizeCard.text.toString()
            val id = cartNow.id


            val cart = CartData(id,nameConst,sizeConst,priceIncreased,numberIncreased)
            cartViewModel.updateCart(cart)
        }

        //Update price when decrease :
        holder.binding.minus.setOnClickListener {

            if (number > 1){
                number -= 1
                holder.binding.numberCard.text = number . toString()
                holder.binding.priceCard.text = number.times(change).toString()


                val numberDecreased = holder.binding.numberCard.text.toString().toInt()
                val priceDecreased = holder.binding.priceCard.text.toString().toDouble()
                val nameConst = holder.binding.nameCard.text.toString()
                val sizeConst = holder.binding.sizeCard.text.toString()
                val id = cartNow.id

                val cart = CartData(id,nameConst,sizeConst,priceDecreased,numberDecreased)
                cartViewModel.updateCart(cart)
            }
        }


        //For deleting :
        holder.binding.deleteCart.setOnClickListener {
            val name = holder.binding.nameCard.text.toString()
            val number = holder.binding.numberCard.text.toString().toInt()
            val price = holder.binding.priceCard.text.toString().toDouble()
            val size = holder.binding.sizeCard.text.toString()
            messageCart(it.context,cartNow.id,name,size,price,number)
        }
    }

    override fun getItemCount(): Int {
        return oldCartList.size
    }


    class MyViewHolder(var binding: CartCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    fun setupViewModel(vm :CartViewModel){
        cartViewModel = vm
    }

    fun setData(newCartList:List<CartData>){
        val diffUtil = FoodDiffUtil(oldCartList = oldCartList, newCartList = newCartList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldCartList = newCartList
        diffResult.dispatchUpdatesTo(this)
    }

    fun totalPrice(cart : List<CartData>) : String{
        val list :ArrayList<Double> = ArrayList()
        for(element in cart){
            val price = element.price
            if (price != null) {
                list.add(price)
            }
        }
        return list.sum().toString()
    }


    //dialog alert message for deleting :
    private fun messageCart(context : Context, idDialog:Int, name:String, size:String, price:Double, number:Int ){
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle("Delete")
            setMessage("Are you sure?")
            setPositiveButton("Yes") { _, _ ->
                val cart = CartData(idDialog,name,size,price,number)
                cartViewModel.deleteCart(cart)
            }
            setNegativeButton("No") { _, _ ->
            }
        }
        val dialog = builder.create()
        dialog.show()
    }



}