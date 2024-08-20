
package com.dao;

import com.model.BookOrder;
import java.util.List;


public interface BookOrderDao {
    
    public boolean saveOrder(List<BookOrder> bList);
    
    public List<BookOrder> getAllBookByUser(String email);
    
    public List<BookOrder> getAllBookByAdmin();
    
    public BookOrder getBookByOrderId(String orderId);
}
