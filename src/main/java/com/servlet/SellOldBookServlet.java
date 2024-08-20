
package com.servlet;

import com.connection.DBConnection;
import com.dao.BookDao;
import com.daoImpl.BookDaoImpl;
import com.model.Books;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet("/sell_old_book")
@MultipartConfig
public class SellOldBookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            String bookName = request.getParameter("bookName");
            String author = request.getParameter("authorName");
            String price = request.getParameter("bookPrice");
            String category = "Old";
            String status = "Active";
            Part part = request.getPart("bImage");
            String fileName = part.getSubmittedFileName();
            String userEmail = request.getParameter("user");
//            String path1 = getServletContext().getRealPath("") + "book";
//            System.out.println(path1);
            Books book = new Books(bookName, author, price, category, status, fileName, userEmail);

            BookDao bookDao = new BookDaoImpl(DBConnection.getConnection());

            HttpSession session = request.getSession();
            boolean f = bookDao.insertBook(book);
            if (f) {
                String path = getServletContext().getRealPath("") + "book";
                File file = new File(path);
                part.write(path + File.separator + fileName);
                session.setAttribute("succMsg", "Book Selled Successfully");
                response.sendRedirect("sell_book.jsp");
            } else {
                session.setAttribute("failedMsg", "Something went wrong");
                response.sendRedirect("sell_book.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
