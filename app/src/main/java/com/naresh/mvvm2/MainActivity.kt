package com.naresh.mvvm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naresh.mvvm2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.button.setOnClickListener {
            addData()
        }
        binding.recycler.layoutManager =  LinearLayoutManager(this)
        observeData()
    }
    private fun observeData(){
        viewModel.list.observe(this) {
            Log.i("data", it.toString())
            binding.recycler.adapter = RecyclerViewAdapter(viewModel, it, this)
        }
    }

    private fun addData() {
        val title = binding.titleText.text.toString()
        if (title.isBlank()) {
            Toast.makeText(this, "Enter value!", Toast.LENGTH_LONG).show()
        } else {
            val blog = NicePlace(title, "")
            viewModel.add(blog)
            binding.titleText.text.clear()
            binding.recycler.adapter?.notifyDataSetChanged()
        }
    }
}
