package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.controller.utils.R;
import com.itheima.domain.Book;
import com.itheima.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public R getAll(){
        R r = new R(true,bookService.list());

        return r;
    }

    @PostMapping
    public R save(@RequestBody Book book){
        R r = new R(bookService.save(book));

        return r;
    }

    @PutMapping
    public R update(@RequestBody Book book){
        R r = new R(bookService.updateById(book));

        return r;
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        R r = new R(bookService.removeById(id));

        return r;
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id){
        R r = new R(true,bookService.getById(id));

        return r;
    }

/*    @GetMapping("/{current}/{pageSize}")
    public R getPage(@PathVariable int current,@PathVariable int pageSize){
        IPage<Book> page = bookService.getPage(current, pageSize);
        //如果当前页码值大于总页码值，重新执行查询操作，使用最大页码值作为当前页码值
        if(current > page.getPages()){
            page = bookService.getPage((int) page.getPages(),pageSize);
        }

        R r = new R(true,page);
        return r;
    }*/

    @GetMapping("/{current}/{pageSize}")
    public R getPage(@PathVariable int current,@PathVariable int pageSize,Book book){
        System.out.println("book ===> "+ book);
        IPage<Book> page = bookService.getPage(current, pageSize,book);
        //如果当前页码值大于总页码值，重新执行查询操作，使用最大页码值作为当前页码值
        if(current > page.getPages()){
            page = bookService.getPage((int) page.getPages(),pageSize,book);
        }

        R r = new R(true,page);
        return r;
    }
}
