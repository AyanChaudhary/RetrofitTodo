package com.example.retrofittodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittodo.Adapter.TodoAdapter
import com.example.retrofittodo.api.RetrofitInstance
import com.example.retrofittodo.api.TodoApiInterface
import com.example.retrofittodo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var todoadapter:TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        lifecycleScope.launch {
            val response=try{
                RetrofitInstance.api.getTodos()
            } catch (e:IOException){
                Log.e("TAG", "onCreate: check your internet", )
                return@launch
            } catch (e:HttpException){
                Log.e("TAG", "onCreate: HttpException, unexpected response", )
                return@launch
            }
            if(response.isSuccessful && response.body()!=null){
                todoadapter.todos=response.body()!!
            }else {
                Log.e("TAG", "Response not successful")
            }
        }

    }

    private fun setUpRecyclerView()=binding.rvTodo.apply {
        layoutManager=LinearLayoutManager(this@MainActivity)
        todoadapter=TodoAdapter()
        adapter=todoadapter
    }
}