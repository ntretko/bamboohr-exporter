package com.ntretko.bamboothingy.service.impl

import com.ntretko.bamboothingy.config.AppConfiguration
import com.ntretko.bamboothingy.exception.TimesheetMappingException
import com.ntretko.bamboothingy.model.TimesheetEntry
import com.ntretko.bamboothingy.service.HtmlParsingService
import groovy.xml.XmlSlurper
import groovy.xml.slurpersupport.GPathResult
import org.cyberneko.html.parsers.SAXParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HtmlParsingServiceImpl implements HtmlParsingService {
    @Autowired
    AppConfiguration config

    @Override
    List<TimesheetEntry> getEntries(String siteContentHtml) {
        GPathResult http = new XmlSlurper(new SAXParser()).parseText(siteContentHtml)
        List timesheetList = http.depthFirst().findAll {
            it['@class'] == "TimesheetEntries"
        }
        assert timesheetList.size() == 1
        List<TimesheetEntry> timesheetEntries = getTimesheetEntries(timesheetList[0] as GPathResult)
        return timesheetEntries
    }

    private List<TimesheetEntry> getTimesheetEntries(GPathResult timesheetUnparsed) {
        List<GPathResult> rows = timesheetUnparsed.depthFirst().findAll {
            def matcher = (it['@class'] as String) =~ /^TimesheetSlat /
            if (matcher.size() > 1) {
                throw new TimesheetMappingException(it as String)
            }
            matcher.size() == 1
        } as List<GPathResult>
        List<TimesheetEntry> timesheetEntries = rows.collect { GPathResult row ->
            String dateString = row.depthFirst().find {
                it['@class'] == "TimesheetSlat__dayDate"
            }
            String timeString = row.depthFirst().find {
                it['@class'] == "TimesheetSlat__dayTotal"
            }
            return new TimesheetEntry(dateString: dateString, timeString: timeString)
        }
        return timesheetEntries
    }

}
