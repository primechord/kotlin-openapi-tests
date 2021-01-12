package utils

import io.qameta.allure.Allure

object AllureStep {

    @JvmStatic
    fun <T> step(name: String, block: () -> T): T = Allure.step(name, block)
}