package com.github.linkav20.streaky.data

object UserDataHandler {

    fun checkDataSignUpFrom(
        login: String?,
        email: String?,
        password: String?,
        repeatedPassword: String?
    ): Boolean {
        val isDataNotEmpty = checkLoginField(login)
                && checkEmailField(email)
                && checkPassword(password, repeatedPassword)

        if (!isDataNotEmpty) return false

        return password == repeatedPassword
    }

    fun checkDataFromLoginForm(login: String?, password: String?) =
        checkLoginField(login) && checkIfNotEmpty(password)

    private fun checkPassword(password: String?, repeatedPassword: String?) =
        checkPasswordField(password)
                && checkIfNotEmpty(repeatedPassword)
                && password == repeatedPassword

    private fun checkLoginField(value: String?) = checkIfNotEmpty(value)

    private fun checkPasswordField(value: String?) = checkIfNotEmpty(value) && isPasswordValid(value!!)

    fun checkEmailField(value: String?) = checkIfNotEmpty(value) && isEmailValid(value!!)

    private fun checkIfNotEmpty(value: String?) =
        value != null && value.isNotEmpty()

    private fun isPasswordValid(value: String): Boolean {
        /*if (value.length < 8) return false
        if (value.firstOrNull { it.isDigit() } == null) return false
        if (value.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (value.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (value.firstOrNull { !it.isLetterOrDigit() } == null) return false*/

        return true
    }

    private fun isEmailValid(value: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()

}