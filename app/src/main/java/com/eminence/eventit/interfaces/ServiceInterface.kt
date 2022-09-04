package com.eminence.eventit.interfaces

import com.eminence.eventit.Accounts.GetOtpRequest
import com.eminence.eventit.Accounts.GetOtpResponse
import com.eminence.eventit.Accounts.VerifyOtpRequest
import com.eminence.eventit.Accounts.VerifyOtpResponse
import com.eminence.eventit.Activities.details.PlaceDetailTotalRequest
import com.eminence.eventit.Activities.details.PlaceDetailTotalResponse
import com.eminence.eventit.Activities.details.models.PlaceDetailsRequest
import com.eminence.eventit.Activities.details.models.PlaceDetailsResponse
import com.eminence.eventit.Activities.details.models.addOnServices.AddOnServicesRequest
import com.eminence.eventit.Activities.details.models.addOnServices.AddOnServicesResponse
import com.eminence.eventit.CartModels.BookTicketRequest
import com.eminence.eventit.CartModels.BookTicketResponse
import com.eminence.eventit.CartModels.CartListRequest
import com.eminence.eventit.CartModels.CartListResponse
import com.eminence.eventit.Category.CategoryListByIdRequest
import com.eminence.eventit.Category.CategoryListByIdResponse
import com.eminence.eventit.Category.model.categoryListByCity.CategoryListByCityRequest
import com.eminence.eventit.Category.model.categoryListByCity.CategoryListByCityResponse
import com.eminence.eventit.Models.AddToCartRequest
import com.eminence.eventit.Models.AddToCartResponse
import com.eminence.eventit.Models.MyTicketModels.MyTicketRequest
import com.eminence.eventit.Models.MyTicketModels.MyTicketResponse
import com.eminence.eventit.Models.OrderDetails.OrderDetailsRequest
import com.eminence.eventit.Models.OrderDetails.OrderDetailsResponse
import com.eminence.eventit.Models.TimeSlotRequest
import com.eminence.eventit.Models.TimeSlotResponse
import com.eminence.eventit.Models.eventDetails.EventDetailsResponse
import com.eminence.eventit.Profiles.*
import com.eminence.eventit.ui.home.model.banner.BannerResponse
import com.eminence.eventit.ui.home.model.category.CategoryListResponse
import com.eminence.eventit.ui.home.model.slider.SliderResponse
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedRequest
import com.eminence.eventit.ui.home.model.tapvisited.TopVisitedResponse
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoRequest
import com.eminence.eventit.ui.home.model.thingtodo.ThingsToDoResponse
import com.eminence.eventit.ui.home.model.upcoming.UpComingEventRequest
import com.eminence.eventit.ui.home.model.upcoming.UpcomingEventResponse
import com.eminence.eventit.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceInterface {

    @get:GET(Constants.SLIDERLIST)
    val appSliderList: Call<SliderResponse>

    @get:GET(Constants.BANNER)
    val appBannerList: Call<BannerResponse>

    @get:GET(Constants.CATEGORYList)
    val getCategoryList: Call<CategoryListResponse>

    @POST(Constants.CATEGORYLISTBYCITY)
    fun getCategoryListByCity(@Body categoryListByCityRequest: CategoryListByCityRequest): Call<CategoryListByCityResponse>
    @POST(Constants.CATEGORYLISTBYID)
    fun getCategoryListById(@Body categoryListByIdRequest: CategoryListByIdRequest): Call<CategoryListByIdResponse>

    @POST(Constants.TOPVISITED)
    fun getTopList(@Body topVisitedRequest: TopVisitedRequest): Call<TopVisitedResponse>

    @POST(Constants.THINGSTODO)
    fun thingsToDoList(@Body thingsToDoRequest: ThingsToDoRequest): Call<ThingsToDoResponse>

     @POST(Constants.USERLOGIN)
    fun getOTP(@Body getOTPRequest: GetOtpRequest): Call<GetOtpResponse>
    @POST(Constants.USERSIGNUP)
    fun getSignUpOTP(@Body getOTPRequest: GetOtpRequest): Call<GetOtpResponse>
    @POST(Constants.PROFILE)
    fun getProfile(@Body getProfileRequest: GetProfileRequest): Call<GetProfileResponse>
 /*   @POST(Constants.USERSIGNUP)
    fun getOtpSigngUp(@Body getOTPSignRequest: GetOtpRequest): Call<GetOtpResponse>

  */

    @POST(Constants.VERIFYOTP)
    fun verifyOTP(@Body verifytOTPRequest: VerifyOtpRequest): Call<VerifyOtpResponse>
    @POST(Constants.VERIFYOTP)
    fun verifySingUpOTP(@Body verifytOTPRequest: VerifySignUpOtpRequest): Call<VerifyOtpResponse>


    @POST(Constants.UPDATEPROFILE)
    fun updateProfile(@Body updateProfileRequest: updateProfileRequest): Call<updateProfileResponse>
    @POST(Constants.FEEDBACK)
    fun sendFeedback(@Body sendFeedbackRequest: SendFeedbackRequest): Call<SendFeedbackResponse>

    @POST(Constants.USERQUERY)
    fun sendQuery(@Body sendQueryRequest: SendQueryRequest): Call<SendQueryResponse>

    @POST(Constants.UPCOMINGEVENT)
    fun getUpComingEventList(@Body upComingEventRequest: UpComingEventRequest): Call<UpcomingEventResponse>

    @POST(Constants.PLACEDETAILS)
    fun getPlaceDetails(@Body placeDetailsRequest: PlaceDetailsRequest): Call<PlaceDetailsResponse>

    @POST(Constants.PLACEDETAILTOTAL)
    fun getPlaceDetailsTotal(@Body placeDetailTotalResponse: PlaceDetailTotalRequest): Call<PlaceDetailTotalResponse>

    @POST(Constants.TIMESLOT)
    fun getTimeSlot(@Body timeSlotRequest: TimeSlotRequest): Call<TimeSlotResponse>

    @POST(Constants.EVENTDETAILS)
    fun getEventDetails(@Body timeSlotRequest: TimeSlotRequest): Call<EventDetailsResponse>

    @POST(Constants.ADDTOCARTADDON)
    fun addOnServices(@Body addOnServicesRequest: AddOnServicesRequest): Call<AddOnServicesResponse>
    @POST(Constants.ADDTOCART)
    fun addToCart(@Body addToCartRequest: AddToCartRequest): Call<AddToCartResponse>

    @POST(Constants.CARTLIST)
    fun cartList(@Body cartListRequest: CartListRequest): Call<CartListResponse>

    @POST(Constants.BOOKTICKET)
    fun bookTicket(@Body bookTicketRequest: BookTicketRequest): Call<BookTicketResponse>

    @POST(Constants.MYORDER)
    fun myOrder(@Body ticketRequest: MyTicketRequest): Call<MyTicketResponse>

    @POST(Constants.ORDERDETAILS)
    fun orderDetails(@Body orderDetailsRequest: OrderDetailsRequest): Call<OrderDetailsResponse>

}