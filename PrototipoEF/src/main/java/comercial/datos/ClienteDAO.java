/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercial.datos;

import comercial.dominio.Clientes;
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
public class ClienteDAO {

    private static final String SQL_INSERT = "insert into tbl_cliente values(?,?,?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE tbl_cliente SET Cliente_Nombre=?, Cliente_Identificaicon=?, Cliente_Correo=?, Cliente_Telefono=? ,Cliente_Estado=? WHERE Codigo_Cliente=?";

    private static final String SQL_SELECT = "SELECT * FROM tbl_cliente";

    private static final String SQL_DELETE = "delete from tbl_cliente where Codigo_Cliente = ?";

    public int insert(Clientes clientes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, clientes.getCodigo());
            stmt.setString(2, clientes.getNombre());
            stmt.setString(3, clientes.getDpi());
            stmt.setString(4, clientes.getCorreo());
            stmt.setString(5, clientes.getTel());
            stmt.setString(6, clientes.getEstado());
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

    public int update(Clientes clientes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//          System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, clientes.getNombre());
            stmt.setString(2, clientes.getDpi());
            stmt.setString(3, clientes.getCorreo());
            stmt.setString(4, clientes.getTel());
            stmt.setString(5, clientes.getEstado());
            stmt.setString(6, clientes.getCodigo());

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public List<Clientes> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes cliente = null;
        List<Clientes> clientes = new ArrayList<Clientes>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo_Cliente");
                String nombre = rs.getString("Cliente_Nombre");
                String dpi = rs.getString("Cliente_Identificaicon");
                String correo = rs.getString("Cliente_Correo");
                String telefono = rs.getString("Cliente_Telefono");
                String estado = rs.getString("Cliente_Estado");

                cliente = new Clientes();
                cliente.setCodigo(codigo);
                cliente.setNombre(nombre);
                cliente.setDpi(dpi);
                cliente.setCorreo(correo);
                cliente.setTel(telefono);
                cliente.setEstado(estado);

                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }

    public int delete(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, cliente.getCodigo());
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
