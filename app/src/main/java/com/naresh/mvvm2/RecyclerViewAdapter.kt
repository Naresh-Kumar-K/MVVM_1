package com.naresh.mvvm2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.naresh.mvvm2.databinding.ItemBinding

class RecyclerViewAdapter(
    val mainViewModel: MainViewModel,
    private val arrayList: ArrayList<NicePlace>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.NewHolder>() {
    inner class NewHolder(private val view: ItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(blog: NicePlace) {
            /*view.findViewById<TextView>(R.id.title).text = blog.title
            view.findViewById<ImageButton>(R.id.delete).setOnClickListener {}*/
            view.title.text = blog.title
            view.delete.setOnClickListener {
                mainViewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NewHolder {
        // val root = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false) // original without dataBinding
        //val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false) //sample for example
        val root = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewHolder(root)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.NewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

}
