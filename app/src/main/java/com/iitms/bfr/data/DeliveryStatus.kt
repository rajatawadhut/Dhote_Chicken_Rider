package com.iitms.bfr.data


enum class DeliveryStatus(val value : String) {
    Placed("Placed"),
    Accepted("Accepted"),
    InProcess("In Process"),
    AssignedToRider("Assigned to Rider"),
    OutForDelivery("Out for Delivery"),
    Completed("Completed"),
    Closed("Closed"),
    Cancelled("Cancelled"),
}