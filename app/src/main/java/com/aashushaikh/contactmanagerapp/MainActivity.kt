package com.aashushaikh.contactmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aashushaikh.contactmanagerapp.databinding.ActivityMainBinding
import com.aashushaikh.contactmanagerapp.room.User
import com.aashushaikh.contactmanagerapp.room.UserDatabase
import com.aashushaikh.contactmanagerapp.room.UserRepository
import com.aashushaikh.contactmanagerapp.viewmodel.UserViewModel
import com.aashushaikh.contactmanagerapp.viewmodel.UserViewModelFactory
import com.aashushaikh.contactmanagerapp.viewui.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userViewModelFactory: UserViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Room
        val dao = UserDatabase.getInstance(applicationContext).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
        binding.userViewModel = userViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        displayUserList()
    }

    private fun displayUserList() {
        userViewModel.users.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, {selectedItem: User -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this, "Selected User Name: ${selectedItem.name}", Toast.LENGTH_SHORT).show()

        userViewModel.intiUpdateOrDelete(selectedItem)
    }
}