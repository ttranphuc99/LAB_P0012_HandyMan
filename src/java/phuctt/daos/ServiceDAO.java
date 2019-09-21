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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import phuctt.db.DBConnection;
import phuctt.dtos.ServiceDTO;

/**
 *
 * @author Thien Phuc
 */
public class ServiceDAO implements Serializable {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }
    
    public List<ServiceDTO> getTop3Recent() throws Exception {
        List<ServiceDTO> result = null;
        try {
            String sql = "SELECT TOP 3 Name, Image, Description, PublishDate FROM Service ORDER BY PublishDate DESC";
            
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            result = new ArrayList<>();
            
            String name, image, description;
            Timestamp publishDate;
            ServiceDTO dto;
            
            while (rs.next()) {
                name = rs.getString("Name");
                image = rs.getString("Image");
                description = rs.getString("Description");
                publishDate = rs.getTimestamp("PublishDate");
                
                dto = new ServiceDTO(name, image, description, 0, publishDate);
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<ServiceDTO> getPriceList() throws Exception {
        List<ServiceDTO> result = null;
        try {
            String sql = "SELECT Name, Description, Price FROM Service";
            
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            String name, description;
            float price;
            ServiceDTO dto;
            
            result = new ArrayList<>();
            
            while (rs.next()) {
                name = rs.getString("Name");
                description = rs.getString("Description");
                price = rs.getFloat("Price");
                
                dto = new ServiceDTO();
                dto.setName(name);
                dto.setDescription(description);
                dto.setPrice(price);
                
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
