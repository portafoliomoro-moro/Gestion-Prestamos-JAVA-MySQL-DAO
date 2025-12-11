package gestion_prestamos.datos;

import gestion_prestamos.dominio.Prestamo;

import java.util.List;

public interface IPrestamoDAO {
    List<Prestamo> listarPrestamos();
    boolean buscarPrestamoPorId(Prestamo prestamo);
    boolean agregarPrestamo(Prestamo prestamo);
    boolean modificarPrestamo(Prestamo prestamo);
    boolean eliminarPrestamo(Prestamo prestamo);
}
