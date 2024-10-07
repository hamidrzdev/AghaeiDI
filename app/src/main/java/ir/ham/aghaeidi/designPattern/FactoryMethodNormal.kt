package ir.ham.aghaeidi.designPattern

object FactoryMethodNormal {
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
    abstract class ButtonFactory {
        abstract fun createButton(): Button
    }

    class PrimaryButtonFactory : ButtonFactory() {
        override fun createButton(): Button {
            return PrimaryButton()
        }
    }

    class SecondaryButtonFactory : ButtonFactory() {
        override fun createButton(): Button {
            return SecondaryButton()
        }
    }

    class RoundedButtonFactory : ButtonFactory() {
        override fun createButton(): Button {
            return RoundedButton()
        }
    }

    /******************** MAIN ******************/
    fun main() {
        // Create a Primary Button using the factory
        val primaryButtonFactory = PrimaryButtonFactory()
        val primaryButton = primaryButtonFactory.createButton()
        println(primaryButton.render()) // Output: Primary Button rendered.

        // Create a Secondary Button using the factory
        val secondaryButtonFactory = SecondaryButtonFactory()
        val secondaryButton = secondaryButtonFactory.createButton()
        println(secondaryButton.render()) // Output: Secondary Button rendered.

        // Create a Rounded Button using the factory
        val roundedButtonFactory = RoundedButtonFactory()
        val roundedButton = roundedButtonFactory.createButton()
        println(roundedButton.render()) // Output: Rounded Button rendered.
    }
}