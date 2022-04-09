package com.ntretko.bamboothingy.service

import com.ntretko.bamboothingy.model.TimesheetEntry

interface HtmlParsingService {

    List<TimesheetEntry> getEntries(String siteContentHtml)

}