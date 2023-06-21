package com.example.android.mod9assignment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class Robot3 {

    @get:Rule
    val rule: ActivityScenarioRule<Activity3> = ActivityScenarioRule(Activity3::class.java)

    @Test
    fun testTextViewContent() {
        val expectedText = "You clicked Item 2" // Expected text to assert

        // Assert the text displayed in the TextView
        onView(withId(R.id.clickedItemTextView)).check(matches(withText(expectedText)))
    }
}

