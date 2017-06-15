package com.cy.kotlin.demo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cy.kotlin.demo.adapter.NewsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by Administrator on 2017/6/8.
 */
class MainFragment : RxBaseFragment() {

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    private var redditNews: RedditNews? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_main, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mList_View.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            addItemDecoration(LinearDiliver(context, LinearLayoutManager.VERTICAL))
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        initAdapter()
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (mList_View.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val news = (mList_View.adapter as NewsAdapter).getNews()
        if(news!=null&&news.size>0){
            outState?.putParcelable(KEY_REDDIT_NEWS,redditNews?.copy(news = news))
        }
    }

    private val newsManager by lazy { NewsManager() }

    fun requestNews() {
        val subcription = newsManager.getNewList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            retrievedNews ->
                            redditNews = retrievedNews
                            (mList_View.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        {
                            e ->
                            Snackbar.make(mList_View, e.message ?: "", Snackbar.LENGTH_SHORT).show()
                        }

                )
        subscriptions.add(subcription)
    }


    private fun initAdapter() {
        if (mList_View.adapter == null) {
            mList_View.adapter = NewsAdapter()
        }
    }


//    private val mListView: RecyclerView by lazy {
//        view?.findViewById(R.id.mList_View) as RecyclerView
//    }

//    private val mListView by lazy {
//        mList_View
//    }
}

