package com.dhote_chicken.rider.data


enum class DeliveryStatus(val value : String) {
    Placed("Placed"),
    Accepted("Accepted"),
    InProcess("In Process"),
    AssignedToRider("Assigned to Rider"),
    OutForDelivery("Out for Delivery"),
    Delivered("Delivered"),
    Closed("Closed"),
    Cancelled("Cancelled"),
}