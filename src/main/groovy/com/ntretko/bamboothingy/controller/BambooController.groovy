package com.ntretko.bamboothingy.controller

import com.ntretko.bamboothingy.model.TimesheetEntry
import com.ntretko.bamboothingy.service.CsvOutputService
import com.ntretko.bamboothingy.service.HtmlParsingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletResponse

@Controller
class BambooController {
    @Autowired
    HtmlParsingService htmlParsingService
    @Autowired
    CsvOutputService csvOutputService

    @GetMapping("/")
    String getMainPage(Model model) {
        return "main"
    }

    @PostMapping("/uploadFile")
    void uploadFile(@RequestParam("file") MultipartFile inputFile, HttpServletResponse response) {
        //get html file, transform it into csv and export
        List<TimesheetEntry> entries = htmlParsingService.getEntries(inputFile.getInputStream().text)
        String csv = csvOutputService.convertToCsv(entries)
        response.setHeader("Content-Disposition", "attachment; filename= bamboohr.csv")
        ServletOutputStream outputStream = response.getOutputStream()
        outputStream.write(csv.bytes)
        outputStream.close()
    }

}
