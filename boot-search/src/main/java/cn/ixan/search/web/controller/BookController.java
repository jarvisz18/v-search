package cn.ixan.search.web.controller;

import cn.ixan.search.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class BookController {
    /**
     * 添加Book对象
     *
     * @param book
     */
    @PostMapping(value = "/book")
    public void addBook(@RequestBody @Valid Book book) {
        log.info(book.toString());
    }
}
