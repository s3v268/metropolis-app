package com.example.metropolis_app.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReservaV2(
    @SerializedName("accepted")
    var accepted: String = "",
    @SerializedName("company_name")
    var companyName: String = "",
    @SerializedName("created_at")
    var createdAt: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("end_date")
    var endDate: String = "",
    @SerializedName("event")
    var event: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("n_attendees")
    var nAttendees: Int = 0,
    @SerializedName("n_bus_pass")
    var nBusPass: Int = 0,
    @SerializedName("n_parking_pass")
    var nParkingPass: Int = 0,
    @SerializedName("n_staff_pass")
    var nStaffPass: Int = 0,
    @SerializedName("space")
    var space: String = "",
    @SerializedName("start_date")
    var startDate: String = "",
    @SerializedName("updated_at")
    var updatedAt: String = ""
) : Parcelable