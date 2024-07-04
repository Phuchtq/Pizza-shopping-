/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DAO.AccountDAO;
import Models.DAO.CustomerDAO;
import Models.DAO.OrderDAO;
import Models.DAO.OrderDetailDAO;
import Models.Entities.Account;
import Models.Entities.Customer;
import Models.Entities.Order;
import Models.Entities.OrderDetail;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
@WebServlet(name = "CustomerUpdateController", urlPatterns = {"/CustomerUpdateController"})
public class CustomerUpdateController extends HttpServlet {

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
        String customerId = request.getParameter("customerId");
        String password = request.getParameter("password");
        String contactName = request.getParameter("contactName");
        String address = request.getParameter("address");
        // Kiem tra sdt neu tao lao back ve dki lai
        String phone = request.getParameter("phone");
        String regex = "[0-9]{9,11}";
        if (phone.matches(regex)) { // Sdt hop le thi vao menu khach hang
            CustomerDAO cDao = new CustomerDAO();
            Customer c = new Customer(customerId, password, contactName, address, phone);
            cDao.update(c);
            //-------------------------------
            AccountDAO aDao = new AccountDAO();
            Account a = aDao.searchById(customerId).get(0);
            a.setFullName(contactName);
            a.setPassword(password);
            aDao.update(a);
            //---------------------------------
            OrderDAO oDao = new OrderDAO();
            List<Order> oList = oDao.searchByCustomerId(customerId);
            if (oList.size() > 0) {
                Order o = oList.get(0);
                o.setShipAddress(address);
                oDao.update(o);
            }
            request.getRequestDispatcher("ViewAllCustomer.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "This phone number is invalid");
            request.getRequestDispatcher("UpdateCustomer.jsp").forward(request, response);
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
            Logger.getLogger(CustomerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CustomerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
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
