package gestion_prestamos.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private int id_prestamo;
    private int id_usuario;
    private int id_libro;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_devolucion;
    private int renovaciones;

    public Prestamo(){}

    public Prestamo(int id_prestamo){
        this.id_prestamo = id_prestamo;
    }

    public Prestamo(int id_usuario, int id_libro, LocalDate fecha_prestamo, LocalDate fecha_devolucion,
                    int renovaciones){
        this.id_usuario = id_usuario;
        this.id_libro = id_libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.renovaciones = renovaciones;
    }

    public Prestamo(int id_prestamo, int id_usuario, int id_libro, LocalDate fecha_prestamo, LocalDate fecha_devolucion,
                    int renovaciones){
        this(id_usuario,id_libro,fecha_prestamo,fecha_devolucion,renovaciones);
        this.id_prestamo = id_prestamo;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public LocalDate getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(LocalDate fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public int getRenovaciones() {
        return renovaciones;
    }

    public void setRenovaciones(int renovaciones) {
        this.renovaciones = renovaciones;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id_prestamo=" + id_prestamo +
                ", id_usuario=" + id_usuario +
                ", id_libro=" + id_libro +
                ", fecha_prestamo=" + fecha_prestamo +
                ", fecha_devolucion=" + fecha_devolucion +
                ", renovaciones=" + renovaciones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return id_prestamo == prestamo.id_prestamo && id_usuario == prestamo.id_usuario && id_libro == prestamo.id_libro && renovaciones == prestamo.renovaciones && Objects.equals(fecha_prestamo, prestamo.fecha_prestamo) && Objects.equals(fecha_devolucion, prestamo.fecha_devolucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_prestamo, id_usuario, id_libro, fecha_prestamo, fecha_devolucion, renovaciones);
    }
}
