package Wheels;

public class Payment {
    private Cliente cliente;
    private static int paymentCount = 1;

    private String formaPagamento;
    private double valorTotal;
    private double desconto;

    public Payment(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void calcularTotalPagamento(Aluguel aluguel) {
        int dias = aluguel.getNumberOfDays();
        double precoBase = aluguel.getBike().calcularCusto(dias);

        // Aplicar desconto conforme a duração
        if (dias >= 30) {
            desconto = 0.25;
        } else if (dias >= 14) {
            desconto = 0.15;
        } else if (dias >= 7) {
            desconto = 0.10;
        } else {
            desconto = 0.0;
        }

        double valorComDesconto = precoBase - (precoBase * desconto);
        valorTotal = valorComDesconto;

        emitirRecibo(aluguel);
    }

    private void emitirRecibo(Aluguel aluguel) {
        System.out.println("===== RECIBO =====");
        System.out.println("Cliente: " + cliente.getName());
        System.out.println("Postcode: " + cliente.getPostcode());
        System.out.println("Bike Nº: " + aluguel.getBike().getNumero());
        System.out.println("Dias alugados: " + aluguel.getNumberOfDays());

        if (desconto > 0) {
            System.out.printf("Desconto aplicado: %.0f%%%n", desconto * 100);
        } else {
            System.out.println("Desconto aplicado: Nenhum");
        }

        System.out.println("Forma de pagamento: " + formaPagamento);
        System.out.printf("Valor total: R$ %.2f%n", valorTotal);
        System.out.println("===================");
    }
}
