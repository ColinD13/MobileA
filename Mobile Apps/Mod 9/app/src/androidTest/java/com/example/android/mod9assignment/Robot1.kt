package com.example.android.mod9assignment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class Robot1 {

    @get:Rule
    val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testEditTextAndButtonClick() {
        val number = 5 // Number to enter in EditText

        // Type a number in the EditText
        onView(withId(R.id.numberEditText)).perform(typeText(number.toString()), closeSoftKeyboard())

        // Click the button
        onView(withId(R.id.generateButton)).perform(click())
    }
}
