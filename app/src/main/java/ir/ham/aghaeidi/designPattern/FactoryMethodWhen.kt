package ir.ham.aghaeidi.designPattern

object FactoryMethodWhen {

    /******************** BUTTON ******************/
    interface Button {
        fun render(): String
    }

    class PrimaryButton : Button {
        override fun render(): String {
            return "Primary Button rendered."
        }
    }

    class SecondaryButton : Button {
        override fun render(): String {
            return "Secondary Button rendered."
        }
    }

    class RoundedButton : Button {
        override fun render(): String {
            return "Rounded Button rendered."
        }
    }

    /******************** BUTTON FACTORY ******************/
    class ButtonFactory {
        companion object {
            // Factory method that uses a type identifier to create specific button types
            fun createButton(type: String): Button {
                return when (type) {
                    "primary" -> PrimaryButton()
                    "secondary" -> SecondaryButton()
                    "rounded" -> RoundedButton()
                    else -> throw IllegalArgumentException("Unknown button type: $type")
                }
            }

            // Alternatively, a factory method using class reference (for more flexibility)
            fun <T : Button> createButton(clazz: Class<T>): Button {
                return clazz.getDeclaredConstructor().newInstance()
            }

        }
    }

    /******************** MAIN ******************/
    fun main() {
        // Using type identifier to create button
        val primaryButton = ButtonFactory.createButton("primary")
        println(primaryButton.render()) // Output: Primary Button rendered.

        val secondaryButton = ButtonFactory.createButton("secondary")
        println(secondaryButton.render()) // Output: Secondary Button rendered.

        val roundedButton = ButtonFactory.createButton("rounded")
        println(roundedButton.render()) // Output: Rounded Button rendered.

        // Using class reference to create button
        val primaryButtonByClass = ButtonFactory.createButton(PrimaryButton::class.java)
        println(primaryButtonByClass.render()) // Output: Primary Button rendered.
    }
}