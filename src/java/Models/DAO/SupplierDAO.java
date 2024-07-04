/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Entities.Supplier;
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
public class SupplierDAO {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String s = "jdbc:sqlserver://localhost:1433;database=AssignmentDB";
            return DriverManager.getConnection(s, "sa", "12345");
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public List<Supplier> getList() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String supplierId, companyName, address, phone;
        List<Supplier> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Supplier";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                supplierId = rs.getString(1);
                companyName = rs.getString(2);
                address = rs.getString(3);
                phone = rs.getString(4);
                list.add(new Supplier(supplierId, companyName, address, phone));
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

    public boolean add(Supplier s) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Insert Supplier values(?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, s.getSupplierId());
            preStm.setString(2, s.getCompanyName());
            preStm.setString(3, s.getAddress());
            preStm.setString(4, s.getPhone());
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
            String sql = "Delete Supplier where [supplierId]=?";
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

    public boolean update(Supplier s) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update Supplier Set [companyName]=?, [address]=?, [phone]=? Where [supplierId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, s.getCompanyName());
            preStm.setString(2, s.getAddress());
            preStm.setString(3, s.getPhone());
            preStm.setString(4, s.getSupplierId());
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
