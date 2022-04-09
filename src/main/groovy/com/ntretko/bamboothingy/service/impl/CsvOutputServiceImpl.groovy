package com.ntretko.bamboothingy.service.impl

import com.ntretko.bamboothingy.model.TimesheetEntry
import com.ntretko.bamboothingy.service.CsvOutputService
import org.springframework.stereotype.Service

@Service
class CsvOutputServiceImpl implements CsvOutputService {
    @Override
    String convertToCsv(List<TimesheetEntry> entries) {
        return ["test", "test2", "test3"].join(",")
    }
}
