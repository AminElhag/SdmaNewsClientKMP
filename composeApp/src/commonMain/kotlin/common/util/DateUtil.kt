package common.util

import kotlinx.datetime.Clock.*
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class DateUtil {
    companion object {
        fun getDateFromString(string: String): Instant {
            return Instant.parse(string)
        }

        fun getDateInBeautyWay(date: Instant): String {
            val currentDate = System.now()
            val duration = date.minus(currentDate)
            return duration.toString()
        }

        fun getDateInBeautyWay(string: String): String {
            try {
                val date = Instant.parse(string).toLocalDateTime(TimeZone.UTC)
                val instant = date.toInstant(TimeZone.currentSystemDefault())
                val currentDateTime = System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                val currentInstant = currentDateTime.toInstant(TimeZone.currentSystemDefault())
                val minus = currentInstant.minus(instant)
                if (minus <= 5.minutes) {
                    return "قبل لحظات"
                } else if (minus > 5.minutes && minus < 1.hours) {
                    return "قبل ${minus.inWholeMinutes} دقيقة "
                } else if (minus > 1.hours && minus < 1.days) {
                    return "قبل ${minus.inWholeHours} ساعة"
                }
                return date.format(
                    LocalDateTime.Format {
                        date(
                            LocalDate.Format {
                                monthName(MonthNames.ENGLISH_ABBREVIATED)
                                char(' ')
                                dayOfMonth()
                                chars(" ")
                                year()
                            }
                        )
                        chars(" - ")
                        time(
                            LocalTime.Format {
                                hour(); char(':'); minute()
                            }
                        )
                    }
                )
            } catch (e: Exception) {
                return "---"
            }
        }
    }
}