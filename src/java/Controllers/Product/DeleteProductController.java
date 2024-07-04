/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DAO.OrderDAO;
import Models.DAO.OrderDetailDAO;
import Models.DAO.ProductDAO;
import Models.Entities.Order;
import Models.Entities.OrderDetail;
import Models.Entities.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteProductController", urlPatterns = {"/DeleteProductController"})
public class DeleteProductController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Product</title>");
            out.println("</head>");
            out.println("<body>");
            String productId = request.getParameter("productId");
            OrderDetailDAO odDao = new OrderDetailDAO();
            List<OrderDetail> tmpStorage = odDao.searchByProductId(productId);
            if (tmpStorage.size() == 0) {
                ProductDAO pDao = new ProductDAO();
                pDao.delete(productId);
                request.getRequestDispatcher("StaffInterface.jsp").forward(request, response);
            } else {
                odDao.deleteByProductIdId(productId);
                OrderDAO oDao = new OrderDAO();
                List<Order> oList = oDao.getList();
                for (int i = 0; i < oList.size(); i++) {
                    Order o = oList.get(i);
                    for (int j = 0; j < tmpStorage.size(); j++) {
                        OrderDetail od = tmpStorage.get(j);
                        if (o.getOrderId().equals(od.getOrderId())) {
                            oDao.delete(o.getOrderId());
                            break;
                        }
                    }
                }
                ProductDAO pDao = new ProductDAO();
                pDao.delete(productId);
            }
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
            Logger.getLogger(DeleteProductController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteProductController.class.getName()).log(Level.SEVERE, null, ex);
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
