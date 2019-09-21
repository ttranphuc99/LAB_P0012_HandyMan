/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import phuctt.db.DBConnection;
import phuctt.dtos.ContactMessageDTO;

/**
 *
 * @author Thien Phuc
 */
public class ContactMessageDAO implements Serializable{
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }
    
    public boolean insert(ContactMessageDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO ContactMessage(Name, Email, Message) VALUES(?,?,?)";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getEmail());
            ps.setString(3, dto.getMessage());
            
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
