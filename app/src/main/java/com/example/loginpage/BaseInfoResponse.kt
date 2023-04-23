package com.example.loginpage

data class BaseInfoResponse(
    val resultCode: String,
    val message: String,
    val data: Data
){

    data class Data(
        val profileInfo: ProfileInfo,
        val garage: List<Garage>
    )

    data class ProfileInfo(
        val name: String,
        val gender: String,
        val city: String,
        val birthday: String,
        val phone: String,
        val mail: String,
        val driveLicenseCheckDate: String,
        val address: String,
        val postcode: String,
        val cityId: String
    )

    data class Garage(
        val id: String,
        val vin: String,
        val plateNumber: String,
        val defaultFlag: String,
        val nickName: String,
        val buyCarTime: String,
        val engineNo: String,
        val vehicleImageURL: String,
        val vehicleShowName: String,
        val model: String,
        val insurer: String,
        val insuranceExpireTime: String,
        val nextExamineTime: String,
        val defaultCity: String
    )

}
