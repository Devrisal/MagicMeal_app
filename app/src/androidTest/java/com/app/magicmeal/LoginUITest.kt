package com.app.magicmeal

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class LoginUITest {

    @get:Rule
    val uiRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLogin() {
        onView(withId(R.id.etEmail)).perform(typeText("dev@ymail.com"))

        Thread.sleep(5000)
        closeSoftKeyboard()

        onView(withId(R.id.etPassword)).perform(typeText("dev123"))

        Thread.sleep(5000)
        closeSoftKeyboard()

        onView(withId(R.id.btnLogin)).perform(click())

    }

}