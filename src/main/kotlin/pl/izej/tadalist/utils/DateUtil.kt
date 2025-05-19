package pl.izej.tadalist.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toISOString(): String = this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
