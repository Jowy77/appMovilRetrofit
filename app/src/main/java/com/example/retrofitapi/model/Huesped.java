package com.example.retrofitapi.model;

import java.util.ArrayList;

public class Huesped {
    public int idHuesped;
    public String nombre;
    public String telefono;
    public String fotoPerfil;
    public ArrayList<Reservas> reservas;

    public Huesped(String nombre, String telefono, String fotoPerfil){
        this.nombre = nombre;
        this.telefono = telefono;
        this.fotoPerfil = fotoPerfil;
    }

}

class Reservas{
    public int idReserva;
    public String fechaEntrada;
    public String fechaSalida;
    public int valor;
    public String formaPago;
}
