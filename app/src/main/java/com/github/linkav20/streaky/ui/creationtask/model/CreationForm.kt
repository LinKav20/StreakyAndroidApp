package com.github.linkav20.streaky.ui.creationtask.model

import java.util.*

data class CreationForm(
    val title: String?,
    val deadline: Date?,
    val repeat: List<RepeatingDayModel>,
    val isNotify: Boolean,
    val notifyTime: Date?,
    val punishment: String?,
    val observer1: String?,
    val observer2: String?
) {
}