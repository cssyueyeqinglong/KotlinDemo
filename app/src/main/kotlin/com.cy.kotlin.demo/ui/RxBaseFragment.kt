package com.cy.kotlin.demo

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Administrator on 2017/6/8.
 */
abstract class RxBaseFragment : Fragment() {
    protected var subscriptions = CompositeDisposable()

    override fun onPause() {
        super.onPause()
        subscriptions?.clear()
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }
}