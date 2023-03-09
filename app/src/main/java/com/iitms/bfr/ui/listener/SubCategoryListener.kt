package com.iitms.bfr.ui.listener

import com.iitms.bfr.data.model.SubCategoryDetail


interface SubCategoryListener {
    fun onSubCategoryClick(subCategoryDetail: SubCategoryDetail)
}