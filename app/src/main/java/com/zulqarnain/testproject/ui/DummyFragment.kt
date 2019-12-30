package com.zulqarnain.testproject.ui


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.architecture.ViewModelFactory
import com.zulqarnain.testproject.data.local.Todo
import com.zulqarnain.testproject.data.remote.StoreCategoryResponse
import com.zulqarnain.testproject.ui.main.MainViewModel
import dagger.android.support.DaggerFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DummyFragment : DaggerFragment() {

    @Inject
    lateinit var retrofitService: MyService
    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var vm: MainViewModel
    lateinit var rvTodo:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_dummy, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStoreCategories()
        rvTodo= view.findViewById(R.id.rvTodo)
        vm.getLatestList()

        vm.todoLiveData.observe(this, Observer {
            Log.e("DummyFragment", "live data ${it.size}")
        })

    }

    private fun getStoreCategories() {

        retrofitService.getCategory("goshoppi777", "22")
            .enqueue(object : Callback<StoreCategoryResponse> {
                override fun onFailure(call: Call<StoreCategoryResponse>, t: Throwable) {
                    Log.e("DummyFragment", "response is null, ErrorBody:${t}")
                }

                override fun onResponse(
                    call: Call<StoreCategoryResponse>,
                    response: Response<StoreCategoryResponse>
                ) {
                    Log.e("DummyFragment", "response ")
                }
            })

    }

    class TodoAdapter(
        var ctx: Context,
        var list: ArrayList<Todo>,
        val onClick: (todo: Todo) -> Unit
    ) :
        RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TodoAdapter.ToDoViewHolder {

            val holder = ToDoViewHolder(
                LayoutInflater.from(ctx).inflate(
                    R.layout.single_row_todo,
                    parent,
                    false
                )
            )
            return holder
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: TodoAdapter.ToDoViewHolder, position: Int) {
            holder.bindView(list[position])
        }

        class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindView(todo: Todo) {
                val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
                tvDescription.setText(todo.decription)
            }
        }
    }

}
