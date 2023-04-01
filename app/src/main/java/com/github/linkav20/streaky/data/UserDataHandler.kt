package com.github.linkav20.streaky.data

import android.util.Log
import com.github.linkav20.streaky.ui.creationtask.model.CreationForm
import com.github.linkav20.streaky.utils.models.TmpResultModel

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

    fun checkPasswordField(value: String?) = checkIfNotEmpty(value) && isPasswordValid(value!!)

    fun checkEmailField(value: String?) = checkIfNotEmpty(value) && isEmailValid(value!!)

    fun checkIfNotEmpty(value: String?) =
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


    fun checkCreationForm(creationForm: CreationForm): TmpResultModel {
        val title = checkIfNotEmpty(creationForm.title)
        val deadline =
            creationForm.deadline != null && Dates.isDateMoreThanNow(creationForm.deadline)
        val notifyTime = if (creationForm.isNotify) creationForm.notifyTime != null else true
        val repeat =
            if (creationForm.isNotify) creationForm.repeat.firstOrNull { it.chosen } != null else true
        val punishment = checkIfNotEmpty(creationForm.punishment)
        val obs1 = checkIfNotEmpty(creationForm.observer1)
        val obs2 = checkIfNotEmpty(creationForm.observer2)

        // TODO some mistake message
        var message = if (!title) "Title cannot be empty"
        else if (!deadline) "Deadline cannot be empty or earlie than now"
        else if (!notifyTime) "Notify time cannot be empty"
        else if (!repeat) "You cannot repeat task never"
        else if (!punishment) "Punishment cannot be empty"
        else if (!obs1) "You need to add friend as observer"
        else if (!obs2) "You need to add stranger as observer"
        else "Something goes wrong"

        val result = title && deadline && notifyTime && repeat && punishment && obs1 && obs2
        if (result) message = "ok"
        return TmpResultModel(result, message)
    }

}