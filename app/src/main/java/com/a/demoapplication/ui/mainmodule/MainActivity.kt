package com.a.demoapplication.ui.mainmodule


import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.a.demoapplication.core.presentation.base.BaseActivity
import com.a.demoapplication.data.api.PlaceHolderModel
import com.a.demoapplication.ui.mainmodule.adapter.PlaceHolderAdapter
import com.a.demoapplication.ui.pagination.PaginationListener.PAGE_START
import com.a.demoapplication.ui.secondmodule.DetailsActivity
import com.a.demoapplication.utils.DialogUtils
import com.demoapplication.R
import com.demoapplication.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    var activityMainBinding: ActivityMainBinding? = null
    val mainViewModel: MainViewModel by viewModel()
    var placeholderModelArraylist = ArrayList<PlaceHolderModel>()
    var placeHolderAdapter: PlaceHolderAdapter? = null
    var mainNavigator: MainNavigator? = null
    lateinit var layoutmanager: GridLayoutManager

    private var currentPage: Int = PAGE_START
    private var isLastPage = false
    private var totalPage = 10
    private var isLoading = false
    var itemCount = 0


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()
        activityMainBinding?.mainviewCallback = this
        mainNavigator = this
        layoutmanager = GridLayoutManager(this@MainActivity, 2)

        placeHolderAdapter = PlaceHolderAdapter(
            placeholderModelArraylist, this@MainActivity,
            mainNavigator as MainActivity
        )
        activityMainBinding?.recyclerView?.setLayoutManager(layoutmanager)
        activityMainBinding?.recyclerView?.setItemAnimator(DefaultItemAnimator())
        activityMainBinding?.recyclerView?.setAdapter(placeHolderAdapter)
        checkInternetConnection()


        /*   activityMainBinding!!.recyclerView.addOnScrollListener(object : PaginationListener(layoutmanager) {
               override fun isLastPage(): Boolean {
                   return isLastPage;
               }

               protected override fun loadMoreItems() {
                   isLoading = true
                   currentPage++
                   doApiCall()
               }

               override fun isLoading1(): Boolean {
                   return isLoading1;
               }


           })*/


    }

    private fun doApiCall() {
        val items: ArrayList<PlaceHolderModel> = ArrayList()
        Handler().postDelayed(Runnable {
            for (i in 0..9) {
                itemCount++
                val postItem = PlaceHolderModel()
                /* postItem.setTitle(getString(R.string.text_title) + itemCount)
                 postItem.setDescription(getString(R.string.text_description))*/
                items.add(postItem)
            }

            if (currentPage !== PAGE_START) placeHolderAdapter?.removeLoading()
            //placeHolderAdapter?.addItems(items)
            // check weather is last page or not
            if (currentPage < totalPage) {
                placeHolderAdapter?.addLoading()
            } else {
                isLastPage = true
            }
            isLoading = false
        }, 1500)
    }


    private fun checkInternetConnection() {
        if (isConnected(this@MainActivity)) {
            DialogUtils.startProgressDialog(this@MainActivity)
            callUserListApi()
        }


    }




    override fun setUp(savedInstanceState: Bundle?) {

    }


    override fun callUserListApi() {
        mainViewModel.UserListApi().observe(this, Observer { allUserList ->
            if (allUserList != null) {
                setAllDataToList(allUserList)

            } else {
                Toast.makeText(this@MainActivity, "No Data Available", Toast.LENGTH_SHORT).show()
            }

        })


    }

    override fun passdataactivity(placeHolderModel: PlaceHolderModel) {
        var intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("placeholdermodel", placeHolderModel)
        startActivity(intent)
    }

    private fun setAllDataToList(allUserList: List<PlaceHolderModel>) {
        placeholderModelArraylist.clear()
        placeholderModelArraylist.addAll(allUserList)
        placeHolderAdapter?.notifyDataSetChanged()


    }

    internal fun <T> chopped(list: List<T>, L: Int): List<List<T>> {
        val parts = ArrayList<List<T>>()
        val N = list.size
        var i = 0
        while (i < N) {
            parts.add(
                ArrayList(
                    list.subList(i, Math.min(N, i + L))
                )
            )
            i += L
        }
        return parts
    }


}

fun isConnected(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        ?: return false
    val info = manager?.activeNetworkInfo
    return info != null && info.isConnected
}





