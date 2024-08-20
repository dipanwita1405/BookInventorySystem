
package com.servlet;

import com.connection.DBConnection;
import com.dao.BookOrderDao;
import com.dao.CartDao;
import com.daoImpl.BookOrderDaoImpl;
import com.daoImpl.CartDaoImpl;
import com.model.BookOrder;
import com.model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;



@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            String name = request.getParameter("userName");
            String email = request.getParameter("email");
            String number = request.getParameter("phoneNumber");
            String address = request.getParameter("fullAddress");
            String landmark = request.getParameter("landmark");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String pincode = request.getParameter("pincode");
            String paymentType = request.getParameter("paymentType");
            String fullAddress = address + " , " + landmark + " , " + city + " , " + state + " , " + pincode;

            CartDao cartDao = new CartDaoImpl(DBConnection.getConnection());
            List<Cart> bList = cartDao.getBookByUserId(userId);
            HttpSession session = request.getSession();
            if (bList.isEmpty()) {
                session.setAttribute("failedMsg", "Please Add Items");
                response.sendRedirect("checkout.jsp");
            } else {
                BookOrderDao bookOrderDao = new BookOrderDaoImpl(DBConnection.getConnection());
                ArrayList<BookOrder> orderList = new ArrayList<>();
                Random random = new Random();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String orderTime = formatter.format(new Date()); // Get current date and time formatted

                for (Cart c : bList) {
                    BookOrder bookOrder = new BookOrder();
                    bookOrder.setOrderId("BOOK-ORD-00" + random.nextInt(1000));
                    bookOrder.setUserName(name);
                    bookOrder.setEmail(email);
                    bookOrder.setPhoneNumber(number);
                    bookOrder.setFullAddress(fullAddress);
                    bookOrder.setBookName(c.getBookName());
                    bookOrder.setAuthorName(c.getAuthor());
                    bookOrder.setPrice(c.getPrice() + "");
                    bookOrder.setPaymentType(paymentType);
                    bookOrder.setOrderTime(orderTime); // Set formatted date and time

                    orderList.add(bookOrder);
                }

                if ("".equals(paymentType)) {
                    session.setAttribute("failedMsg", "Choose Payment Method");
                    response.sendRedirect("checkout.jsp");
                } else {
                    boolean isSuccess = bookOrderDao.saveOrder(orderList);
                    if (isSuccess) {
                        response.sendRedirect("order_success_page.jsp");
                    } else {
                        session.setAttribute("failedMsg", "Your Order Failed");
                        response.sendRedirect("checkout.jsp");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "OrderServlet handles order processing";
    }
}
