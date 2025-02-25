package service;

import domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks {
    private static final List<Snack> snacks;

    //bloque estático incializador
    //ya que al ser todo estático no necesita constructor

    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas Fritas",70));
        snacks.add(new Snack("Refresco",50));
        snacks.add(new Snack("Mani confitado",30));
    }

    public void agregarSnack(Snack snack) {
        snacks.add(snack);
    }

    public void mostrarSnacks() {
        String inventarioSnacks = "";
        for (Snack snack : snacks) {
            inventarioSnacks += snack.toString() + "\n";
        }

        System.out.println("--- ServicioSnacksLista en el inventario---");
        System.out.println(inventarioSnacks);
    }

    public List<Snack> getSnacks(){
        return snacks;
    }


}
