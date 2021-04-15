package com.aprian1337.consumerapp.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.consumerapp.data.database.DatabaseContract
import com.aprian1337.consumerapp.data.model.FavoriteUser
import com.aprian1337.consumerapp.utils.MappingHelper

class FavoriteViewModel : ViewModel() {
    fun setFavDatas(context: Context): LiveData<List<FavoriteUser>> {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        return MappingHelper.mapCursorToArrayList(cursor)
    }
}