/*
package ir.ham.aghaeidi.factory

import android.util.Log

private const val TAG = "LambdaFactory"

// Define the Notification interface
interface Notification {
    fun notifyUser()
}

// Implement EmailNotification
class EmailNotification : Notification {
    override fun notifyUser() {
        Log.i(TAG, "notifyUser: Sending an Email Notification")
    }
}

// Implement PushNotification
class PushNotification : Notification {
    override fun notifyUser() {
        Log.i(TAG, "notifyUser: Sending a Push Notification")
    }
}

// Define a new enum for Notification types
enum class NotificationType {
    EMAIL, PUSH
}

// Define a Factory class that uses a registry pattern
object NotificationFactory {
    private val notificationRegistry: MutableMap<NotificationType,() -> Notification> = mutableMapOf()

    init {
        Log.i(TAG, "init: ")
        // Register default notification types
        registerNotification(NotificationType.EMAIL, ::EmailNotification)
        registerNotification(NotificationType.PUSH, ::PushNotification)
    }

    // Function to register new notification types
    private fun registerNotification(type: NotificationType, creator: () -> Notification) {
        Log.i(TAG, "registerNotification: type: $type")
        notificationRegistry[type] = creator
    }

    // Create Notification using the registered types
    fun createNotification(type: NotificationType): Notification? {
        Log.i(TAG, "createNotification: type: $type")
        return notificationRegistry[type]?.invoke()
    }
}
*/
