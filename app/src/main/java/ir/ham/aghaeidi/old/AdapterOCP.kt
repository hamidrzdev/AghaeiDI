package ir.ham.aghaeidi.old

// 1. Logger interface remains the same.
interface LoggerOCP {
    fun logInfo(message: String)
    fun logError(message: String)
}

// 2. First adapter for the FirstThirdPartyLogger.
class FirstThirdPartyLoggerAdapter(private val firstLogger: FirstThirdPartyLogger) : LoggerOCP {
    override fun logInfo(message: String) {
        firstLogger.log(message, "INFO")
    }

    override fun logError(message: String) {
        firstLogger.log(message, "ERROR")
    }
}

// 3. Second adapter for the SecondThirdPartyLogger.
class SecondThirdPartyLoggerAdapter(private val secondLogger: SecondThirdPartyLogger) : LoggerOCP {
    override fun logInfo(message: String) {
        secondLogger.writeLog(message, "INFO")
    }

    override fun logError(message: String) {
        secondLogger.writeLog(message, "ERROR")
    }
}

// 4. Client code that uses both adapters.
fun main() {
    val firstLoggerAdapter: LoggerOCP = FirstThirdPartyLoggerAdapter(FirstThirdPartyLogger())
    firstLoggerAdapter.logInfo("This is an info message with the first logger.")

    val secondLoggerAdapter: LoggerOCP = SecondThirdPartyLoggerAdapter(SecondThirdPartyLogger())
    secondLoggerAdapter.logError("This is an error message with the second logger.")
}