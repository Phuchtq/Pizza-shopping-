/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Entities.Account;
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
public class AccountDAO {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String s = "jdbc:sqlserver://localhost:1433;database=AssignmentDB";
            return DriverManager.getConnection(s, "sa", "12345");
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public List<Account> getList() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String accountId, userName, password, fullName;
        boolean type;
        List<Account> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Account";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                accountId = rs.getString(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                type = rs.getBoolean(5);
                list.add(new Account(accountId, userName, password, fullName, type));
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

    public List<Account> searchById(String searchValue) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String accountId, userName, password, fullName;
        boolean type;
        List<Account> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Account where [accountId] like '%" + searchValue + "%'";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                accountId = rs.getString(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                type = rs.getBoolean(5);
                list.add(new Account(accountId, userName, password, fullName, type));
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

    public List<Account> searchByName(String searchValues) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String accountId, userName, password, fullName;
        boolean type;
        List<Account> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "Select * from Account where [fullName] like '%" + searchValues + "%'";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                accountId = rs.getString(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                type = rs.getBoolean(5);
                list.add(new Account(accountId, userName, password, fullName, type));
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

    public boolean add(Account a) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Insert Account values(?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, a.getAccountId());
            preStm.setString(2, a.getUserName());
            preStm.setString(3, a.getPassword());
            preStm.setString(4, a.getFullName());
            preStm.setBoolean(5, a.isType());
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
            String sql = "Delete Account where [accountId]=?";
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

    public boolean update(Account a) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update Account Set [userName]=?, [password]=?, [fullName]=?, [type]=? Where [accountId]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, a.getUserName());
            preStm.setString(2, a.getPassword());
            preStm.setString(3, a.getFullName());
            preStm.setBoolean(4, a.isType());
            preStm.setString(5, a.getAccountId());
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
