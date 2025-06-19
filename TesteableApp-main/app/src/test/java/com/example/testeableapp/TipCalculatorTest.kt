package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import kotlin.test.Test
import kotlin.test.assertEquals

class TipCalculatorTests {

    @Test
    fun calculateTip_with37PercentAndRounding() {
        val result = calculateTip(67.8, 37, true)
        assertEquals(26.0, result, 0.01)
    }

    @Test
    fun calculateTip_withNegativeAmount_returnsZero() {
        val result = calculateTip(-50.0, 10, false)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateTotalPerPerson_correctlyIncludesTip() {
        val bill = 150.0
        val tip = calculateTip(bill, 15, false)
        val numberOfPeople = 4
        val totalPerPerson = if (numberOfPeople > 0) (bill + tip) / numberOfPeople else 0.0
        assertEquals(43.12, totalPerPerson, 0.1)
    }

    // Test 4.1 - Propina de 0% debería dar 0
    @Test
    fun propinaCeroPorciento_retornaCero() {
        val result = calculateTip(amount = 100.0, tipPercent = 0, roundUp = false)
        assertEquals(0.0, result, 0.0)
    }

    // Test 4.2 - Redondear propina 3.01 debería dar 4.0
    @Test
    fun redondearPropina_aplicaCeilCorrectamente() {
        val result = calculateTip(amount = 20.07, tipPercent = 15, roundUp = true) // 20.07 * 0.15 = 3.0105
        assertEquals(4.0, result, 0.0)
    }
}