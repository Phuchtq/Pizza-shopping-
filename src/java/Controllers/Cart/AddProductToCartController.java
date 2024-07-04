/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DAO.ProductDAO;
import Models.Entities.Product;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
@WebServlet(name = "AddProductToCartController", urlPatterns = {"/AddProductToCartController"})
public class AddProductToCartController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String accountId = (String) session.getAttribute("accountId");
        String productId = request.getParameter("productId");
        Product p = null;
        String productCart = "productCart" + accountId;
        HashMap<String, Product> pCart = (HashMap<String, Product>) session.getAttribute(productCart);
        CartUtil util = new CartUtil();
        Cookie c = util.getCookieByName(request, productCart);
        //---------------------------------------------
        if (pCart == null) {
            if (c != null) {
                HashMap<String, Product> cookieCart = util.getCartFromCookie(c);
                if (cookieCart != null) {
                    pCart = cookieCart;
                    
                   
                    p = pCart.get(productId);
                    if (p == null) {
                        ProductDAO pDao = new ProductDAO();
                        List<Product> list = pDao.searchById(productId);
                        p = list.get(0);
                        p.setQuantity(1);
                        pCart.put(productId, p);
                    } else {
                        p.setQuantity(p.getQuantity() + 1);
                    }
                }
            } else {
                pCart = new HashMap<>();
                ProductDAO pDao = new ProductDAO();
                List<Product> list = pDao.searchById(productId);
                p = list.get(0);
                p.setQuantity(1);
                pCart.put(productId, p);
            }
        } else {
            p = pCart.get(productId);
            if (p == null) {
                ProductDAO pDao = new ProductDAO();
                List<Product> list = pDao.searchById(productId);
                p = list.get(0);
                p.setQuantity(1);
                pCart.put(productId, p);
            } else {
                p.setQuantity(p.getQuantity() + 1);
                pCart.remove(productId);
                pCart.put(productId, p);
            }
        }
        //---------------------------------------//      
        session.setAttribute("productCart" + accountId, pCart);
        request.getRequestDispatcher("UserInterface.jsp").forward(request, response);
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
            Logger.getLogger(AddProductToCartController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddProductToCartController.class.getName()).log(Level.SEVERE, null, ex);
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
