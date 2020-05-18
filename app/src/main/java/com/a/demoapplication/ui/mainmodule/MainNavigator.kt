package com.a.demoapplication.ui.mainmodule

import com.a.demoapplication.data.api.PlaceHolderModel

public interface MainNavigator{
    fun callUserListApi()
   fun passdataactivity(placeHolderModel: PlaceHolderModel)
}
