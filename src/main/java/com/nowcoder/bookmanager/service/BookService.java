package com.nowcoder.bookmanager.service;

import com.nowcoder.bookmanager.dao.BookDAO;
import com.nowcoder.bookmanager.model.Book;
import com.nowcoder.bookmanager.model.enums.BookStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks(){
        return bookDAO.selectAll();
    }

    public int addBooks(Book book){
        return bookDAO.addBook(book);
    }

    public void deleteBooks(int id){
        bookDAO.updateBookStatus(id, BookStatusEnum.DELETE.getValue());
    }

    public void recoverBooks(int id){
        bookDAO.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
    }


}
