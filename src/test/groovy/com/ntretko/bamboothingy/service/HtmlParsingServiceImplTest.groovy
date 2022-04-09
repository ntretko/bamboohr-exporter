//file:noinspection DuplicateStringLiteral
package com.ntretko.bamboothingy.service

import com.ntretko.bamboothingy.config.AppConfiguration
import com.ntretko.bamboothingy.security.BamboohrCredentials
import com.ntretko.bamboothingy.service.impl.HtmlParsingServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class HtmlParsingServiceImplTest extends Specification {

    @Autowired
    private HtmlParsingServiceImpl dataDownloadService
    @Autowired
    AppConfiguration config

    def "can log in"() {
        given:
        BamboohrCredentials credentials = new BamboohrCredentials(username: "natan.tretko@xtrf.eu", password: "Zyrafajekupe1337!")
        when:
        String html = dataDownloadService.downloadHtml("${config.baseURL}${config.loginEndpoint}", credentials)
        then:
        noExceptionThrown()
        println htmlpr
    }
}
