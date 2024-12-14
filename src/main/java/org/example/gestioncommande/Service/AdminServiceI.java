package org.example.gestioncommande.Service;

import java.sql.SQLException;

public interface AdminServiceI {
    boolean isExist(String e,String p) throws SQLException;
}
