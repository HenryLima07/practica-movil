package com.example.testeableapp

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TipCalculatorUiTest {


    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun redondearPropina_cambiaResultadoPropina() {
        composeTestRule.onNodeWithTag("amountToPay").performTextInput("151.65")

        composeTestRule.onNodeWithTag("tipResult").assertTextEquals("Propina: $22.75")

        composeTestRule.onNodeWithTag("roundCheckBox").performClick()

        composeTestRule.onNodeWithTag("tipResult").assertTextEquals("Propina: $23.00")
    }

    @Test
    fun slider_modificaPorcentajeYActualizaPropina() {
        composeTestRule.onNodeWithTag("tipPercentageText")
            .assertTextEquals("Porcentaje de propina: 15%")

        composeTestRule.onNodeWithTag("tipPercentageSlider")
            .performSemanticsAction(SemanticsActions.SetProgress) {
                it(0.5f)
            }
        composeTestRule.onNodeWithTag("tipPercentageText")
            .assertTextEquals("Porcentaje de propina: 1%")
    }

    @Test
    fun elementosUi_visibles_enPantalla() {

        composeTestRule.onNodeWithTag("amountToPay").assertExists()
        composeTestRule.onNodeWithTag("tipPercentageText").assertExists()
        composeTestRule.onNodeWithTag("tipPercentageSlider").assertExists()
        composeTestRule.onNodeWithTag("peopleCountText").assertExists()
        composeTestRule.onNodeWithTag("roundCheckBox").assertExists()
        composeTestRule.onNodeWithTag("tipResult").assertExists()

    }


    //agregadas
    @Test
    fun noPermitirMenosDeUnaPersona() {
        repeat(5) {
            composeTestRule.onNodeWithText("-").performClick()
        }

        composeTestRule.onNodeWithTag("peopleCountText").assertTextEquals("Número de personas: 1")
    }

    @Test
    fun inputTextoNoNumerico_muestraPropinaCero() {
        composeTestRule.onNodeWithTag("amountToPay").performTextInput("abc")

        composeTestRule.onNodeWithTag("tipResult").assertTextEquals("Propina: $0.00")
    }
}