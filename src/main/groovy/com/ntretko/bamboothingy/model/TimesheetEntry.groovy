//file:noinspection DuplicateStringLiteral
package com.ntretko.bamboothingy.model

import groovy.transform.ToString

import java.time.LocalDate
import java.time.Month

@ToString(includeNames = true)
class TimesheetEntry {
    private final String timeString
    private final String dateString

    TimesheetEntry(String timeString, String dateString) {
        this.timeString = timeString
        this.dateString = dateString
    }

    String getTimeString() {
        return timeString
    }

    LocalDate getDate() {
        int year = LocalDate.now().year
        String[] dateSplit = dateString.split(" ")
        assert dateSplit.size() == 2
        Month month = getMonthFromString(dateSplit[0])
        int dayNumber = dateSplit[1] as short
        LocalDate.of(year, month, dayNumber)
    }

    Long getMinutes() {
        String[] timeSplit = timeString.split(" ")
        String hours = timeSplit[0]
        String minutes = timeSplit[1]
        hours = hours.dropRight(1)
        minutes = minutes.dropRight(1)
        return hours.toLong() * 60 + minutes.toLong()
    }

    long getWeekDayNumber() {
        date.dayOfWeek.value
    }

    private Month getMonthFromString(String monthString) {
        Map<String, Integer> monthsNumbered = [:]
        Month.values().toList().each { Month it -> monthsNumbered[it.name()] = it.value }
        int number = monthsNumbered.find { String name, Integer number ->
            name.toLowerCase().contains(monthString.toLowerCase())
        }.value
        return Month.of(number)
    }
}
