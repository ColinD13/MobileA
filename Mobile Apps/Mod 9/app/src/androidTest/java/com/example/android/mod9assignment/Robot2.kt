package com.example.android.mod9assignment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test

class Robot2 {

    @get:Rule
    val rule: ActivityScenarioRule<Activity2> = ActivityScenarioRule(Activity2::class.java)

    @Test
    fun testItemListAndItemClick() {
        val itemPosition = 2 // Position of the item to click (starting from 0)

        // Assert the number of items on the screen
        onData(anything())
            .inAdapterView(withId(R.id.listView))
            .atPosition(itemPosition)
            .perform(click())
    }
}

