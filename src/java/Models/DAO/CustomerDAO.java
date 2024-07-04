/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Entities.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class CustomerDAO {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String s = "jdbc:sqlserver://localhost:1433;database=AssignmentDB";
            return DriverManager.getConnection(s, "sa", "12345");
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public List<Customer> getList() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String customerId, password, contactName, address, phone;
        List<Customer> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Customer";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                customerId = rs.getString(1);
                password = rs.getString(2);
                contactName = rs.getString(3);
                address = rs.getString(4);
                phone = rs.getString(5);
                list.add(new Customer(customerId, password, contactName, address, phone));
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

    public List<Customer> searchById(String searchValue) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String customerId, password, contactName, address, phone;
        List<Customer> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Customer where [customerId] like '%" + searchValue + "%'";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                customerId = rs.getString(1);
                password = rs.getString(2);
                contactName = rs.getString(3);
                address = rs.getString(4);
                phone = rs.getString(5);
                list.add(new Customer(customerId, password, contactName, address, phone));
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

    public List<Customer> searchByName(String searchValues) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String customerId, password, contactName, address, phone;
        List<Customer> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Customer where [contactName] like '%" + searchValues + "%'";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                customerId = rs.getString(1);
                password = rs.getString(2);
                contactName = rs.getString(3);
                address = rs.getString(4);
                phone = rs.getString(5);
                list.add(new Customer(customerId, password, contactName, address, phone));
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

    public boolean add(Customer c) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Insert Customer values(?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, c.getCustomerId());
            preStm.setString(2, c.getPassword());
            preStm.setString(3, c.getContactName());
            preStm.setString(4, c.getAddress());
            preStm.setString(5, c.getPhone());
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
            String sql = "Delete Customer where [customerId]=?";
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

    public boolean update(Customer c) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update Customer Set [password]=?, [contactName]=?, [address]=?, [phone]=? Where [customerId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, c.getPassword());
            preStm.setString(2, c.getContactName());
            preStm.setString(3, c.getAddress());
            preStm.setString(4, c.getPhone());
            preStm.setString(5, c.getCustomerId());
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
}
