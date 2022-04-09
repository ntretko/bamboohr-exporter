package com.ntretko.bamboothingy.factory

import com.ntretko.bamboothingy.model.TimesheetEntry

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CSVFactory {
    private final char separator

    CSVFactory(char separator) {
        this.separator = separator
    }

    String getCSVRow(TimesheetEntry entry) {
        List fields = [
                getFormattedDate(entry.date),
                entry.timeString,
                entry.getMinutes(),
                entry.weekDayNumber
        ]
        return fields.join(separator.toString())
    }

    private String getFormattedDate(LocalDate date) {
        date.format(DateTimeFormatter.ISO_DATE)
    }

}
