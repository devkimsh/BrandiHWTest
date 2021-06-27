package com.example.brandihwtest.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.brandihwtest.R
import com.example.brandihwtest.databinding.DialogImagedetailBinding
import com.example.brandihwtest.repository.pojo.ImageData
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class ImageDetailPopup(val m_context: Context, val m_data:ImageData): Dialog(m_context) {
    private lateinit var binding:DialogImagedetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogImagedetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //이미지
        Glide.with(m_context).load(m_data.image_url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.loading).into(binding.ivImage)

        //출처 표시
        if(!m_data.display_sitename.equals(""))
        {
            binding.tvDisplaySitename.apply {
                visibility = View.VISIBLE
                text = "출처 : " + m_data.display_sitename
            }
        }

        //문서 작성 시간 표시
        if(!m_data.datetime.equals(""))
        {
            binding.tvDatetime.apply {
                visibility = View.VISIBLE

                Observable.just(m_data.datetime)
                    .map { strDateTime -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+09:00").parse(strDateTime) }
                    .map { date -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) }
                    .map { strFormattedDate -> "작성시간 : " + strFormattedDate }
                    .subscribe { strFormattedDate -> text = strFormattedDate }

//                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+09:00")
//                val sdf_new = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                val date = sdf.parse(m_data.datetime)
//                val strDate = sdf_new.format(date)
//                text = "작성시간 : " + strDate
            }
        }
    }
}