package com.nowcoder.bookmanager.controllers;

import com.nowcoder.bookmanager.model.Book;
import com.nowcoder.bookmanager.model.User;
import com.nowcoder.bookmanager.service.BookService;
import com.nowcoder.bookmanager.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/index"},method={RequestMethod.GET})
    public String bookList(Model model){

        User host= hostHolder.getUser();

        if(host!=null){
            model.addAttribute("host",host);
        }

        loadAllBooksView(model);
        return "book/books";
    }

    @RequestMapping(path={"/books/add"},method = {RequestMethod.GET})
    public String addBook(){
        return "book/addbook";
    }

    @RequestMapping(path = {"/books/add/do"} ,method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") String price
    ){
        Book book=new Book();
        book.setAuthor(author);
        book.setName(name);
        book.setPrice(price);
        bookService.addBooks(book);

        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"},method ={RequestMethod.GET})
    public String deleteBook(
            @PathVariable("bookId") int bookId
    ){
        bookService.deleteBooks(bookId);
        return "redirect:/index";
    }


    @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
    public String recoverBook(
            @PathVariable("bookId") int bookId
    ) {

        bookService.recoverBooks(bookId);
        return "redirect:/index";

    }

    /**
     * 为model加载所有的book
     * @param model
     */
    private void loadAllBooksView(Model model){
        model.addAttribute("books",bookService.getAllBooks());
    }
}
