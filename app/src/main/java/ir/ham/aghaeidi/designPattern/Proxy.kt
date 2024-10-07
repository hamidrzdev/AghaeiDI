package ir.ham.aghaeidi.designPattern

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class UnauthorizedException:Exception()

enum class UserRule {
    ADMIN , NORMAL_USER
}

object UserStatus{
    var rule = UserRule.NORMAL_USER
}

/******************** SHARED PREFERENCES  ******************/
interface SharedPreferencesContract {
    fun saveData(key: String, value: String)
    fun getData(key: String): String?
}

/******************** REAL IMPLEMENTATION ******************/
class RealSharedPreferences(private val sharedPreferences: SharedPreferences) : SharedPreferencesContract {
    override fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}

/******************** PROXY IMPLEMENTATION ******************/
class SharedPreferencesProxy(private val realSharedPreferences: RealSharedPreferences) : SharedPreferencesContract {
    override fun saveData(key: String, value: String) {
        if (UserStatus.rule != UserRule.ADMIN)
            throw UnauthorizedException()

        realSharedPreferences.saveData(key, value)
    }

    override fun getData(key: String): String? {
        if (UserStatus.rule != UserRule.ADMIN)
            throw UnauthorizedException()

        val value = realSharedPreferences.getData(key)
        return value
    }
}

class CustomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get shared preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Create a real SharedPreferences object
        val realSharedPreferences = RealSharedPreferences(sharedPreferences)

        // Create a proxy that wraps the real SharedPreferences object
        val sharedPreferencesProxy = SharedPreferencesProxy(realSharedPreferences)

        // Use the proxy to save and retrieve data
        sharedPreferencesProxy.saveData("username", "JohnDoe")
        val username = sharedPreferencesProxy.getData("username")
    }
}