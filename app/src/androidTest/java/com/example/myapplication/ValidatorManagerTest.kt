package com.example.myapplication

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class ValidatorManagerTest() {

    lateinit var validatorManager: ValidatorManager

    @Before
    fun beforeEach() {
        validatorManager = ValidatorManager()
    }

    @Test
    fun validateEmail() {
        var email = "sdad"
        var validation = validatorManager.emailValid(email)
        assertFalse(validation)
    }

    @Test
    fun validatePassword() {
        var password = "asdad"
        var validation = validatorManager.passValid(password)
        assertFalse(validation)
    }

    @Test
    fun validateEmailDialog() {

        val validation = validatorManager.login("sdad", "qwerty123")
        assertTrue(validatorManager.isDialogShow)

    }

    @Test
    fun validatePasswordDialog() {

        val validation = validatorManager.login("some@gmail.com", "wrong")
        assertTrue(validatorManager.isDialogShow)
    }

    @Test
    fun validateCorrectLogin() {
        val validation = validatorManager.login("some@gmail.com", "correctpass")
        assertTrue(validation)
    }

    @Test
    fun validateIncorrectLogin() {
        val validation = validatorManager.login("some@gmail.com", "correctpass")
        assertFalse(validation)
    }

}