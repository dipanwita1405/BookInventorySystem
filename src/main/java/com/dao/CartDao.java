
package com.dao;

import com.model.Books;
import com.model.Cart;
import java.util.List;

public interface CartDao {
    
    public boolean addCart(Cart cart);
    
    public List<Cart> getBookByUserId(Integer id);
    
    public boolean deleteBook(Integer bid, Integer uid, Integer cid);
    
}
