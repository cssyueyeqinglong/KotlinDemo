package com.cy.kotlin.demo

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


/**
 * Created by Administrator on 2017/6/8.
 */
class LinearDiliver(val context: Context, val orientation: Int) : RecyclerView.ItemDecoration() {
    private val ATTRS = intArrayOf(android.R.attr.listDivider)
    private var mDividerHeight: Int = dip2dx(2)
    private var dividerColor: Int = 0xFF000000.toInt()
    var mDivider: Drawable? = null
    var mPaint: Paint? = null

    init {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw IllegalArgumentException("请输入正确的参数！")
        }

        val a: TypedArray = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.color = dividerColor
        mPaint?.style = Paint.Style.FILL
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawHorizontal(canvas: Canvas?, parent: RecyclerView?) {
        val left: Int = parent?.paddingLeft as Int
        val right: Int = (parent?.measuredWidth).minus(parent?.paddingRight)
        val childSize = parent.childCount
        for (i in 0..childSize - 1) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + mDividerHeight
            if (mDivider != null) {
                mDivider?.bounds = Rect(left, top, right, bottom)
                mDivider?.draw(canvas)
            }
            if (mPaint != null) {
                canvas?.drawRect(Rect(left, top, right, bottom), mPaint)
            }
        }
    }

    private fun drawVertical(canvas: Canvas?, parent: RecyclerView?) {
        val top: Int = parent?.paddingTop as Int
        val bottom: Int = (parent?.measuredHeight).minus(parent?.paddingBottom)
        val childSize = parent.childCount
        for (i in 0..childSize - 1) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + mDividerHeight
            if (mDivider != null) {
                mDivider?.bounds = Rect(left, top, right, bottom)
                mDivider?.draw(canvas)
            }
            if (mPaint != null) {
                canvas?.drawRect(Rect(left, top, right, bottom), mPaint)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect?, itemPosition: Int, parent: RecyclerView?) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect?.set(0, 0, 0, mDividerHeight)
    }


    fun dip2dx(value: Int): Int = (context.resources.displayMetrics.density * value).toInt()
}

