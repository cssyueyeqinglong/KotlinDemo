@file:JvmName("NewsDelegateAdapter")

package com.cy.kotlin.demo.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cy.kotlin.demo.*
import kotlinx.android.synthetic.main.item_new_content.view.*

/**
 * Created by Administrator on 2017/6/8.
 */

class NewsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = NewsDelegateHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsDelegateHolder
        holder.bind(item as RedditNewsItem)
    }

    class NewsDelegateHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_new_content)) {
        //        val img_thumbnail: ImageView by lazy { itemView?.findViewById(R.id.img_thumbnail) as ImageView }
//        val description: TextView by lazy { itemView?.findViewById(R.id.description) as TextView }
//        val author: TextView by lazy { itemView?.findViewById(R.id.author) as TextView }
//        val comments: TextView by lazy { itemView?.findViewById(R.id.comments) as TextView }
//        val time: TextView by lazy { itemView?.findViewById(R.id.time) as TextView }
        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            itemView.setOnClickListener { v ->
                val intent = Intent(context, NetActivity::class.java)
                context.startActivity(intent)
            }
//            time.text = item.created.getFriendlyTime()
        }
    }
}
