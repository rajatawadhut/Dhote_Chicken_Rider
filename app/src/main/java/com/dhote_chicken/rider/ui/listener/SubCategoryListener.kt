package com.dhote_chicken.rider.ui.listener

import com.dhote_chicken.rider.data.model.SubCategoryDetail


interface SubCategoryListener {
    fun onSubCategoryClick(subCategoryDetail: SubCategoryDetail)
}