package com.a.demoapplication.ui.secondmodule

import android.os.Bundle
import android.util.Log
import com.a.demoapplication.PicassoTrustAll
import com.a.demoapplication.core.presentation.base.BaseActivity
import com.a.demoapplication.data.api.PlaceHolderModel
import com.a.demoapplication.utils.DialogUtils
import com.bumptech.glide.Glide
import com.demoapplication.R
import com.demoapplication.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>(), DetailsNavigator {
    var activityDetailsBinding: ActivityDetailsBinding? = null
    val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsBinding = getViewDataBinding()
        DialogUtils.startProgressDialog(this@DetailsActivity)

        var data = intent.getParcelableExtra<PlaceHolderModel>("placeholdermodel")
       var url =  data.thumbnailUrl;
        PicassoTrustAll.getInstance(this@DetailsActivity)
            .load(url)
            .into(activityDetailsBinding?.imageView!!);

        DialogUtils.stopProgressDialog()



        activityDetailsBinding?.txtGridName?.setText(data.title)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_details

    }

    override fun getViewModel(): DetailsViewModel {
        return detailsViewModel
    }

    override fun setUp(savedInstanceState: Bundle?) {


    }
}
