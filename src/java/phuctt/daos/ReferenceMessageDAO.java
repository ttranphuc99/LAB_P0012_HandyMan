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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import phuctt.db.DBConnection;
import phuctt.dtos.ReferenceMessageDTO;

/**
 *
 * @author Thien Phuc
 */
public class ReferenceMessageDAO implements Serializable {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }
    
    public int insert(ReferenceMessageDTO dto) throws Exception {
        int id = -1;
        try {
            boolean check;
            
            String sql = "INSERT INTO ReferenceMessage(Name, Email, Website, Message) VALUES(?,?,?,?)";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getEmail());
            ps.setString(3, dto.getWebsite());
            ps.setString(4, dto.getMessage());
            
            check = ps.executeUpdate() > 0;
            
            if (check) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } finally {
            closeConnection();
        }
        return id;
    }
    
    public boolean updateImg(String image, int id) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE ReferenceMessage SET Image = ? WHERE ID = ?";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, image);
            ps.setInt(2, id);
            
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<ReferenceMessageDTO> getList() throws Exception {
        List<ReferenceMessageDTO> result = null;
        try {
            String sql = "SELECT Name, Email, Website, Message, Image, CreatedTime FROM ReferenceMessage ORDER BY CreatedTime DESC";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            String name, email, website, message, image;
            Timestamp createdTime;
            ReferenceMessageDTO dto;
            
            result = new ArrayList<>();
            
            while (rs.next()) {
                name = rs.getString("Name");
                email = rs.getString("Email");
                website = rs.getString("Website");
                message = rs.getString("message");
                image = rs.getString("Image");
                createdTime = rs.getTimestamp("createdTime");
                
                dto = new ReferenceMessageDTO(0, name, email, website, message, image, createdTime);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
