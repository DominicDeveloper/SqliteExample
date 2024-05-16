package com.asadbek.sqliteexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.asadbek.sqliteexample.database.MyBase
import com.asadbek.sqliteexample.databinding.ActivityMainBinding
import com.asadbek.sqliteexample.models.MyUser

class MainActivity : AppCompatActivity() {
    lateinit var list: ArrayList<MyUser>
    lateinit var myBase: MyBase
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        myBase = MyBase(this)

        // databasega saqlash
        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val surname = binding.edtSurname.text.toString()
            myBase.addUser(MyUser(name,surname))
            Toast.makeText(this, "Saqlandi!", Toast.LENGTH_SHORT).show()
            binding.edtName.text.clear()
            binding.edtSurname.text.clear()
        }

        binding.btnGetData.setOnClickListener {
            var data:String = ""
            list.addAll(myBase.getAllUsers())
            list.forEach {
                data += "\nId: ${it.id}\nName: ${it.name}\nSurname: ${it.surname}"
            }
            binding.txtView.text = data
        }


    }
}