package ir.ham.aghaeidi.designPattern

import android.util.Log
import kotlin.reflect.KClass

object FactoryMethodAdhereOCP {

    /******************** BUTTON ******************/
    interface Button {
        fun render(): String
    }

    class PrimaryButton : Button {
        override fun render(): String {
            return "Primary Button rendered."
        }

        companion object {
            init {
                ButtonFactory.registerButton(PrimaryButton::class) { PrimaryButton() }
            }
        }
    }

    class SecondaryButton : Button {
        override fun render(): String {
            return "Secondary Button rendered."
        }

        companion object {
            init {
                ButtonFactory.registerButton(SecondaryButton::class) { SecondaryButton() }
            }
        }
    }

    class RoundedButton : Button {
        override fun render(): String {
            return "Rounded Button rendered."
        }

        init {
            Log.i("MAIN", "main init ")
        }

        companion object {
            init {
                ButtonFactory.registerButton(RoundedButton::class) { RoundedButton() }
                Log.i("MAIN", "companion init ")
            }
        }
    }

    /******************** BUTTON FACTORY ******************/
    object ButtonFactory {
        private val registry = mutableMapOf<KClass<out Button>, () -> Button>()

        // Register a button type with a factory method
        fun registerButton(type: KClass<out Button>, creator: () -> Button) {
            registry[type] = creator
        }

        // Factory method to create buttons based on type
        fun <T : Button> createButton(type: KClass<T>): Button {
            val creator = registry[type] ?: throw IllegalArgumentException("Unknown button type: ${type.simpleName}")
            return creator()
        }
    }

    /******************** MAIN ******************/
    fun main() {

        val primaryButton = ButtonFactory.createButton(PrimaryButton::class)
        println(primaryButton.render()) // Output: Primary Button rendered.

        val secondaryButton = ButtonFactory.createButton(SecondaryButton::class)
        println(secondaryButton.render()) // Output: Secondary Button rendered.

//        val roundedButton = ButtonFactory.createButton(RoundedButton::class)
        val roundedButton = ButtonFactory.createButton(RoundedButton::class)
        println(roundedButton.render()) // Output: Rounded Button rendered.

    }
}