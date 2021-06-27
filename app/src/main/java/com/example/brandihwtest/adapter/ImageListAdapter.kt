package com.example.brandihwtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.brandihwtest.R
import com.example.brandihwtest.dialog.ImageDetailPopup
import com.example.brandihwtest.repository.pojo.ImageData

class ImageListAdapter(val m_context: Context, val m_arDatas:ArrayList<ImageData>) : RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val view = LayoutInflater.from(m_context).inflate(R.layout.list_item_image, parent, false)
        return ImageListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.bindData(m_arDatas[position])
    }

    override fun getItemCount(): Int {
        return m_arDatas.size
    }

    fun addDatas(datas : ArrayList<ImageData>)
    {
        m_arDatas.addAll(datas)
    }

    inner class ImageListViewHolder(val itemView: View?) : RecyclerView.ViewHolder(itemView!!)
    {
        val ivPhoto = itemView.findViewById<ImageView>(R.id.iv_image)

        fun bindData(data:ImageData)
        {
            //이미지
            Glide.with(m_context).load(data.image_url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.loading).into(ivPhoto)

            ivPhoto.setOnClickListener({
                val imageDetailPopup = ImageDetailPopup(m_context, data)
                imageDetailPopup.show()
            })
        }
    }
}