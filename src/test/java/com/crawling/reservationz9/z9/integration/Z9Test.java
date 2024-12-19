package com.crawling.reservationz9.z9.integration;

import com.crawling.reservationz9.z9.service.Z9CrawlingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * packageName    : com.crawling.reservationz9.z9.integration
 * fileName       : Z9Test
 * author         : ipeac
 * date           : 24. 12. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 15.        ipeac       최초 생성
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(ExecutionMode.CONCURRENT)
public class Z9Test {
    @Autowired
    private Z9CrawlingService z9CrawlingService;
    
    //@Test
    //@DisplayName("테스트용")
    //public void crawlingTest() {
    //    //2024 - 12 - 15 09:50
    //    final LocalDateTime dateTime = LocalDateTime.of(2024, 12, 26, 9, 50);
    //
    //    z9CrawlingService.goToZ9ListPage(dateTime);
    //}
    
    //테스트 반복해서 여러번 병렬로 실행되도록 설정
    @RepeatedTest(value = 100, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("2024-12-26-19-45")
    public void crawlingTest2() {
        //2024 - 12 - 15 09:50
        final LocalDateTime dateTime = LocalDateTime.of(2024, 12, 26, 19, 45);
        
        z9CrawlingService.goToZ9ListPage(dateTime);
    }
    
    @RepeatedTest(value = 100, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("2024-12-26-18-20")
    public void crawlingTest3() {
        //2024 - 12 - 15 09:50
        final LocalDateTime dateTime = LocalDateTime.of(2024, 12, 26, 18, 20);
        
        z9CrawlingService.goToZ9ListPage(dateTime);
    }
}
