
package com.dao;

import com.model.Books;
import java.util.List;


public interface BookDao {

    public boolean insertBook(Books book);

    public List<Books> getAllBooks();

    public Books getBookById(Integer id);

    public boolean updateBook(Books books);

    public boolean deleteBook(Integer id);

    public List<Books> getNewBooks();

    public List<Books> getRecentBooks();

    public List<Books> getOldBooks();

    public List<Books> getAllRecentBook();

    public List<Books> getAllNewBook();

    public List<Books> getAllOldBook();

    public List<Books> getUserAllOldBooks(String email, String category);
    
    public boolean deleteUseOldBook(String email,String category, Integer bookId);
    
    public List<Books> getBookBySearch(String ch);
}
