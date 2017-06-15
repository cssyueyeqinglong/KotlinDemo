package com.cy.kotlin.demo.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.cy.kotlin.demo.R
import com.cy.kotlin.demo.inflate

/**
 * Created by Administrator on 2017/6/8.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup)= TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        Log.d(this.javaClass.simpleName,"bind")
    }

    class TurnsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(parent.inflate(R.layout.item_loading))

}