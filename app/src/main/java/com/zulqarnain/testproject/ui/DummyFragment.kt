package com.zulqarnain.testproject.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.data.local.Todo
import com.zulqarnain.testproject.data.remote.StoreCategoryResponse
import dagger.android.support.DaggerFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DummyFragment : DaggerFragment() {

    @Inject
    lateinit var retrofitService: MyService


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dummy, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStoreCategories()
        Log.e("DummyFragment", "injected")

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

    class TodoAdapter(val onClick: (todo: Todo) -> Unit) :
        RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TodoAdapter.ToDoViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: TodoAdapter.ToDoViewHolder, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        class ToDoViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        }
    }

}
