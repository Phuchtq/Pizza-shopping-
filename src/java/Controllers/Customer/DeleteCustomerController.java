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
import Models.Entities.Order;
import Models.Entities.OrderDetail;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "DeleteCustomerController", urlPatterns = {"/DeleteCustomerController"})
public class DeleteCustomerController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String customerId = request.getParameter("customerId");
        // oder -> orderDetail -> customer -> account
        OrderDAO oDao = new OrderDAO();
        List<Order> oList = oDao.searchByCustomerId(customerId);
        OrderDetailDAO odDao = new OrderDetailDAO();
        List<OrderDetail> odList = odDao.getList();
        for (int i = 0; i < odList.size(); i++) {
            OrderDetail od = odList.get(i);
            for (Order o : oList) {
                if (od.getOrderId().equals(o.getOrderId())) {
                    odDao.delete(od.getOrderId());
                    break;
                }
            }
        }
        oDao.deleteByCustomerId(customerId);
        CustomerDAO cDao = new CustomerDAO();
        cDao.delete(customerId);
        AccountDAO aDao = new AccountDAO();
        aDao.delete(customerId);
        request.getRequestDispatcher("ViewAllCustomer.jsp").forward(request, response);
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
            Logger.getLogger(DeleteCustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteCustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
