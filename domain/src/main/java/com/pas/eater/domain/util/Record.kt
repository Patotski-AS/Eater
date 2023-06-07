package com.pas.eater.domain.util

data class Record<out R>(
    val data: R?,
    val error: ErrorRecord?,
)
