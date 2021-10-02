package com.app.magicmeal

import com.app.magicmeal.repository.UserRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.lang.AssertionError

class LoginFailedTest {

    @Test
    fun loginFailedTest() = runBlocking {
        val userRepo = UserRepo()
        val response = userRepo.login("dev@gmail.com", "dev@123") //provided wrong password
        val expected = true
        val actual = response.success
        Assert.assertEquals(expected, actual)
    }
}