/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Entities.Product;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class CartUtil {

    public HashMap<String, Product> getCartFromCookie(Cookie cookie) {
        HashMap<String, Product> cart = null;
        String[] arrItemDetail;
        String productId, productName, supplierId, categoryId, productImage;
        float price;
        int quantity;
        Product item;
        Base64.Decoder base64Decoder = Base64.getDecoder();
        cart = new HashMap();
        String encodedString = new String(base64Decoder.decode(cookie.getValue().getBytes()));
        String[] itemsList = encodedString.split("\\|");
        for (String strItem : itemsList) {
            arrItemDetail = strItem.split(",");
            productId = arrItemDetail[0].trim();
            productName = arrItemDetail[1].trim();
            supplierId = arrItemDetail[2].trim();
            categoryId = arrItemDetail[3].trim();
            quantity = Integer.parseInt(arrItemDetail[4].trim());
            price = Float.parseFloat(arrItemDetail[5].trim());
            productImage = arrItemDetail[6].trim();
            item = new Product(productId, productName, supplierId, categoryId, quantity, price, productImage);
            cart.put(productId, item);
        }
        return cart;
    }

    public Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] arrCookies = request.getCookies();
        if (arrCookies != null) {
            for (Cookie cookie : arrCookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void saveCartToCookie(HttpServletRequest request, HttpServletResponse response, String strItemsInCart, String accountId) {
        String cookieName = "productCart" + accountId;
        Cookie cookieCart = getCookieByName(request, cookieName);
        if (cookieCart != null) {
            cookieCart.setValue(strItemsInCart);
        } else {
            cookieCart = new Cookie(cookieName, strItemsInCart);
        }
        cookieCart.setMaxAge(120);
        response.addCookie(cookieCart);
    }

    public void deleteCartToCookie(HttpServletRequest request, HttpServletResponse response, String accountId) {
        String cookieName = "productCart" + accountId;
        Cookie cookieCart = getCookieByName(request, cookieName);
        if (cookieCart != null) {
            cookieCart.setMaxAge(0);
            response.addCookie(cookieCart);
        }
    }

    public String convertCartToString(List<Product> itemsList) {
        StringBuilder strItemsInCart = new StringBuilder();
        for (Product item : itemsList) {
            strItemsInCart.append(item + "|");
        }
        Base64.Encoder base64Encoder = Base64.getEncoder();
        String encodedString = base64Encoder.encodeToString(strItemsInCart.toString().getBytes());
        return encodedString;
    }

    public List<String> cookieName(HttpServletRequest request) {
        Cookie[] arrCookies = request.getCookies();
        List<String> cookieL = new ArrayList<>();
        if (arrCookies != null) {
            for (Cookie cookie : arrCookies) {
                cookieL.add(cookie.getName());
            }
        }
        return cookieL;
    }
}
