package Wheels;

import Wheels.ArquivoEmail.ArquivoPDF;
import Wheels.ArquivoEmail.EnvioEmail;

import javax.swing.*;
import java.awt.*;


public class Menu {


    public static Integer solicitarInteiro(String mensagem) {
        while (true) {
            String input = JOptionPane.showInputDialog(mensagem);
            if (input == null || input.trim().isEmpty()) return null;
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: digite apenas números inteiros.");
            }
        }
    }


    public static Double solicitarDouble(String mensagem) {
        while (true) {
            String input = JOptionPane.showInputDialog(mensagem);
            if (input == null || input.trim().isEmpty()) return null;
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: digite um número decimal válido.");
            }
        }
    }



    public static void criarMenuGUI() {
        Cliente.listaClientes = ArquivoUtils.lerClientesCSV("clientes.csv");
        Bike.listaBicicletas = ArquivoUtils.lerBikesCSV("bikes.csv");

        JFrame frame = new JFrame("Sistema Wheels");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel Cliente
        JPanel clientePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
        JButton btnListarClientes = new JButton("Listar Clientes");
        JButton btnSalvarClientes = new JButton("Salvar Clientes");
        clientePanel.add(btnCadastrarCliente);
        clientePanel.add(btnListarClientes);
        clientePanel.add(btnSalvarClientes);

        // Painel Bicicleta
        JPanel bikePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        JButton btnCadastrarBike = new JButton("Cadastrar Bicicleta");
        JButton btnListarBikes = new JButton("Listar Bicicletas");
        JButton btnSalvarBikes = new JButton("Salvar Bicicletas");
        bikePanel.add(btnCadastrarBike);
        bikePanel.add(btnListarBikes);
        bikePanel.add(btnSalvarBikes);

        // Painel Envio
        JPanel envioPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        JButton btnSalvarPDF = new JButton("Salvar PDF");
        JButton btnEnviarEmail = new JButton("Enviar Email");
        envioPanel.add(btnSalvarPDF);
        envioPanel.add(btnEnviarEmail);

        // Adicionando abas
        tabbedPane.addTab("Cliente", clientePanel);
        tabbedPane.addTab("Bicicleta", bikePanel);
        tabbedPane.addTab("Envio", envioPanel);

        // Sair
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> frame.dispose());

        // Conteúdo principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(btnSair, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);

        btnCadastrarCliente.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome:");
            if (nome == null || nome.trim().isEmpty()) return;

            String cep;
            while (true) {
                cep = JOptionPane.showInputDialog("CEP (formato: 12345-678):");
                if (cep == null) return;
                if (cep.matches("^\\d{5}-\\d{3}$")) break;
                JOptionPane.showMessageDialog(null, "CEP inválido. Use o formato 12345-678.");
            }

            String telefone;
            while (true) {
                telefone = JOptionPane.showInputDialog("Telefone (formato: (12)123456789):");
                if (telefone == null) return;
                if (telefone.matches("^\\(\\d{2}\\)\\d{8,9}$")) break;
                JOptionPane.showMessageDialog(null, "Telefone inválido. Use o formato (12)123456789.");
            }

            String email;
            while (true) {
                email = JOptionPane.showInputDialog("Email:");
                if (email == null) return;
                if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) break;
                JOptionPane.showMessageDialog(null, "Email inválido. Digite um e-mail válido.");
            }

            Cliente cliente = new Cliente(nome, cep, telefone, email);
            Cliente.listaClientes.add(cliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        });



        btnCadastrarBike.addActionListener(e -> {
            Integer numero = solicitarInteiro("Número da Bike:");
            if (numero == null) return;

            for (Bike b : Bike.listaBicicletas) {
                if (b.getNumero() == numero) {
                    JOptionPane.showMessageDialog(null, "Erro: já existe uma bike com esse número.");
                    return;
                }
            }

            Double deposito = solicitarDouble("Depósito:");
            if (deposito == null) return;

            Double diaria = solicitarDouble("Diária:");
            if (diaria == null) return;

            Bike bike = new Bike(numero, deposito, diaria);
            Bike.listaBicicletas.add(bike);
            JOptionPane.showMessageDialog(null, "Bicicleta cadastrada com sucesso!");
        });




        btnListarClientes.addActionListener(e -> {
            if (Cliente.listaClientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (Cliente c : Cliente.listaClientes) {
                sb.append("ID: ").append(c.getCustomerNumber())
                        .append(" | Nome: ").append(c.getName())
                        .append(" | Telefone: ").append(c.getTelephone()).append("\n")
                        .append(" | Email: ").append(c.getEmail()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        });

        btnListarBikes.addActionListener(e -> {
            if (Bike.listaBicicletas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma bicicleta cadastrada.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (Bike b : Bike.listaBicicletas) {
                sb.append(b.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        });

        btnSalvarClientes.addActionListener(e ->{
            StringBuilder csv = new StringBuilder("ID,Nome,Postcode,Telefone,Email\n");
            for (Cliente c : Cliente.listaClientes) {
                csv.append(c.toCSV()).append("\n");
            }
            ArquivoUtils.salvarCSV("clientes.csv", csv.toString());
            JOptionPane.showMessageDialog(null, "Clientes salvos com sucesso!");

        });

        btnSalvarBikes.addActionListener(e -> {
            if (Bike.listaBicicletas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma bicicleta para salvar.");
                return;
            }
            StringBuilder csv = new StringBuilder("Número,Depósito,Diária\n");
            for (Bike b : Bike.listaBicicletas) {
                csv.append(b.toCSV()).append("\n");
            }
            ArquivoUtils.salvarCSV("bikes.csv", csv.toString());
            JOptionPane.showMessageDialog(null, "Bicicletas salvas com sucesso!");
        });

        btnSalvarPDF.addActionListener(e -> {
            if (Cliente.listaClientes.isEmpty() || Bike.listaBicicletas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastre ao menos um cliente e uma bicicleta.");
                return;
            }

            Cliente cliente = (Cliente) JOptionPane.showInputDialog(
                    null,
                    "Selecione o cliente:",
                    "Cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Cliente.listaClientes.toArray(),
                    Cliente.listaClientes.get(0)
            );
            if (cliente == null) return;  // Cancelado

            Bike bike = (Bike) JOptionPane.showInputDialog(
                    null,
                    "Selecione a bicicleta:",
                    "Bicicleta",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Bike.listaBicicletas.toArray(),
                    Bike.listaBicicletas.getFirst()
            );
            if (bike == null) return;  // Cancelado

            String[] opcoesPagamento = {"Dinheiro", "Cartão", "Pix"};
            String formaPagamento = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha a forma de pagamento:",
                    "Forma de Pagamento",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoesPagamento,
                    opcoesPagamento[0]
            );
            if (formaPagamento == null) return;  // Cancelado

            Integer dias = solicitarInteiro("Informe a quantidade de dias de aluguel:");
            if (dias == null) return;

            try {

                Aluguel aluguel = new Aluguel(new java.util.Date(), dias, bike, cliente);
                Payment pagamento = new Payment(cliente);
                pagamento.setFormaPagamento(formaPagamento);
                pagamento.calcularTotalPagamento(aluguel);

                ArquivoPDF pdf = new ArquivoPDF();
                pdf.gerarPDF(
                        cliente.getName(),
                        "Bike Nº " + bike.getNumero(),
                        dias,
                        formaPagamento,
                        pagamento.getDesconto(),
                        pagamento.getValorTotal()
                );

                JOptionPane.showMessageDialog(null, "PDF gerado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: número de dias inválido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar PDF: " + ex.getMessage());
            }
        });


        btnEnviarEmail.addActionListener(e -> {
            EnvioEmail envioEmail = new EnvioEmail();
            envioEmail.enviarEmail();
        });


        btnSair.addActionListener(e -> frame.dispose());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
