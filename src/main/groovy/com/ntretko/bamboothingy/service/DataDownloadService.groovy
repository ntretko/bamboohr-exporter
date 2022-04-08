package com.ntretko.bamboothingy.service

import com.ntretko.bamboothingy.model.TimesheetEntry
import com.ntretko.bamboothingy.security.BamboohrCredentials

interface DataDownloadService {

    String downloadHtml(String url, BamboohrCredentials credentials)

    List<TimesheetEntry> getEntries(String siteContentHtml)

}