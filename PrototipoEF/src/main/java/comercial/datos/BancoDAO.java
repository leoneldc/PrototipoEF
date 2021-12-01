/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comercial.datos;

import comercial.dominio.Bancos;
import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LeonelDominguez.exe c:
 * @github https://github.com/leoneldc
 */

public class BancoDAO {
    
    private static final String SQL_INSERT = "insert into tbl_banco values(?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE tbl_banco SET Banco_Nombre=?, Banco_Ubicacion=?, Banco_Estado=? WHERE Codigo_Banco=?";
    
    private static final String SQL_SELECT = "SELECT * FROM tbl_banco";

    private static final String SQL_DELETE = "delete from tbl_banco where Codigo_Banco = ?";

    public int insert(Bancos bancos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, bancos.getCodigo());
            stmt.setString(2, bancos.getNombre());
            stmt.setString(3, bancos.getUbicacion());
            stmt.setString(4, bancos.getEstado());
            //System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            //System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Bancos bancos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//          System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, bancos.getNombre());
            stmt.setString(2, bancos.getUbicacion());
            stmt.setString(3, bancos.getEstado());
            stmt.setString(4, bancos.getCodigo());

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public List<Bancos> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bancos banco = null;
        List<Bancos> bancos = new ArrayList<Bancos>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo_Banco");
                String nombre = rs.getString("Banco_Nombre");
                String ubicacion = rs.getString("Banco_Ubicacion");
                String estado = rs.getString("Banco_Estado");

                banco = new Bancos();
                banco.setCodigo(codigo);
                banco.setNombre(nombre);
                banco.setUbicacion(ubicacion);
                banco.setEstado(estado);

                bancos.add(banco);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bancos;
    }

    public int delete(Bancos bancos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, bancos.getCodigo());
            rows = stmt.executeUpdate();
            //System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
