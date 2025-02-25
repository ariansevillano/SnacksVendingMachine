package service;

import domain.Snack;

import java.util.List;

public interface IServicioSnacks {
    void agregarSnack(Snack snack);
    void mostrarSnacks();
    List<Snack> getSnacks();
}
