package com.app.magicmeal

import com.app.magicmeal.repository.UserRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class LoginTest {
    @Test
    fun loginTest() = runBlocking {
        val userRepo = UserRepo()
        val response = userRepo.login("dev@ymail.com", "dev123")
        val expected = true
        val actual = response.success
        Assert.assertEquals(expected, actual)
    }
}