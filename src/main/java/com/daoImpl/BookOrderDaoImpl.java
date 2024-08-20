
package com.daoImpl;

import com.dao.BookOrderDao;
import com.model.BookOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BookOrderDaoImpl implements BookOrderDao {

    private Connection con;

    public BookOrderDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean saveOrder(List<BookOrder> bList) {
        boolean f = false;
        try {
            String insertOrder = "INSERT INTO book_order(orderId, userName, email, fullAddress, bookName, authorName, price, paymentType, phoneNumber,orderTime) VALUES(?,?,?,?,?,?,?,?,?,?)";
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(insertOrder);
            for (BookOrder b : bList) {
                ps.setString(1, b.getOrderId());
                ps.setString(2, b.getUserName());
                ps.setString(3, b.getEmail());
                ps.setString(4, b.getFullAddress());
                ps.setString(5, b.getBookName());
                ps.setString(6, b.getAuthorName());
                ps.setString(7, b.getPrice());
                ps.setString(8, b.getPaymentType());
                ps.setString(9, b.getPhoneNumber());
                ps.setString(10, b.getOrderTime());
                ps.addBatch();
            }
            int[] count = ps.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            f = true;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<BookOrder> getAllBookByUser(String email) {
        List<BookOrder> list = new ArrayList<>();
        BookOrder b = null;
        try {
            String query = "SELECT * FROM book_order WHERE email=? ORDER BY orderId DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                b = new BookOrder();
                b.setId(set.getInt("id"));
                b.setOrderId(set.getString("orderId"));
                b.setUserName(set.getString("userName"));
                b.setEmail(set.getString("email"));
                b.setFullAddress(set.getString("fullAddress"));
                b.setBookName(set.getString("bookName"));
                b.setAuthorName(set.getString("authorName"));
                b.setPrice(set.getString("price"));
                b.setPaymentType(set.getString("paymentType"));
                b.setPhoneNumber(set.getString("phoneNumber"));
                b.setOrderTime(set.getString("orderTime"));
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<BookOrder> getAllBookByAdmin() {
        List<BookOrder> list = new ArrayList<>();
        BookOrder b = null;
        try {
            String query = "SELECT * FROM book_order ORDER BY orderId DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                b = new BookOrder();
                b.setId(set.getInt("id"));
                b.setOrderId(set.getString("orderId"));
                b.setUserName(set.getString("userName"));
                b.setEmail(set.getString("email"));
                b.setFullAddress(set.getString("fullAddress"));
                b.setBookName(set.getString("bookName"));
                b.setAuthorName(set.getString("authorName"));
                b.setPrice(set.getString("price"));
                b.setPaymentType(set.getString("paymentType"));
                b.setPhoneNumber(set.getString("phoneNumber"));
                b.setOrderTime(set.getString("orderTime"));
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public BookOrder getBookByOrderId(String orderId) {
        BookOrder b = new BookOrder();
        try {
            String query = "SELECT * FROM book_order WHERE orderId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, orderId);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                b.setId(set.getInt("id"));
                b.setOrderId(set.getString("orderId"));
                b.setUserName(set.getString("userName"));
                b.setEmail(set.getString("email"));
                b.setFullAddress(set.getString("fullAddress"));
                b.setBookName(set.getString("bookName"));
                b.setAuthorName(set.getString("authorName"));
                b.setPrice(set.getString("price"));
                b.setPaymentType(set.getString("paymentType"));
                b.setPhoneNumber(set.getString("phoneNumber"));
                b.setOrderTime(set.getString("orderTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}