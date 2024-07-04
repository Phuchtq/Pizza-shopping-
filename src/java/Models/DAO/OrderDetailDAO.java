/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Entities.Order;
import Models.Entities.OrderDetail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author pc
 */
public class OrderDetailDAO {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String s = "jdbc:sqlserver://localhost:1433;database=AssignmentDB";
            return DriverManager.getConnection(s, "sa", "12345");
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public List<OrderDetail> getList() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String orderId, productId;
        float price;
        int quantity;
        List<OrderDetail> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from OrderDetail";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                orderId = rs.getString(1);
                productId = rs.getString(2);
                price = rs.getFloat(3);
                quantity = rs.getInt(4);
                list.add(new OrderDetail(orderId, productId, price, quantity));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return list;
    }

    public boolean add(OrderDetail od) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Insert OrderDetail values(?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, od.getOrderId());
            preStm.setString(2, od.getProductId());
            preStm.setFloat(3, od.getPrice());
            preStm.setInt(4, od.getQuantity());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean delete(String id) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Delete OrderDetail where [orderId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, id);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean deleteByOrderId(String orderId) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Delete OrderDetail where [orderId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, orderId);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    
    public boolean deleteByCustomerId(String customerId) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Delete OrderDetail where [customerId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, customerId);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    
    public boolean deleteByProductIdId(String productId) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Delete OrderDetail where [productId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, productId);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean update(OrderDetail od) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update OrderDetail Set [productId]=?, [price]=?, [float]=? Where [orderId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, od.getProductId());
            preStm.setFloat(2, od.getPrice());
            preStm.setInt(3, od.getQuantity());
            preStm.setString(4, od.getOrderId());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public List<OrderDetail> searchByOrderId(String orderId) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail order = null;
        String productId;
        int quantity;
        float price;
        try {
            cnn = getConnection();
            String sql = "Select productId, price, quantity from OrderDetail where [orderId] = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                productId = rs.getString(1);
                price = rs.getFloat(2);
                quantity = rs.getInt(3);
                order = new OrderDetail(orderId, productId, price, quantity);
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public float getSalesByOrderId(Order o) throws SQLException {
        float sum = 0;
        int quantity = 0;
        List<OrderDetail> odList = searchByOrderId(o.getOrderId());
        for (OrderDetail od : odList) {
            sum += od.getQuantity() * od.getPrice();
            quantity += od.getQuantity();
        }
        if (quantity >= 3) {
            sum = sum * 9 / 10;
        }
        return sum;
    }

    public List<Order> sort(List<Order> oList) throws SQLException {
        HashMap<Order, Float> orderSortMap = new HashMap<>();
        for (Order o : oList) {
            float salesAmount = getSalesByOrderId(o);
            orderSortMap.put(o, salesAmount);
        }
        Collections.sort(oList, (o1, o2) -> {
            float sales1 = orderSortMap.get(o1);
            float sales2 = orderSortMap.get(o2);
            return Float.compare(sales2, sales1);
        });
        return oList;
    }

    public List<OrderDetail> searchByProductId(String productId) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail order = null;
        String orderId;
        int quantity;
        float price;
        try {
            cnn = getConnection();
            String sql = "Select orderId, price, quantity from OrderDetail where [productId] like %'" + productId +"'%";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderId = rs.getString(1);
                price = rs.getFloat(2);
                quantity = rs.getInt(3);
                order = new OrderDetail(orderId, productId, price, quantity);
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

}
