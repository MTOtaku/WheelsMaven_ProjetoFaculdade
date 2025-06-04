package Wheels;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {

    public static void salvarCSV(String nomeArquivo, String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(conteudo);
            System.out.println("Arquivo CSV salvo com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }

    public static List<Cliente> lerClientesCSV(String nomeArquivo) {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 5) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String postcode = dados[2];
                    String telefone = dados[3];
                    String email = dados[4];
                    Cliente c = new Cliente(nome, postcode, telefone, email);
                    c.setCustomerID(id);
                    clientes.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler clientes CSV: " + e.getMessage());
        }
        return clientes;
    }

    public static List<Bike> lerBikesCSV(String nomeArquivo) {
        List<Bike> bikes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 3) {
                    int numero = Integer.parseInt(dados[0]);
                    double deposito = Double.parseDouble(dados[1]);
                    double diaria = Double.parseDouble(dados[2]);
                    Bike b = new Bike(numero, deposito, diaria);
                    bikes.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler bicicletas CSV: " + e.getMessage());
        }
        return bikes;
    }
}

