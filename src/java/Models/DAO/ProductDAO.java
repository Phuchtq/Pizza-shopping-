/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Entities.Product;
import Models.Entities.Product;
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
public class ProductDAO {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String s = "jdbc:sqlserver://localhost:1433;database=AssignmentDB";
            return DriverManager.getConnection(s, "sa", "12345");
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public List<Product> getList() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String productId, productName, supplierId, categoryId, productImage;
        float price;
        int quantity;
        List<Product> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Product";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productId = rs.getString(1);
                productName = rs.getString(2);
                supplierId = rs.getString(3);
                categoryId = rs.getString(4);
                quantity = rs.getInt(5);
                price = rs.getFloat(6);
                productImage = rs.getString(7);
                list.add(new Product(productId, productName, supplierId, categoryId, quantity, price, productImage));
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

    public List<Product> searchById(String searchValue) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String productId, productName, supplierId, categoryId, productImage;
        float price;
        int quantity;
        List<Product> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Product where [productId] like '%" + searchValue + "%'";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productId = rs.getString(1);
                productName = rs.getString(2);
                supplierId = rs.getString(3);
                categoryId = rs.getString(4);
                quantity = rs.getInt(5);
                price = rs.getFloat(6);
                productImage = rs.getString(7);
                list.add(new Product(productId, productName, supplierId, categoryId, quantity, price, productImage));
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

    public List<Product> searchByName(String searchValues) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String productId, productName, supplierId, categoryId, productImage;
        float price;
        int quantity;
        List<Product> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Product where [productName] like '%" + searchValues + "%'";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productId = rs.getString(1);
                productName = rs.getString(2);
                supplierId = rs.getString(3);
                categoryId = rs.getString(4);
                quantity = rs.getInt(5);
                price = rs.getFloat(6);
                productImage = rs.getString(7);
                list.add(new Product(productId, productName, supplierId, categoryId, quantity, price, productImage));
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
    
    public List<Product> searchByCategoryId(String searchValue) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String productId, productName, supplierId, categoryId, productImage;
        float price;
        int quantity;
        List<Product> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Product where [categoryId] like '%" + searchValue + "%'";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productId = rs.getString(1);
                productName = rs.getString(2);
                supplierId = rs.getString(3);
                categoryId = rs.getString(4);
                quantity = rs.getInt(5);
                price = rs.getFloat(6);
                productImage = rs.getString(7);
                list.add(new Product(productId, productName, supplierId, categoryId, quantity, price, productImage));
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

    public List<Product> searchInRange(float min, float max) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String productId, productName, supplierId, categoryId, productImage;
        float price;
        int quantity;
        List<Product> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Product where [price] >= " + min + " and [price] <= " + max;
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productId = rs.getString(1);
                productName = rs.getString(2);
                supplierId = rs.getString(3);
                categoryId = rs.getString(4);
                quantity = rs.getInt(5);
                price = rs.getFloat(6);
                productImage = rs.getString(7);
                list.add(new Product(productId, productName, supplierId, categoryId, quantity, price, productImage));
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
    
    public boolean add(Product p) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Insert Product values(?,?,?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, p.getProductId());
            preStm.setString(2, p.getProductName());
            preStm.setString(3, p.getSupplierId());
            preStm.setString(4, p.getCategoryId());
            preStm.setInt(5, p.getQuantity());
            preStm.setFloat(6, p.getPrice());
            preStm.setString(7, p.getProductImage());
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
            String sql = "Delete Product where [productId]=?";
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

    public boolean update(Product p) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update Product Set [productName]=?, [quantity]=?, [price]=?, [productImage]=? Where [productId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, p.getProductName());
            preStm.setInt(2, p.getQuantity());
            preStm.setFloat(3, p.getPrice());
            preStm.setString(4, p.getProductImage());
            preStm.setString(5, p.getProductId());
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
