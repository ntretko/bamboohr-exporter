package com.ntretko.bamboothingy.model

import groovy.transform.ToString

@ToString(includeNames = true)
class TimesheetEntry {
    String timeString
    String dateString

    Date getDate() {
        //todo
    }

    Long getMinutes() {
        //todo
    }
}
