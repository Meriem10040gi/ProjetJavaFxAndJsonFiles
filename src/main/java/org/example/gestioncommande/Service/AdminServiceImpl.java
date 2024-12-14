package org.example.gestioncommande.Service;

import org.example.gestioncommande.Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminServiceI{
    DBConnection DB = new DBConnection();
    @Override
    public boolean isExist(String email,String password) throws SQLException {
        DB.connecte("CommandeClient","root","");
        String checkid = "SELECT COUNT(*) FROM Admin WHERE email Like ? AND password Like ?";
        PreparedStatement checkId = DB.connexion.prepareStatement(checkid);
        checkId.setString(1, email);
        checkId.setString(2, password);
        ResultSet rs = checkId.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) { return false;}
        return true;
    }
}
