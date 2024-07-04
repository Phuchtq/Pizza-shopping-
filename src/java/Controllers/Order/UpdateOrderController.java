/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DAO.OrderDAO;
import Models.Entities.Order;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
@WebServlet(name = "UpdateOrderController", urlPatterns = {"/UpdateOrderController"})
public class UpdateOrderController extends HttpServlet {

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
        String orderId = request.getParameter("orderId");
        OrderDAO oDao = new OrderDAO();
        List<Order> list = oDao.searchByOrderId(orderId);
        Order o = list.get(0);
        String freight_raw = request.getParameter("freight");
        float newFreight = 0;
        try {
            newFreight = Float.parseFloat(freight_raw);
        } catch (Exception e) {
            request.setAttribute("orderId", orderId);
            request.setAttribute("msg", "Invalid freight fee");
            request.getRequestDispatcher("UpdateOrder.jsp").forward(request, response);
        }
        if (newFreight < 0) {
            o.setFreight(0);
        } else o.setFreight(newFreight);
        
        //--------------------------------------
        String shippedDate_raw = request.getParameter("shippedDate");
        Date newShippedDate = null;
        try {
            newShippedDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(shippedDate_raw).getTime());
            if (!newShippedDate.after(o.getRequiredDate()) || !newShippedDate.after(o.getOrderDate())) {
                request.setAttribute("orderId", orderId);
                request.setAttribute("msg", "Invalid shipped date");
                request.getRequestDispatcher("UpdateOrder.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("orderId", orderId);
            request.setAttribute("msg", "Invalid shipped date");
            request.getRequestDispatcher("UpdateOrder.jsp").forward(request, response);
        }
        if (newShippedDate != null) {
            o.setShippedDate(newShippedDate);
        }
        //-----------------------------------
        boolean status = oDao.update(o);
        if (status) {
            request.getRequestDispatcher("ViewAllOrder.jsp").forward(request, response);
        } else {
            request.setAttribute("orderId", orderId);
            request.setAttribute("msg", "Invalid information");
            request.getRequestDispatcher("UpdateOrder.jsp").forward(request, response);
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
            Logger.getLogger(UpdateOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
