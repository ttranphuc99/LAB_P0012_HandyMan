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
import phuctt.dtos.IntroductionDTO;

/**
 *
 * @author Thien Phuc
 */
public class IntroductionDAO implements Serializable {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }
    
    public IntroductionDTO getIntro() throws Exception {
        IntroductionDTO dto = null;
        try {
            String sql = "SELECT Title, Description, Image FROM Introduction";
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
            if (rs.next()) {
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String image = rs.getString("Image");
                
                dto = new IntroductionDTO(1, title, description, image);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
