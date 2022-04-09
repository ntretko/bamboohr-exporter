package com.ntretko.bamboothingy.service.impl

import com.ntretko.bamboothingy.factory.CSVFactory
import com.ntretko.bamboothingy.model.TimesheetEntry
import com.ntretko.bamboothingy.service.CsvOutputService
import org.springframework.stereotype.Service

@Service
class CsvOutputServiceImpl implements CsvOutputService {
    @Override
    String convertToCsv(List<TimesheetEntry> entries) {
        CSVFactory csvFactory = new CSVFactory(";" as char)
        return entries.collect { TimesheetEntry entry -> csvFactory.getCSVRow(entry) }.join("\n")
    }
}
