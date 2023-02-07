package com.example.javafxmultibanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel(){
        connection = SqliteConnection.getConnetion();
        if(connection == null) System.exit(1);
    }

    public boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLoggedIn(String numero, String pin) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from cartao where numero = ? and pin = ?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, numero);
            preparedStatement.setString(2, pin);

            resultSet = preparedStatement.executeQuery();

            Conta conta = new Conta();
            conta.setId(resultSet.getInt("id"));
            conta.setNumero(resultSet.getInt("numero"));
            conta.setPin(resultSet.getInt("pin"));
            conta.setNome(resultSet.getString("nome"));
            conta.setSaldo(resultSet.getDouble("saldo"));
            ContaHolder holder = ContaHolder.getInstance();
            holder.setConta(conta);

            return resultSet.next();

        }catch (Exception e){
            return false;
        }
        finally{
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
    }
}
