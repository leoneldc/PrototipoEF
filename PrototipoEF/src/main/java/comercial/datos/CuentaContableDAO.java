/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercial.datos;

import comercial.dominio.CuentasContables;
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
public class CuentaContableDAO {

    private static final String SQL_INSERT = "insert into tbl_cuentaContable values(?,?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE tbl_cuentaContable SET CuentaContable_Nombre=?, CuentaContable_Clasificacion=?, CuentaContable_Monto=?, CuentaContable_Estado=? WHERE Codigo_CuentaContable=?";

    private static final String SQL_SELECT = "SELECT * FROM tbl_cuentaContable";

    private static final String SQL_DELETE = "delete from tbl_cuentaContable where Codigo_CuentaContable = ?";

    public int insert(CuentasContables ct) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, ct.getCodigo());
            stmt.setString(2, ct.getNombre());
            stmt.setString(3, ct.getClasf());;
            stmt.setString(4, ct.getDescripcion());
            stmt.setString(5, ct.getEstado());
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

    public int update(CuentasContables ct) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//          System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, ct.getNombre());
            stmt.setString(2, ct.getClasf());
            stmt.setString(3, ct.getDescripcion());
            stmt.setString(4, ct.getEstado());
            stmt.setString(5, ct.getCodigo());

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public List<CuentasContables> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CuentasContables cts = null;
        List<CuentasContables> ct = new ArrayList<CuentasContables>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo_CuentaContable");
                String nombre = rs.getString("CuentaContable_Nombre");
                String cls = rs.getString("CuentaContable_Clasificacion");
                String monto = rs.getString("CuentaContable_Monto");
                String estado = rs.getString("CuentaContable_Estado");
                

                cts = new CuentasContables();
                cts.setCodigo(codigo);
                cts.setNombre(nombre);
                cts.setClasf(cls);
                cts.setDescripcion(monto);
                cts.setEstado(estado);

                ct.add(cts);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return ct;
    }

    public int delete(CuentasContables ct) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, ct.getCodigo());
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
