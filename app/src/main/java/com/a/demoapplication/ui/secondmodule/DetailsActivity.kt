package com.a.demoapplication.ui.secondmodule

import android.os.Bundle
import android.util.Log
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
        Log.e("data", data.toString())

        Glide.with(this@DetailsActivity)
            .load("https://via.placeholder.com/150/24f355")
            .error(R.drawable.ic_no_image)
            .into(activityDetailsBinding?.imageView!!)

        DialogUtils.stopProgressDialog()


/*

        Glide.with(this@DetailsActivity)
            .load(data.thumbnailUrl)
            .centerCrop()
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false;
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false;
                }

            })
            .apply(myOptions)
            .into(activityDetailsBinding?.imageView!!)

*/


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
