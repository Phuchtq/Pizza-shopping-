/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DAO.ProductDAO;
import Models.Entities.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Product</title>");
            out.println("</head>");
            out.println("<body>");
            String productId = request.getParameter("productId");
            int quantity = 1;
            try {
                quantity = Integer.parseInt(request.getParameter("quantity"));
            } catch (Exception e) {
                request.setAttribute("msg", "Invalid information");
                request.getRequestDispatcher("UpdateProduct.jsp?productId=" + productId).forward(request, response);
            }
            float price = 1;
            try {
                price = Float.parseFloat(request.getParameter("price"));
            } catch (Exception e) {
                request.setAttribute("msg", "Invalid information");
                request.getRequestDispatcher("UpdateProduct.jsp?productId=" + productId).forward(request, response);
            }
            String productName = request.getParameter("productName");
            if (productName.isEmpty()) {
                request.setAttribute("msg", "Invalid information");
                request.getRequestDispatcher("UpdateProduct.jsp?productId=" + productId).forward(request, response);
            }
            String productImage = request.getParameter("productImage");
            if (productImage.isEmpty()) {
                request.setAttribute("msg", "Invalid information");
                request.getRequestDispatcher("UpdateProduct.jsp?productId=" + productId).forward(request, response);
            }
            String supplierId = request.getParameter("supplierId");
            String categoryId = request.getParameter("categoryId");
            Product p = new Product(productId, productName, supplierId, categoryId, quantity, price, productImage);
            ProductDAO pDao = new ProductDAO();
            boolean status = pDao.update(p);
            if (status) {
                request.getRequestDispatcher("StaffInterface.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Invalid information");
                request.getRequestDispatcher("UpdateProduct.jsp?productId=" + productId).forward(request, response);
            }
            out.println("</body>");
            out.println("</html>");
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
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
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
