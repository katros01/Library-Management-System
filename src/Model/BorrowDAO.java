/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    protected Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    public void addBorrow(Borrow borrow) throws SQLException {
        String sql = "INSERT INTO borrower (userId, bookId, borrowDate, returnDate, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, borrow.getUserId());
            stmt.setLong(2, borrow.getBookId());
            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(4, borrow.getReturnDate() != null ? new java.sql.Date(borrow.getReturnDate().getTime()) : null);
            stmt.setString(5, borrow.getStatus());
            stmt.executeUpdate();
        }
    }

   
    public Borrow getBorrow(int id) throws SQLException {
        String sql = "SELECT * FROM borrower WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Borrow(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getLong("bookId"),
                        rs.getDate("borrowDate"),
                        rs.getDate("returnDate"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    
    public void updateBorrow(Borrow borrow) throws SQLException {
        String sql = "UPDATE borrower SET userId = ?, bookId = ?, borrowDate = ?, returnDate = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, borrow.getUserId());
            stmt.setLong(2, borrow.getBookId());
            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(4, borrow.getReturnDate() != null ? new java.sql.Date(borrow.getReturnDate().getTime()) : null);
            stmt.setString(5, borrow.getStatus());
            stmt.setLong(6, borrow.getId());
            stmt.executeUpdate();
        }
    }

   
    public void deleteBorrow(int id) throws SQLException {
        String sql = "DELETE FROM borrower WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    
    public List<Borrow> getAllBorrows() throws SQLException {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrower";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Borrow borrow = new Borrow(
                    rs.getLong("id"),
                    rs.getLong("userId"),
                    rs.getLong("bookId"),
                    rs.getDate("borrowDate"),
                    rs.getDate("returnDate"),
                    rs.getString("status")
                );
                borrow.setId(rs.getLong("id"));
                borrows.add(borrow);
            }
        }
        return borrows;
    }
    
    public List<Borrow> getBorrowsByUserId(Long userId) throws SQLException {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrower WHERE userId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Borrow borrow = new Borrow(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getLong("bookId"),
                        rs.getDate("borrowDate"),
                        rs.getDate("returnDate"),
                        rs.getString("status")
                    );
                    borrows.add(borrow);
                }
            }
        }
        return borrows;
    }
}
