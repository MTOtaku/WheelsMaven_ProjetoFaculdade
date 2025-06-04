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

    public double getDeposito() {
        return deposito;
    }

    public double getDiaria() {
        return diaria;
    }

    // Buscar bicicleta por número
    public static Bike buscarPorNumero(int numero) {
        for (Bike b : listaBicicletas) {
            if (b.getNumero() == numero) {
                System.out.println("Bicicleta com o número '" + numero + "' encontrada.\n");
                return b;
            }
        }
        System.out.println("Bicicleta com o número '" + numero + "' não encontrada.\n");
        return null;
    }

    // Mostrar detalhes
    public void mostrarDetalhes() {
        System.out.println("Detalhes da Bicicleta Nº " + numero);
        System.out.println("Depósito: " + deposito);
        System.out.println("Diária: " + diaria + "\n");
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
