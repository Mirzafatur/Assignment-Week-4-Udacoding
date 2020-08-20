package com.example.mahasiswaappmirzafaturrachman.model.data

import com.example.mahasiswaappmirzafaturrachman.model.data.DataItem
import com.google.gson.annotations.SerializedName

data class ResponseMahasiswa(

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem>? = null

)


