package com.geekday.account;

import java.sql.*;

/**
 * Created by shreyas on 16/7/16.
 */
public class AccountRepository {
    public static void initialise() {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("create table account(accountid varchar(50))");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:hsqldb:mem:account", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAccount(Account account) {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("insert into account values(?)");
            ps.setString(1, account.getAccountId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Account getAccount(String accountId) {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from account where accountid = ?");
            ps.setString(1, accountId);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return new Account(resultSet.getString("accountid"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
