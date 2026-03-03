package redron.tradox.data.utils

import kotlinx.datetime.Instant
import java.time.format.DateTimeFormatter
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.toStringDate(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val localDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())
    val javaLocalDateTime = java.time.LocalDateTime.of(
        localDateTime.year,
        localDateTime.monthNumber,
        localDateTime.dayOfMonth,
        localDateTime.hour,
        localDateTime.minute,
        localDateTime.second
    )
    return formatter.format(javaLocalDateTime)
}
