package com.cy.kotlin.demo

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.cy.kotlin.demo.adapter.AdapterConstants
import com.cy.kotlin.demo.adapter.ViewType

/**
 * Created by Administrator on 2017/6/8.
 */
@SuppressLint("ParcelCreator")
data class RedditNews(val after: String,
                      val before: String,
                      val news: List<RedditNewsItem>) : Parcelable {
    companion object {
        @JvmField val CREATOR = createParcel { RedditNews(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.createTypedArrayList(RedditNewsItem.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(after)
        dest.writeString(before)
        dest.writeTypedList(news)
    }
}


data class RedditNewsItem(val author: String,
                          val title: String,
                          val numComments: Int,
                          val created: Long,
                          val thumbnail: String,
                          val url: String) : ViewType, Parcelable {


    companion object {
        @JvmField val CREATOR = createParcel { RedditNewsItem(it) }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readInt(), source.readLong(), source.readString(), source.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(author)
        dest?.writeString(title)
        dest?.writeInt(numComments)
        dest?.writeLong(created)
        dest?.writeString(thumbnail)
        dest?.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun getViewType() = AdapterConstants.NEWS

}

data class LoginData(val data: LoginBean, val head: Head)

data class Head(val bcode: Int, val bmessage: String, val code: Int, val message: String, val responseTime: Long)

data class LoginBean(val _user_token_: String,
                     val result: Boolean,
                     val userInfo: UserInfo,
                     val userCarInfo: CarInfoBean,
                     val userThirdAccount: List<UserThirdAccount>,
                     val userIMRelation: UserIMRelation,
                     val adminIMInfo: AdminIMInfo)

data class AdminIMInfo(var adminUserName: String? = null,
                       var adminUserAvatar: String? = null,
                       var adminNickName: String? = null)

data class UserIMRelation(var IMUserName: String? = null,
                          var IMUserPass: String? = null,
                          var IMNickName: String? = null)

data class UserThirdAccount(var accountIcon: String? = null,
                            var accountName: String? = null,
                            var accountTypeId: String? = null,
                            var isBound: String? = null)

data class CarInfoBean(var carInfoSpectId: String? = null,
                       var yearType: String? = null,
                       var fctName: String? = null,
                       var seriesIcon: String? = null,
                       var bFirstLetter: String,
                       var url: String? = null,
                       var userCarInfoId: String? = null,
                       var carInfoBrandId: String? = null,
                       var carInfoSeriesId: String? = null,
                       var carInfoEngineId: String? = null,
                       var carInfoYearId: String? = null,
                       var carInfoSpecId: String? = null,
                       var brandName: String? = null,
                       var brandIcon: String? = null,
                       var seriesName: String? = null,
                       var displacement: String? = null,
                       var travelMileage: String? = null,
                       var lastServiceTime: String? = null,
                       var isDefault: String? = null,
                       var year: String? = null,
                       var carInfoLevelId: String? = null,
                       var levelName: String? = null,
                       var licensePlate: String? = null,
                       var engineNo: String? = null,
                       var frameNo: String? = null,
                       var startOffTime: String? = null,
                       var specName: String? = null,
                       var carNo: String? = null,
                       var carType: String? = null,
                       var isIllegalSubscribed: Int = 0)

data class UserInfo(val user_token: String,
                    val address: String,
                    val avatar: String,
                    val cardNumber: String,
                    val email: String,
                    val idCard: String,
                    val loginName: String,
                    val mobile: String,
                    val nickName: String,
                    val userName: String,
                    val qq: String,
                    val realName: String,
                    val score: String,
                    val sex: String,
                    val userId: String,
                    val wechat: String,
                    val _user_token_: String,
                    val belongUserType: String,
                    val belongUserId: String,
                    val referralCode: String,
                    val isLoginPassSet: String,
                    val isReferralPageShow: String,
                    val userNameIsModified: String
)