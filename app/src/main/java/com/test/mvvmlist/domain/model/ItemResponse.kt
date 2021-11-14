package com.test.mvvmlist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ItemResponse(
	val pagination: Pagination? = null,
	val results: List<ResultsItem>? = null
)


data class Pagination(
	val key: Any? = null
)


@Parcelize
data class ResultsItem(
	val uid: String? = null,
	val price: String? = null,
	val name: String? = null,
	val created_at: String? = null,
	val image_ids: List<String?>? = null,
	val image_urls: List<String?>? = null,
	val image_urls_thumbnails: List<String?>? = null
) : Parcelable
