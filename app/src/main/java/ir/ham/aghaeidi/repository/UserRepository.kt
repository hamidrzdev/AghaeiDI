package ir.ham.aghaeidi.repository

import android.util.Log
import kotlinx.coroutines.android.awaitFrame
import javax.inject.Inject

interface UserRepository {
    fun setName(name:String)
    fun setAge(age:String)
    fun setMobile(mobile:String)

    fun getName():String
    fun getAge():String
    fun getMobile():String
}

class UserRepositoryImpl @Inject constructor(): UserRepository{
    private val TAG = UserRepository::class.simpleName

    var mName = ""
    var mAge = ""
    var mMobile = ""

    override fun setName(name: String) {
        Log.i(TAG, "setName: name: $name")
        mName = name
    }

    override fun setAge(age: String) {
        Log.i(TAG, "setAge: age: $age")
        mAge = age
    }

    override fun setMobile(mobile: String) {
        Log.i(TAG, "setMobile: mobile: $mobile")
        mMobile = mobile
    }

    override fun getName(): String {
        return mName
    }

    override fun getAge(): String {
        return mAge
    }

    override fun getMobile(): String {
        return mMobile
    }

}