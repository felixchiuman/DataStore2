package com.felix.datastore2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
                binding.etPass.setText(it)
                binding.btnLogin.setOnClickListener {result ->
                    Log.d("resultan", it.toString())
                    Log.d("edittext", binding.etPass.text.toString())
                    if (binding.etPass.text.toString().equals(result)){
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
            vText.observe(this@MainActivity){result ->
                binding.etPass.setText(result)
            }
        }
    }

    private fun setupView() {
        binding.apply {
            btnSet.setOnClickListener {
                viewModel.saveDataStore(binding.etPass.text.toString())
            }

        }
    }
}