package com.asadbek.sqliteexample

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.asadbek.sqliteexample.adapters.MyAdapter
import com.asadbek.sqliteexample.database.MyBase
import com.asadbek.sqliteexample.databinding.ActivityListOfUsersBinding
import com.asadbek.sqliteexample.models.MyUser

class ListOfUsersActivity : AppCompatActivity() {
    lateinit var binding: ActivityListOfUsersBinding
    lateinit var list:ArrayList<MyUser>
    lateinit var myBase: MyBase
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()
        myBase = MyBase(this)

        list.addAll(myBase.getAllUsers())


        myAdapter = MyAdapter(list,object : MyAdapter.RvClick{
            override fun onClick(myUser: MyUser) {
                val dialog = AlertDialog.Builder(this@ListOfUsersActivity)
                dialog.setTitle("Ogohlantirish!")
                dialog.setMessage("Rostanxam ushbu foydalanuvchi ma`lumotlari o`chirilsinmi?")
                dialog.setPositiveButton("O`chirish",object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        myBase.deleteUser(myUser)
                        list.remove(myUser)
                        myAdapter.notifyDataSetChanged()
                    }

                })
                dialog.setNegativeButton("Bekor qilish",object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@ListOfUsersActivity, "Bekor qilindi!", Toast.LENGTH_SHORT).show()
                    }

                })
                dialog.show()
            }

            @SuppressLint("MissingInflatedId")
            override fun editUser(myUser: MyUser) {
                val dialog = AlertDialog.Builder(this@ListOfUsersActivity).create()
                val view = LayoutInflater.from(this@ListOfUsersActivity).inflate(R.layout.dialog_item,null,false)
                dialog.setView(view)
                dialog.show()
                dialog.setCancelable(true)
                view.findViewById<EditText>(R.id.dialogName).setText(myUser.name)
                view.findViewById<EditText>(R.id.dialogSurName).setText(myUser.surname)

                view.findViewById<Button>(R.id.dialogBtnSave).setOnClickListener {
                    val name = view.findViewById<EditText>(R.id.dialogName).text.toString().trim()
                    val surname = view.findViewById<EditText>(R.id.dialogSurName).text.toString().trim()
                    list.clear()
                    myBase.editUser(MyUser(myUser.id,name,surname))
                    list.addAll(myBase.getAllUsers())
                    myAdapter.notifyDataSetChanged()
                    Toast.makeText(this@ListOfUsersActivity, "Ma`lumot o`zgartirildi!", Toast.LENGTH_SHORT).show()
                    dialog.cancel()

                }
            }

        })

        binding.rv.adapter = myAdapter
    }
}