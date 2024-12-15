package com.crawling.reservationz9.z9.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.crawling.reservationz9.z9.api
 * fileName       : Z9CrawlingApiController
 * author         : ipeac
 * date           : 24. 12. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 15.        ipeac       최초 생성
 */
@RestController
@RequestMapping("/api/z9")
@RequiredArgsConstructor
public class Z9CrawlingApiController {
    
    @GetMapping("/crawling")
    public void crawling() {
    
    }
}
