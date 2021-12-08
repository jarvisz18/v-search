package cn.ixan.search.test;

import cn.ixan.search.domain.valid.Insert;
import cn.ixan.search.domain.valid.People;
import com.rometools.rome.feed.atom.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/3/1 13:57
 * @description test valid controller
 */
@RestController
@Slf4j
public class FirstController {
    //不需验证ID
    @PostMapping("/addPeople")
    public void addPeople(@Validated(Insert.class) @RequestBody People p) {
        log.info("people's ID:" + p.getId());
        log.info("people's toString:" + p.toString());
    }

    @PostMapping("/addTwoPeople")
    public String addTwoPeople(@Validated People p1, BindingResult result1,
                               @Validated Person p2, BindingResult result2) {
        if (result1.hasErrors()) {
            return "0";
        }
        return result2.hasErrors() ? "-1" : "1";
    }

    //需要验证ID
    @PostMapping("/updatePeople")
    public String updatePeople(@Validated({Insert.class}) People p, BindingResult result) {
        log.info("people's ID:" + p.getId());
        return result.hasErrors() ? "0" : "1";
    }
}
