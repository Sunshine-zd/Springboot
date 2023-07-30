package com.itheima.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 *
 */
@SpringBootTest
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    public void testGetById() {
        System.out.println(bookDao.selectById(2));
    }

    @Test
    public void testSave(){
        Book book = new Book();
        book.setType("测试数据123");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookDao.insert(book);
    }

    @Test
    public void testUpdate(){
        Book book = new Book();
        book.setId(15);
        book.setType("测试数据321");
        book.setName("测试数据321");
        book.setDescription("测试数据321");
        bookDao.updateById(book);
    }

    @Test
    public void testDelete(){
        bookDao.deleteById(15);
    }

    @Test
    public void testGetAll(){
        List<Book> books = bookDao.selectList(null);
        books.forEach(System.out::println);
    }

    @Test
    public void testGetPage(){
        IPage page = new Page(2,5);
        bookDao.selectPage(page,null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }

    @Test
    public void testGetByCondition(){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","Spring");
        bookDao.selectList(queryWrapper);
    }

    @Test
    public void testGetByLambda(){
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Book::getName,"Spring");
        bookDao.selectList(queryWrapper);
    }

    @Test
    public void testGetByLambdaAndComdi(){
        String name = "Spring";
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Book::getName,name);
        bookDao.selectList(queryWrapper);
    }

}