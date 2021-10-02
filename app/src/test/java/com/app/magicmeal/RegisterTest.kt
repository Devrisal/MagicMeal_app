package com.app.magicmeal

import com.app.magicmeal.model.User
import com.app.magicmeal.repository.UserRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RegisterTest {

    @Test
    fun registerTest() = runBlocking {
        val userRepo = UserRepo()
        val user = User(
            username = "Dev Risal",
            email = "dev@gmail.com",
            phone_no = "9878989098",
            password = "dev123"
        )
        val response = userRepo.register(user)
        val expected = true
        val actual = response.success
        Assert.assertEquals(expected, actual)
    }
}