package gestion_prestamos.datos;

import gestion_prestamos.dominio.Prestamo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static gestion_prestamos.conexion.Conexion.getConexion;

public class PrestamoDAO implements IPrestamoDAO{
    @Override
    public List<Prestamo> listarPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM prestamos ORDER BY id_prestamo";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var prestamo = new Prestamo();
                prestamo.setId_prestamo(rs.getInt("id_prestamo"));
                prestamo.setId_usuario(rs.getInt("id_usuario"));
                prestamo.setId_libro(rs.getInt("id_libro"));
                prestamo.setFecha_prestamo(rs.getDate("fecha_prestamo").toLocalDate());
                prestamo.setFecha_devolucion(rs.getDate("fecha_devolucion").toLocalDate());
                prestamo.setRenovaciones(rs.getInt("renovaciones"));
                prestamos.add(prestamo);
            }
        }catch(Exception e){
            System.out.println("Error al listar los préstamos: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return prestamos;
    }

    @Override
    public boolean buscarPrestamoPorId(Prestamo prestamo) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM prestamos WHERE id_prestamo=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, prestamo.getId_prestamo());
            rs = ps.executeQuery();
            if(rs.next()){
                prestamo.setId_prestamo(rs.getInt("id_prestamo"));
                prestamo.setId_usuario(rs.getInt("id_usuario"));
                prestamo.setId_libro(rs.getInt("id_libro"));
                prestamo.setFecha_prestamo(rs.getDate("fecha_prestamo").toLocalDate());
                prestamo.setFecha_devolucion(rs.getDate("fecha_devolucion").toLocalDate());
                prestamo.setRenovaciones(rs.getInt("renovaciones"));
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al buscar préstamo: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarPrestamo(Prestamo prestamo) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion, renovaciones)" +
                " VALUES (?,?,?,?,?)";
        try{
            // Para fecha prestamo
            LocalDate fechaPrestamo = prestamo.getFecha_prestamo();
            Date fecha_prestamo = Date.valueOf(fechaPrestamo);
            //Para fecha devolucion
            LocalDate fechaDevolucion = prestamo.getFecha_devolucion();
            Date fecha_devolucion = Date.valueOf(fechaDevolucion);

            ps = con.prepareStatement(sql);
            ps.setInt(1, prestamo.getId_usuario());
            ps.setInt(2, prestamo.getId_libro());
            ps.setDate(3, fecha_prestamo);
            ps.setDate(4, fecha_devolucion);
            ps.setInt(5, prestamo.getRenovaciones());
            ps.execute();
            return true;

        }catch(Exception e){
            System.out.println("Error al agregar préstamo: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarPrestamo(Prestamo prestamo) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE prestamos SET id_usuario=?, id_libro=?, fecha_prestamo=?, " +
                "fecha_devolucion=?, renovaciones=? WHERE id_prestamo=?";
        try{
            // Para fecha prestamo
            LocalDate fechaPrestamo = prestamo.getFecha_prestamo();
            Date fecha_prestamo = Date.valueOf(fechaPrestamo);
            //Para fecha devolucion
            LocalDate fechaDevolucion = prestamo.getFecha_devolucion();
            Date fecha_devolucion = Date.valueOf(fechaDevolucion);

            ps = con.prepareStatement(sql);
            ps.setInt(1, prestamo.getId_usuario());
            ps.setInt(2, prestamo.getId_libro());
            ps.setDate(3, fecha_prestamo);
            ps.setDate(4, fecha_devolucion);
            ps.setInt(5, prestamo.getRenovaciones());
            ps.setInt(6, prestamo.getId_prestamo());
            ps.execute();
            return true;

        }catch(Exception e){
            System.out.println("Error al modificar préstamo: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarPrestamo(Prestamo prestamo) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "DELETE FROM prestamos WHERE id_prestamo=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, prestamo.getId_prestamo());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar prestamo: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

}
