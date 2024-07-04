/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DAO.AccountDAO;
import Models.DAO.CustomerDAO;
import Models.Entities.Account;
import Models.Entities.Customer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
@WebServlet(name = "LoginProcessController", urlPatterns = {"/LoginProcessController"})
public class LoginProcessController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginProcessController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginProcessController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == "" || password == "") {
            request.setAttribute("msg", "Invalid username or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        AccountDAO aDao = new AccountDAO();
        List<Account> list = aDao.getList();
        boolean status = false;
        Account account = null;
        for (Account a : list) {
            if (a.getUserName().equals(username) && a.getPassword().equals(password)) {
                account = a;
                break;
            }
        }
        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            session.setAttribute("accountId", account.getAccountId());
            session.setAttribute("fullName", account.getFullName());
            if (account.isType()) {
                session.setAttribute("staff", account);
                request.getRequestDispatcher("StaffInterface.jsp").forward(request, response);
            } else {
                CustomerDAO cDao = new CustomerDAO();
                List<Customer> cList = cDao.searchById(account.getAccountId());
                Customer t = cList.get(0);
                if (t != null) {
                    Customer c = t;
                    session.setAttribute("customer" + account.getAccountId(), c);
                    request.getRequestDispatcher("UserInterface.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("msg", "Invalid username or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
