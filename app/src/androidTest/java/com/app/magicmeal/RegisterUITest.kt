package com.app.magicmeal

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.temporal.JulianFields

@LargeTest
@RunWith(JUnit4::class)
class RegisterUITest {

    @Test
    fun testRegister() {

        Espresso.onView(ViewMatchers.withId(R.id.etFullName))
            .perform(ViewActions.typeText("Dev Risal New"))
        ViewActions.closeSoftKeyboard()
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.etPhone))
            .perform(ViewActions.typeText("9998909878"))
        ViewActions.closeSoftKeyboard()
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
            .perform(ViewActions.typeText("devnew@gmail.com"))
        ViewActions.closeSoftKeyboard()
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.etLocation))
            .perform(ViewActions.typeText("KTM"))
        ViewActions.closeSoftKeyboard()
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
            .perform(ViewActions.typeText("dev123"))
        ViewActions.closeSoftKeyboard()
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.btnRegister)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

}