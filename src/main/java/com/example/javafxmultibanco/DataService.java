package com.example.javafxmultibanco;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataService {

    private static final String connStr = "jdbc:sqlite:database.db";

    /**
     * Connect to the database
     * @return the Connection object
     */
    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static boolean login(String numero, String pin) throws SQLException {
        ResultSet resultSet;
        String query = "SELECT * FROM cartao WHERE numero = ? AND pin = ?";
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, numero);
            pstmt.setString(2, pin);

            resultSet = pstmt.executeQuery();

            Conta conta = new Conta();
            conta.setId(resultSet.getInt("id"));
            conta.setNumero(resultSet.getInt("numero"));
            conta.setPin(resultSet.getInt("pin"));
            conta.setNome(resultSet.getString("nome"));
            conta.setSaldo(resultSet.getDouble("saldo"));
            ContaHolder holder = ContaHolder.getInstance();
            holder.setConta(conta);

            return resultSet.next();

        }catch (SQLException e) {
            System.out.println("Ocorreu ao tentar fazer o login: " + e.getMessage());
            return false;
        }
    }

    public static Conta getById (int id) throws SQLException {
        ResultSet resultSet;
        String query = "SELECT * FROM cartao WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            Conta conta = new Conta();
            conta.setId(resultSet.getInt("id"));
            conta.setNumero(resultSet.getInt("numero"));
            conta.setPin(resultSet.getInt("pin"));
            conta.setNome(resultSet.getString("nome"));
            conta.setSaldo(resultSet.getDouble("saldo"));

            return conta;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar o cartão com o id: "
                    + id + " : " + e.getMessage());
            return null;
        }
    }

    public static void updateSaldo (int id, double saldo) throws SQLException {

        String sql = "UPDATE cartao SET saldo = ? WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, saldo);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao atualizar o saldo do cartão com o id: "
                    + id + " : " + e.getMessage());
        }

    }

    /**
     * Insere movimento na base de dados
     * @param cartao_id ‘id’ do cartão
     * @param descricao descrição do movimento
     * @param valor valor
     * @param tipo tipo de movimento
     */
    public static void addMovimento(int cartao_id, String descricao, double valor, int tipo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String data = sdf.format(timestamp);

        String sql = "INSERT INTO movimentos (cartao_id, data, descricao, valor, tipo) VALUES (?,?,?,?,?) ";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cartao_id);
            pstmt.setString(2, data);
            pstmt.setString(3, descricao);
            pstmt.setDouble(4, valor);
            pstmt.setInt(5, tipo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao atualizar os movimentos do cartão com o id: "
                    + cartao_id + " : " + e.getMessage());
        }
    }

    public static List<Movimento> getMovimentos() {
        int cartao_id = ContaHolder.getInstance().getConta().getId();
        ResultSet resultSet;
        String query = "SELECT * FROM movimentos WHERE cartao_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cartao_id);

            resultSet = pstmt.executeQuery();
            List<Movimento> movimentos = new ArrayList<>();

            while(resultSet.next()) {
                Movimento movimento = new Movimento();
                movimento.setId(resultSet.getInt("id"));
                movimento.setCartao_id(resultSet.getInt("cartao_id"));
                movimento.setData(resultSet.getString("data"));
                movimento.setDescricao(resultSet.getString("descricao"));
                movimento.setValor(resultSet.getDouble("valor"));
                movimento.setTipo(resultSet.getInt("tipo"));

                movimentos.add(movimento);
            }

            return movimentos;

        }catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar o cartão com o id: "
                    + cartao_id + " : " + e.getMessage());
            return null;
        }
    }

}
