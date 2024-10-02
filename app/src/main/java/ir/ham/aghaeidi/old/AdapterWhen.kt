package ir.ham.aghaeidi.old

// 1. Existing Logger interface.
interface Logger {
    fun logInfo(message: String)
    fun logError(message: String)
}

// 2. First third-party logger.
class FirstThirdPartyLogger {
    fun log(message: String, severity: String) {
        println("FirstLogger - [$severity] $message")
    }
}

// 3. Second third-party logger (new requirement).
class SecondThirdPartyLogger {
    fun writeLog(msg: String, type: String) {
        println("SecondLogger - [$type] $msg")
    }
}

// 4. Adapter that now tries to handle multiple third-party loggers.
class ThirdPartyLoggerAdapter(private val type: String) : Logger {
    private val firstLogger = FirstThirdPartyLogger()
    private val secondLogger = SecondThirdPartyLogger()

    override fun logInfo(message: String) {
        if (type == "First") {
            firstLogger.log(message, "INFO")
        } else if (type == "Second") {
            secondLogger.writeLog(message, "INFO")
        }
    }

    override fun logError(message: String) {
        if (type == "First") {
            firstLogger.log(message, "ERROR")
        } else if (type == "Second") {
            secondLogger.writeLog(message, "ERROR")
        }
    }
}

// 5. Client code.
fun main() {
    val firstLoggerAdapter: Logger = ThirdPartyLoggerAdapter("First")
    firstLoggerAdapter.logInfo("This is an info message with the first logger.")

    val secondLoggerAdapter: Logger = ThirdPartyLoggerAdapter("Second")
    secondLoggerAdapter.logError("This is an error message with the second logger.")
}