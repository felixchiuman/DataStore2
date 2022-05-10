package com.felix.datastore2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.felix.datastore2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = DataStoreManager(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        setupView()
        setObserver()
    }

    private fun setObserver() {
        viewModel.apply {
            getDataStore().observe(this@MainActivity){
                binding.etPass.text.toString()
            }
            vText.observe(this@MainActivity){result ->
                binding.etPass.text.toString()
            }
        }
    }

    private fun setupView() {
        binding.apply {
            btnSet.setOnClickListener {
                viewModel.saveDataStore(binding.etPass.text.toString())
            }
            btnLogin.setOnClickListener {
                if (binding.etPass.text.toString().equals(viewModel.vText)){
                    Toast.makeText(this@MainActivity, "login sukses", Toast.LENGTH_SHORT).show()
                }
                else if (binding.etPass.text.toString().equals("")){
                    Toast.makeText(this@MainActivity, "kosong", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "login gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}