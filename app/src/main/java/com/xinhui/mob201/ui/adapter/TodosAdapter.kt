package com.xinhui.mob201.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xinhui.mob201.data.model.Todo
import com.xinhui.mob21firebase.databinding.ItemLayoutTodoBinding

class TodosAdapter(
    private var todos: List<Todo>
): RecyclerView.Adapter<TodosAdapter.TodoItemViewHolder>() {
    var listener: Listener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTodoBinding.inflate(inflater, parent, false)
        return TodoItemViewHolder(binding)
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    inner class TodoItemViewHolder(
        private val binding: ItemLayoutTodoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.run {
                tvTitle.text = todo.title
                tvDesc.text = todo.desc
                tvCreatedAt.text = todo.createdAt

                llTodo.setOnClickListener {
                    listener?.onClick(todo)
                }

                btnDelete.setOnClickListener {
                    listener?.onDelete(todo)
                }
            }
        }
    }

    interface Listener {
        fun onClick(todo: Todo)
        fun onDelete(todo: Todo)
    }
}
