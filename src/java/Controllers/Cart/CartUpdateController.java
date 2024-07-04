/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Entities.Product;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "CartUpdateController", urlPatterns = {"/CartUpdateController"})
public class CartUpdateController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String productId = request.getParameter("productId");
        HttpSession session = request.getSession();
        String accountId = (String) session.getAttribute("accountId");
        String sessionName = "productCart" + accountId;
        HashMap<String, Product> sessionCart = (HashMap<String, Product>) session.getAttribute(sessionName);
        CartUtil util = new CartUtil();
        Cookie c = util.getCookieByName(request, sessionName);
        if (sessionCart != null) {
            if (sessionCart.containsKey(productId)) {
                Product p = sessionCart.get(productId);
                p.setQuantity(quantity);
                sessionCart.remove(productId); //xoa product cu~
                sessionCart.put(productId, p);
                session.setAttribute("productCart" + accountId, sessionCart);
            }
        }
//        if (c != null) {
//            HashMap<String, Product> cookieCart = util.getCartFromCookie(c);
//            if (cookieCart != null) {
//                if (cookieCart.containsKey(productId)) {
//                    Product p = cookieCart.get(productId);
//                    p.setQuantity(quantity);
//                    cookieCart.remove(productId);
//                    cookieCart.put(productId, p);
//                    String itemsInCart = util.convertCartToString(new ArrayList<Product>(cookieCart.values()));
//                    util.saveCartToCookie(request, response, itemsInCart, accountId);
//                }
//            }
//        }
        request.getRequestDispatcher("ViewCart.jsp").forward(request, response);
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
