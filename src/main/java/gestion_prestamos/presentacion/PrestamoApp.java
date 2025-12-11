package gestion_prestamos.presentacion;

import gestion_prestamos.datos.IPrestamoDAO;
import gestion_prestamos.datos.PrestamoDAO;
import gestion_prestamos.dominio.Prestamo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PrestamoApp {
    public static void main(String[] args) {
        prestamoApp();
    }

    private static void prestamoApp(){
        var salir = false;
        var consola = new Scanner(System.in);

        IPrestamoDAO prestamoDAO = new PrestamoDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpcion(opcion, consola, prestamoDAO);
            }catch(Exception e){
                System.out.println("Opción incorrecta: " + e.getMessage());
            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Gestión de Préstamos ***
                1. Listar Préstamos
                2. Buscar Préstamo
                3. Agregar Préstamo
                4. Modificar Préstamo
                5. Eliminar Préstamo
                6. Salir
                Elige una opción:\s""");

        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpcion(int opcion, Scanner consola, IPrestamoDAO prestamoDAO){
        var salir = false;
        switch(opcion){
            case 1 -> {
                System.out.println("--- Listado de Préstamos ---");
                var listarPrestamos = prestamoDAO.listarPrestamos();
                listarPrestamos.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("--- Buscar Préstamo por Id ---");
                System.out.print("Id: ");
                var idPrestamo = Integer.parseInt(consola.nextLine());
                var buscarPrestamo = new Prestamo(idPrestamo);
                var encontrado = prestamoDAO.buscarPrestamoPorId(buscarPrestamo);
                if(encontrado){
                    System.out.println("Préstamo encontrado: " + buscarPrestamo);
                }
                else{
                    System.out.println("Préstamo No encontrado: " + buscarPrestamo);
                }
            }
            case 3 -> {
                System.out.println("--- Agregar Préstamo ---");
                System.out.print("id usuario: ");
                var idUsuario = Integer.parseInt(consola.nextLine());
                System.out.print("Id libro: ");
                var idLibro = Integer.parseInt(consola.nextLine());
                System.out.print("Fecha Préstamo (yyyy-MM-dd): ");
                var fechaStr = consola.nextLine();
                LocalDate fecha_prestamo = null;
                try {
                    fecha_prestamo = LocalDate.parse(fechaStr);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Usa yyyy-MM-dd.");
                    break;
                }
                System.out.print("Fecha devolución (yyyy-MM-dd): ");
                var fechaString = consola.nextLine();
                LocalDate fecha_devolucion = null;
                try {
                    fecha_devolucion = LocalDate.parse(fechaString);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Usa yyyy-MM-dd.");
                    break;
                }
                System.out.print("Número de renovaciones: ");
                var numeroRenovaciones = Integer.parseInt(consola.nextLine());
                var agregarPrestamo = new Prestamo(idUsuario, idLibro, fecha_prestamo, fecha_devolucion, numeroRenovaciones);
                var agregado = prestamoDAO.agregarPrestamo(agregarPrestamo);
                if(agregado){
                    System.out.println("Préstamo agregado: " + agregarPrestamo);
                }
                else{
                    System.out.println("Préstamo No agregado: " + agregarPrestamo);
                }
            }
            case 4 -> {
                System.out.println("--- Modificar Préstamo ---");
                System.out.print("Id del préstamo a modificar: ");
                var idPrestamo = Integer.parseInt(consola.nextLine());
                var buscar = new Prestamo(idPrestamo);
                var encontrado = prestamoDAO.buscarPrestamoPorId(buscar);

                if(encontrado){
                    System.out.print("id usuario: ");
                    var idUsuario = Integer.parseInt(consola.nextLine());
                    System.out.print("Id libro: ");
                    var idLibro = Integer.parseInt(consola.nextLine());

                    System.out.print("Fecha Préstamo (yyyy-MM-dd): ");
                    LocalDate fecha_prestamo;
                    try {
                        fecha_prestamo = LocalDate.parse(consola.nextLine());
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha inválido. Usa yyyy-MM-dd.");
                        break;
                    }

                    System.out.print("Fecha devolución (yyyy-MM-dd): ");
                    LocalDate fecha_devolucion;
                    try {
                        fecha_devolucion = LocalDate.parse(consola.nextLine());
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha inválido. Usa yyyy-MM-dd.");
                        break;
                    }

                    System.out.print("Número de renovaciones: ");
                    var numeroRenovaciones = Integer.parseInt(consola.nextLine());

                    var modificarPrestamo = new Prestamo(idPrestamo, idUsuario, idLibro, fecha_prestamo, fecha_devolucion, numeroRenovaciones);
                    var modificado = prestamoDAO.modificarPrestamo(modificarPrestamo);

                    if(modificado){
                        System.out.println("Préstamo modificado: " + modificarPrestamo);
                    } else {
                        System.out.println("Préstamo NO modificado: " + modificarPrestamo);
                    }

                } else {
                    System.out.println("Registro No encontrado: " + buscar);
                }
            }

            case 5 -> {
                System.out.println("--- Eliminar Préstamo ---");
                System.out.print("Id del préstamo a eliminar: ");
                var idPrestamo = Integer.parseInt(consola.nextLine());
                var buscar = new Prestamo(idPrestamo);
                var encontrado = prestamoDAO.buscarPrestamoPorId(buscar);
                if(encontrado){
                    prestamoDAO.eliminarPrestamo(buscar);
                    System.out.println("Préstamo eliminado: " + buscar);
                }
                else{
                    System.out.println("Registro No encontrado: " + buscar);
                }
            }
            case 6 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opción No reconocida: " + opcion);
        }
        return salir;
    }

}
