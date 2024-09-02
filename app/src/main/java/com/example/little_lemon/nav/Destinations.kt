package com.example.little_lemon.nav

interface Destinations {
    val path: String

    object Onboarding : Destinations {
        override val path = "Onboarding"
    }

    object Home : Destinations {
        override val path = "Home"
    }

    object Profile : Destinations {
        override val path = "Profile"
    }

}
