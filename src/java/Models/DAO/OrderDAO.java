/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Entities.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class OrderDAO {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String s = "jdbc:sqlserver://localhost:1433;database=AssignmentDB";
            return DriverManager.getConnection(s, "sa", "12345");
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public List<Order> getList() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String orderId, customerId, shipAddress;
        Date orderDate, requiredDate, shippedDate;
        float freight;
        List<Order> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Orders";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                orderId = rs.getString(1);
                customerId = rs.getString(2);
                orderDate = rs.getDate(3);
                requiredDate = rs.getDate(4);
                shippedDate = rs.getDate(5);
                freight = rs.getFloat(6);
                shipAddress = rs.getString(7);

                list.add(new Order(orderId, customerId, orderDate, requiredDate, shippedDate, freight, shipAddress));
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

    public boolean add(Order o) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Insert Orders values(?,?,?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, o.getOrderId());
            preStm.setString(2, o.getCustomerId());
            preStm.setDate(3, o.getOrderDate());
            preStm.setDate(4, o.getRequiredDate());
            preStm.setDate(5, o.getShippedDate());
            preStm.setFloat(6, o.getFreight());
            preStm.setString(7, o.getShipAddress());
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
            String sql = "Delete Orders where [orderId]=?";
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

    public boolean deleteByCustomerId(String customerId) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Delete Orders where [customerId]=?";
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

    public boolean update(Order o) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update Orders Set [orderDate]=?, [requiredDate]=?, [shippedDate]=?, [freight]=?, [shipAddress]=? Where [orderId]=?";
            preStm = cnn.prepareStatement(sql);
            Date utilOrder = o.getOrderDate();
            Date utilRequire = o.getRequiredDate();
            Date utilShip = o.getShippedDate();

            preStm.setDate(1, o.getOrderDate());
            preStm.setDate(2, o.getRequiredDate());
            preStm.setDate(3, o.getShippedDate());
            preStm.setFloat(4, o.getFreight());
            preStm.setString(5, o.getShipAddress());
            preStm.setString(6, o.getOrderId());
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

    public List<Order> searchByDate(Date t1, Date t2) throws SQLException, Exception {
        LocalDate localDate = t2.toLocalDate();

        LocalDate t3LocalDate = localDate.plusDays(1);

        Date t3 = Date.valueOf(t3LocalDate);
        List<Order> searchList = new ArrayList<>();
        List<Order> orderList = getList();
        orderList.stream().filter((o) -> (o.getOrderDate().after(t1) && o.getOrderDate().before(t3))).forEachOrdered((o) -> {
            orderList.add(o);
        });
        return orderList;
    }
    
    public List<Order> searchByDateRange(Date t1, Date t2) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String orderId, customerId, shipAddress;
        Date orderDate, requiredDate, shippedDate;
        float freight;
        List<Order> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Orders where orderDate between ? and ?";
            preStm.setDate(1, t1);
            preStm.setDate(2, t2);
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                orderId = rs.getString(1);
                customerId = rs.getString(2);
                orderDate = rs.getDate(3);
                requiredDate = rs.getDate(4);
                shippedDate = rs.getDate(5);
                freight = rs.getFloat(6);
                shipAddress = rs.getString(7);
                list.add(new Order(orderId, customerId, orderDate, requiredDate, shippedDate, freight, shipAddress));
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

    public List<Order> searchByCustomerId(String searchValue) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String orderId, customerId, shipAddress;
        Date orderDate, requiredDate, shippedDate;
        float freight;
        List<Order> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Orders where [customerId] like '%" + searchValue + "%'";
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderId = rs.getString(1);
                customerId = rs.getString(2);
                orderDate = rs.getDate(3);
                requiredDate = rs.getDate(4);
                shippedDate = rs.getDate(5);
                freight = rs.getFloat(6);
                shipAddress = rs.getString(7);
                list.add(new Order(orderId, customerId, orderDate, requiredDate, shippedDate, freight, shipAddress));
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

    public List<Order> searchByOrderId(String searchValue) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String orderId, customerId, shipAddress;
        Date orderDate, requiredDate, shippedDate;
        float freight;
        List<Order> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Orders where [orderId] like '%" + searchValue + "%'";
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderId = rs.getString(1);
                customerId = rs.getString(2);
                orderDate = rs.getDate(3);
                requiredDate = rs.getDate(4);
                shippedDate = rs.getDate(5);
                freight = rs.getFloat(6);
                shipAddress = rs.getString(7);
                list.add(new Order(orderId, customerId, orderDate, requiredDate, shippedDate, freight, shipAddress));
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
