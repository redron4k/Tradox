package redron.tradox.navigation

object NavRoutes {
    const val PRICES = "prices"
    const val INSTRUMENT = "instrument"

    fun instrument(code: String) = "$INSTRUMENT/$code"
}

object NavArguments {
    const val CODE = "code"
}
