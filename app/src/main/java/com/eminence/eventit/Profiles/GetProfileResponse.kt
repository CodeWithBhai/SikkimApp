package com.eminence.eventit.Profiles

import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventListData

data class GetProfileResponse(
    val status: Int,
    val message: String,
    val `data`: List<ProfileData>,



    )
