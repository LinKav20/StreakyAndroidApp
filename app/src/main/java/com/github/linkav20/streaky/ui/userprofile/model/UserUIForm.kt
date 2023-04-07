package com.github.linkav20.streaky.ui.userprofile.model

data class UserUIForm(
    val login: String,
    val image: String?,
    val countTask: Int,
    val countObservers: Int,
    val countObserved: Int
) {
}