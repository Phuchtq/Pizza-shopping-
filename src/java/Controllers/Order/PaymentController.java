/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Order;

import Controllers.CartUtil;
import Models.DAO.CategoryDAO;
import Models.DAO.OrderDAO;
import Models.DAO.OrderDetailDAO;
import Models.DAO.SupplierDAO;
import Models.Entities.Category;
import Models.Entities.Customer;
import Models.Entities.Order;
import Models.Entities.OrderDetail;
import Models.Entities.Product;
import Models.Entities.Supplier;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

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
        CategoryDAO cDao = new CategoryDAO();
        SupplierDAO sDao = new SupplierDAO();
        HttpSession session = request.getSession();
        String accountId = (String) session.getAttribute("accountId");
        String sessionName = "productCart" + accountId;
        List<Category> cList = cDao.getList();
        List<Supplier> sList = sDao.getList();
        HashMap<String, Product> pCart = null;
        HashMap<String, Product> sessionCart = (HashMap<String, Product>) session.getAttribute("productCart" + accountId);
        HashMap<String, Product> cookieCart = new HashMap<>();
        CartUtil util = new CartUtil();
        Cookie c = util.getCookieByName(request, sessionName);
        if (c != null) {
            cookieCart = util.getCartFromCookie(c);
        }
        if (sessionCart != null && sessionCart.size() > 0) {
            pCart = sessionCart;
        } else if (cookieCart != null && cookieCart.size() > 0) {
            pCart = cookieCart;
        }
        if (pCart != null && pCart.size() > 0) {
            //----- Lên đơn cho từng sản phảm ------
            // Note chưa hoàn thiện (chưa tối ưu): ở đây t làm dạng lên đơn theo kiểu shopee tức là trùng sẩn phẩm do 1 người đặt từ 1 nguồn sẽ vào 1 đơn (xét thêm từ supplier mà t k làm kĩ chỗ này), nhưng 
            // bị nhầm do đây là shop pizza 1 đơn chứa được nhiều sản phẩm khác nhau nên có thể tự fix lại chỗ này
            for (Product p : pCart.values()) {
                // Note chưa hoàn thiện p2: chuyển date sang dạng ngày giờ cụ thể để set tình trạng xử lí đơn (xem chi tiết ở ViewMyOrder.jsp) :))))
                
                // Chỉnh thời gian orrder , required và ship
                // Ở đây t set thời gian order là lúc thanh toán 
                // 2 cái còn lại = thời gian order + 1 ngày lên
                // Như t nói ở trên thì order t k để dạng giờ cụ thể nên hơi lủng xíu nên là t nghĩ cần chỉnh về dạng giờ cụ thể
                
                long millis = System.currentTimeMillis();
                Date orderDate = new Date(millis);
                LocalDate localOrderDate = orderDate.toLocalDate();
                // Add one day to the orderDate
                LocalDate requiredLocalDate = localOrderDate.plusDays(1);
                // Convert LocalDate back to java.sql.Date
                Date requiredDate = Date.valueOf(requiredLocalDate);
                Date shippedDate = requiredDate;
                
                
                String customerName = "customer" + accountId;
                Customer customer = (Customer) session.getAttribute(customerName);
                /*ORDERID = "O" + đơn trong db + 1. Nếu là đơn đầu thì ID = O2; nếu là đơn thứ n sẽ là On + 1*/
                OrderDAO oDao = new OrderDAO();
                String customerId = customer.getCustomerId();
                String shipAddress = customer.getAddress();

                //------------- Tạo order ID ----------------
                // Nguyên lí đọc code nếu chưa thấm thì ib hỏi thêm (lười giải thích trên đây :)))
                List<Order> oList = oDao.getList();
                int max;
                int result = -1;
                if (oList.size() > 0) {
                    List<Integer> containNumberInOrderIdList = new ArrayList<>();
                    for (Order o : oList) {
                        String currentOrderId = new String(o.getOrderId());
                        char numberInOrderId_raw[] = currentOrderId.toCharArray();
                        String tmp = "";
                        for (int i = 1; i < numberInOrderId_raw.length; i++) {
                            tmp += String.valueOf(numberInOrderId_raw[i]);
                        }
                        int numberInOrderId = Integer.parseInt(tmp);
                        containNumberInOrderIdList.add(numberInOrderId);
                    }
                    max = containNumberInOrderIdList.get(0);
                    for (int i = 1; i < containNumberInOrderIdList.size(); i++) {
                        if (max < containNumberInOrderIdList.get(i)) {
                            max = containNumberInOrderIdList.get(i);
                        }
                    }
                    int t = 1;
                    while (t < max) {
                        boolean check = true;
                        for (int i = 0; i < containNumberInOrderIdList.size(); i++) {
                            Integer ordinary = containNumberInOrderIdList.get(i);
                            if (t == ordinary) {
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            result = t;
                            break;
                        }
                        t++;
                    }
                    if (result == -1) {
                        result = max + 1;
                    }
                } else {
                    result = 1;
                }
                String orderId = "";
                if (result < 10) {
                    orderId = "O00" + result;
                } else if (result < 100 && result >= 10) {
                    orderId = "O0" + result;
                } else if (result < 1000 && result >= 100) {
                    orderId = "O" + result;
                } // Các trường hợp if khác thì cứ tăng đơn vị lên dần
                
                //-------------------------------------------------
                //-------------- Tạo đơn hàng -------------------//
                // Mặc định ship = 5$
                Order o = new Order(orderId, customerId, orderDate, requiredDate, shippedDate, 5, shipAddress);
                oDao.add(o);
                //----------------- Tạo thông tin đơn hàng ----------------//
                OrderDetail od = new OrderDetail(orderId, p.getProductId(), p.getPrice(), p.getQuantity());
                OrderDetailDAO odDao = new OrderDetailDAO();
                odDao.add(od);
            }
            
            // Sau khi tạo đơn hàng thành công thì xóa giỏ hàng - Clear giỏ hàng từ cookie + session//
            if (c != null) {
                c.setMaxAge(0);
                response.addCookie(c);
            }
            pCart = new HashMap<>();
            session.setAttribute("productCart" + accountId, pCart);
            request.getRequestDispatcher("UserInterface.jsp").forward(request, response);
            //-----------------------------------------------------------//
            
        } else if (pCart == null || pCart.size() == 0) {
            request.setAttribute("msg", "Your cart is empty");
            request.getRequestDispatcher("ViewCart.jsp").forward(request, response);
        }
        // Sau khi tạo đơn hàng thành công thì xóa giỏ hàng - Clear giỏ hàng từ cookie + session//
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
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
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
