package com.asadbek.sqliteexample.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asadbek.sqliteexample.R
import com.asadbek.sqliteexample.models.MyUser

class MyAdapter(val list:ArrayList<MyUser>,val rvClick: RvClick) : RecyclerView.Adapter<MyAdapter.VH>() {
        inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
            fun onBind(myUser: MyUser){
                itemRv.findViewById<TextView>(R.id.itemUserId).text = myUser.id.toString()
                itemRv.findViewById<TextView>(R.id.itemUserName).text = myUser.name
                itemRv.findViewById<TextView>(R.id.itemUserSurname).text = myUser.surname

                itemRv.setOnLongClickListener {
                    rvClick.onClick(myUser)
                    true
                }
                itemRv.findViewById<ImageView>(R.id.itemEdtUserData).setOnClickListener {
                    rvClick.editUser(myUser)
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return  VH(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.onBind(list[position])
        }

        override fun getItemCount(): Int {
            return  list.size
        }


    interface RvClick{
        fun onClick(myUser: MyUser)
        fun editUser(myUser: MyUser)
    }

}