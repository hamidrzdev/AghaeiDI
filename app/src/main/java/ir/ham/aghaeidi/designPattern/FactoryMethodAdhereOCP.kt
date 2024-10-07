//package ir.ham.aghaeidi.designPattern
//
//object FactoryMethodAdhereOCP {
//
//    /******************** BUTTON ******************/
//    interface Button {
//        fun render(): String
//
//        companion object{
//            enum class Type {
//                PRIMARY, SECONDARY, ROUNDED
//            }
//        }
//    }
//
//    class PrimaryButton : Button {
//        override fun render(): String {
//            return "Primary Button rendered."
//        }
//    }
//
//    class SecondaryButton : Button {
//        override fun render(): String {
//            return "Secondary Button rendered."
//        }
//    }
//
//    class RoundedButton : Button {
//        override fun render(): String {
//            return "Rounded Button rendered."
//        }
//    }
//
//    /******************** BUTTON FACTORY ******************/
//    object ButtonFactory {
//        private val registry = mutableMapOf<Button.Companion.Type, () -> Button>()
//
//        init {
//
//            // Register button types with corresponding creation logic
//            registerButton(Button.Companion.Type.PRIMARY) { PrimaryButton() }
//            registerButton(Button.Companion.Type.SECONDARY) { SecondaryButton() }
//            registerButton(Button.Companion.Type.ROUNDED) { RoundedButton() }
//        }
//
//        // Register a button type with a factory method
//         fun registerButton(type: Button.Companion.Type, creator: () -> Button) {
//            registry[type] = creator
//        }
//
//        // Factory method to create buttons based on type
//        fun createButton(type: Button.Companion.Type): Button {
//            val creator = registry[type] ?: throw IllegalArgumentException("Unknown button type: $type")
//            return creator()
//        }
//    }
//    /******************** MAIN ******************/
//    fun main() {
//
//        val primaryButton = ButtonFactory.createButton(Button.Companion.Type.PRIMARY)
//        println(primaryButton.render()) // Output: Primary Button rendered.
//
//        val secondaryButton = ButtonFactory.createButton(Button.Companion.Type.SECONDARY)
//        println(secondaryButton.render()) // Output: Secondary Button rendered.
//
//        val roundedButton = ButtonFactory.createButton(Button.Companion.Type.ROUNDED)
//        println(roundedButton.render()) // Output: Rounded Button rendered.
//    }
//}