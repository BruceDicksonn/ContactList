package com.example.applistadecontatosktx.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.applistadecontatosktx.R
import com.example.applistadecontatosktx.databinding.ActivityMainBinding
import com.example.applistadecontatosktx.view.cadastro.ActivityRegisterContact

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainViewModel(application, this)
    }

    override fun onResume() {
        super.onResume()
        loadViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.addContact -> {
                startActivityForResult(
                    Intent(
                        applicationContext,
                        ActivityRegisterContact::class.java
                    ), R.id.mainContainer
                )
            }
        }

        return super.onOptionsItemSelected(item)

    }

    private fun loadViews() {

        viewModel.refreshListAdapter()

        viewModel.getAdapter().observe(this) {
            binding.recyclerContatos.adapter = it
        }

        viewModel.getLayoutManager().observe(this) {
            binding.recyclerContatos.layoutManager = it
        }

        viewModel.showOrHideListContacts(binding.mainContainer)

    }
}