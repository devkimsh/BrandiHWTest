package com.example.brandihwtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandihwtest.adapter.GridSpacing
import com.example.brandihwtest.adapter.ImageListAdapter
import com.example.brandihwtest.databinding.ActivityMainBinding
import com.example.brandihwtest.repository.pojo.ImageData
import com.example.brandihwtest.repository.pojo.MetaData
import com.example.brandihwtest.viewmodel.ImageListViewModel

const val COLUMN = 3

class MainActivity : AppCompatActivity() {
    var m_viewModel: ImageListViewModel

    private lateinit var binding:ActivityMainBinding
    private var m_metaData:MetaData

    private var m_nCurrentScrollPage = 1

    private var m_bIsAddingDataLoading = false

    init {
        m_viewModel = ImageListViewModel(this, this)

        m_metaData = MetaData()

        m_viewModel.m_kakaoImageResponse.observe(this, Observer {
            binding.recyclerview.apply {
                val arImageLists:ArrayList<ImageData> = it.documents
                m_metaData = it.meta

                if(arImageLists.size > 0)
                {
                    binding.recyclerview.visibility = View.VISIBLE
                    binding.tvNoresult.visibility = View.GONE

                    adapter = ImageListAdapter(this@MainActivity, arImageLists)
                }
                else{
                    binding.recyclerview.visibility = View.GONE
                    binding.tvNoresult.visibility = View.VISIBLE
                }
            }
        })

        m_viewModel.m_moreKakaoImageResponse.observe(this, Observer {
            val arImageLists:ArrayList<ImageData> = it.documents
            val metaData = it.meta

            if(arImageLists.size > 0 && !metaData.is_end)
            {
                val adapter = binding.recyclerview.adapter as ImageListAdapter
                adapter.addDatas(arImageLists)
                adapter.notifyDataSetChanged()
                m_bIsAddingDataLoading = false
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    //검색어 변경시엔 새로 조회
                    m_viewModel.getImageLists(s.toString())
                    m_nCurrentScrollPage = 1
                    m_bIsAddingDataLoading = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(this@MainActivity, COLUMN)
            addItemDecoration(GridSpacing(this@MainActivity, 2f))

            addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = binding.recyclerview.layoutManager
                    val lastVisibleItem = (layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()

                    if (layoutManager.itemCount <= lastVisibleItem + COLUMN && !m_metaData.is_end) {
                        if(!m_bIsAddingDataLoading) {
                            m_viewModel.addImageLists(
                                binding.edSearch.text.toString(),
                                ++m_nCurrentScrollPage
                            )
                            m_bIsAddingDataLoading = true
                        }
                    }
                }
            })
        }
    }
}