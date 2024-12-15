package com.crawling.reservationz9.z9.service;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * packageName    : com.crawling.reservationz9.z9.service
 * fileName       : Z9CrawlingService
 * author         : ipeac
 * date           : 24. 12. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 15.        ipeac       최초 생성
 */
@Service
@Slf4j
public class Z9CrawlingService {
    private final Browser browser;
    
    public Z9CrawlingService() {
        Playwright playwright = Playwright.create();
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }
    
    public Mono<String> goToZ9ListPage(LocalDateTime dateTime) {
        log.info("지구 예약 리스트 접근 완료");
        
        // 날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final String formattedDate = dateTime.format(formatter);
        
        //ss:MM 형식으로 시간 포맷 지정
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        final String formattedTime = dateTime.format(timeFormatter);
        
        // URL 생성 시 포맷된 날짜 사용
        final String url = MessageFormat.format("https://xn--2e0b040a4xj.com/reservation?branch=4&date={0}#list", formattedDate);
        
        Page page = browser.newPage();
        page.navigate(url);
        
        final Locator locator = page.locator(MessageFormat.format("//*[text() = ''{0}'']", formattedTime));
        
        if (locator.count() <= 0) {
            log.error("해당 시간에 예약 가능한 시간이 없습니다.");
            return Mono.just(url);
        }
        
        log.info("해당 시간에 예약 가능한 시간이 있습니다.");
        
        locator.click();
        
        page.waitForURL("https://xn--2e0b040a4xj.com/reservation/create#list");
        
        log.info("예약 페이지로 이동 완료");
        
        log.info(page.url());
        
        // 예약 페이지 정보 입력
        
        page.fill("input[name='name']", "박상준");
        page.fill("input[name='phone']", "010-2520-4929");
        ////option[@value='3']
        page.selectOption("//*[@id='evePeople']", "3");
        
        //결제방식 가상계좌 체크
        ////label[@class='el-rc bs-bb']
        page.click("//label[@class='el-rc bs-bb']");
        
        //////label[@class='el-rc fw7']
        page.click("//label[@class='el-rc fw7']");
        
        //예약하기 버튼 클릭  //*[@id='eveReservationBtn'
         page.click("//*[@id='eveReservationBtn']");
        
        return Mono.just(url);
    }
    
    private void navigateToPage(String url) {
        try (Page page = browser.newPage()) {
            page.navigate(url);
        }
    }
    
    private Page clickElement(String url, String selector) {
        try (Page page = browser.newPage()) {
            page.navigate(url);
            page.click(selector);
            
            return page;
        }
    }
    
    private void enterText(String url, String selector, String text) {
        try (Page page = browser.newPage()) {
            page.navigate(url);
            page.fill(selector, text);
        }
    }
    
    private void clickButton(String url, String selector) {
        try (Page page = browser.newPage()) {
            page.navigate(url);
            page.click(selector);
        }
    }
}
