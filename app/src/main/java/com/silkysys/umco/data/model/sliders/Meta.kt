package com.silkysys.umco.data.model.sliders

data class Meta(
    val path: String = "",
    val perPage: Int = 0,
    val total: Int = 0,
    val lastPage: Int = 0,
    val from: Int = 0,
    val links: List<LinksItem>?,
    val to: Int = 0,
    val currentPage: Int = 0
)