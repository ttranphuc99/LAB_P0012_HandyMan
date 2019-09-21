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
import phuctt.dtos.ContactInfoDTO;

/**
 *
 * @author Thien Phuc
 */
public class ContactInfoDAO implements Serializable {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }
    
    public ContactInfoDTO getInfo() throws Exception {
        ContactInfoDTO dto = null;
        try {
            String sql = "SELECT Address, City, Country, Tel, Email, Image FROM ContactInfo";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String country = rs.getString("Country");
                String tel = rs.getString("Tel");
                String email = rs.getString("Email");
                String image = rs.getString("Image");
                
               dto = new ContactInfoDTO(0, address, city, country, tel, email, image);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
