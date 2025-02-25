package presentation;

import domain.Snack;
import service.IServicioSnacks;
import service.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks() {

        boolean salir = false;
        var consola = new Scanner(System.in);

        //creamos el objeto para obtener el servicio de snacks (lista)
        IServicioSnacks servicioSnack = new ServicioSnacksLista();

        //creamos la lista de productos de tipo snacks
        List<Snack> productos = new ArrayList<>();

        System.out.println("*** Máquina de snacks ***");
        servicioSnack.mostrarSnacks();

        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion,consola,productos, servicioSnack);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            } finally {
                System.out.println("\n");
            }
        }
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos, IServicioSnacks servicioSnack) {
        var salir =  false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos, servicioSnack);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnack);
            case 4 -> {
                System.out.println("Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opción inválida: " + opcion + "\n");
        }
        return salir;
    }


    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnack) {
        System.out.print("Nombre del snack nuevo: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack nuevo: ");
        var precio = Double.parseDouble(consola.nextLine());

        servicioSnack.agregarSnack(new Snack(nombre,precio));
        System.out.println("Tu snack se ha agregado correctamente\n");
        servicioSnack.mostrarSnacks();
    }

    private static void mostrarTicket(List<Snack> productos) {
        var ticket = "*** Ticket de venta ***";
        var total = 0.0;
        for (var producto : productos){
            ticket += "\n\t- " + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, IServicioSnacks servicioSnack) {
        System.out.print("¿Qué snack desea comprar? (id): ");
        var idSnack = Integer.parseInt(consola.nextLine());

        //validar que el snack existe
        var snackEncontrado = false;
        for (var snack : servicioSnack.getSnacks()) {
            if (idSnack == snack.getIdSnack()){
                //agregamos snack a la lista de productos
                productos.add(snack);
                System.out.println("Ok, snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado){
            System.out.println("Id del snack no encontrad: " + idSnack);
        }
    }


    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                Menú:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar nuevo snack al inventario
                4. Salir
                Elige una opción:\s""");
        //leemos y retornamos la opción seleccionada
        return Integer.parseInt(consola.nextLine());
    }
}
