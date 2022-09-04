package com.eminence.eventit.interfaces

interface AddToBookingListner {
    fun addToBookListner(event_activity_id: String?, quantity:String?, price:String?,date:String?,bookingType:String)
}