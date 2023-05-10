package com.example.retrofittodo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittodo.ToDo
import com.example.retrofittodo.databinding.ItemTodoBinding

class TodoAdapter:RecyclerView.Adapter<TodoAdapter.ViewHolder>() {


    private val diffCallBack = object :DiffUtil.ItemCallback<ToDo>(){
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diffCallBack)
    var todos:List<ToDo>
        get()=differ.currentList
        set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int=todos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=todos[position]
        holder.binding.apply {
            tvTodo.text=item.title
            checkbox.isChecked=item.completed
        }
    }

    inner class ViewHolder(val binding:ItemTodoBinding):RecyclerView.ViewHolder(binding.root)
}