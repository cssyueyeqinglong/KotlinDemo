package com.cy.kotlin.demo.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Administrator on 2017/6/8.
 */
interface  ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}