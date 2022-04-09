package com.ntretko.bamboothingy.service

import com.ntretko.bamboothingy.model.TimesheetEntry

interface CsvOutputService {
    String convertToCsv(List<TimesheetEntry> entries)
}
