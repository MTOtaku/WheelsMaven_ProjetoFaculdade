package Wheels;

import java.util.ArrayList;
import java.util.List;

public class Bike {

    public static List<Bike> listaBicicletas = new ArrayList<>();

    private int numero;
    private double deposito;
    private double diaria;

    // Construtor
    public Bike(int numero, double deposito, double diaria) {
        this.numero = numero;
        this.deposito = deposito;
        this.diaria = diaria;
    }

    // Getters
    public int getNumero() {
        return numero;
    }


    public double calcularCusto(int dias) {
        double custo = deposito + (diaria * dias);
        System.out.println("Custo total por " + dias + " dias: R$ " + custo + "\n");
        return custo;
    }

    public String toCSV() {
        return numero + "," + deposito + "," + diaria;
    }

    @Override
    public String toString() {
        return "Bicicleta Nº " + numero + " | Depósito: R$ " + deposito + " | Diária: R$ " + diaria;
    }
}
