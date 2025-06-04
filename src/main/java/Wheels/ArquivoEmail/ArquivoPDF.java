package Wheels.ArquivoEmail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Random;

public class ArquivoPDF {

    public void gerarPDF(String nomeCliente, String nomeBike, int diasAlugados,
                         String formaPagamento, double desconto, double valorTotal) {

        Document documento = new Document();

        String[] imagens = {
                "src/main/java/Wheels/imagens/teste1.png",
                "src/main/java/Wheels/imagens/teste2.png",
                "src/main/java/Wheels/imagens/teste3.png",
                "src/main/java/Wheels/imagens/teste4.png",
                "src/main/java/Wheels/imagens/teste5.png",
                "src/main/java/Wheels/imagens/teste6.png",
                "src/main/java/Wheels/imagens/teste7.png",
                "src/main/java/Wheels/imagens/teste8.png",
                "src/main/java/Wheels/imagens/teste9.png",
                "src/main/java/Wheels/imagens/teste10.png"
        };

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("recibo.pdf"));
            documento.open();

            documento.add(new Paragraph("===== RECIBO DE ALUGUEL DE BICICLETA ====="));
            documento.add(new Paragraph("Cliente: " + nomeCliente));
            documento.add(new Paragraph("Bicicleta: " + nomeBike));
            documento.add(new Paragraph("Dias alugados: " + diasAlugados));
            documento.add(new Paragraph("Forma de pagamento: " + formaPagamento));

            if (desconto > 0) {
                documento.add(new Paragraph(String.format("Desconto aplicado: %.0f%%", desconto * 100)));
            } else {
                documento.add(new Paragraph("Desconto aplicado: Nenhum"));
            }

            documento.add(new Paragraph(String.format("Valor total: R$ %.2f", valorTotal)));
            documento.add(new Paragraph("\n"));

            documento.add(new Paragraph("Imagem aleatória da bike:"));

            // Adiciona imagem aleatória
            Random rand = new Random();
            String imagemEscolhida = imagens[rand.nextInt(imagens.length)];
            Image imagem = Image.getInstance(imagemEscolhida);
            imagem.scaleToFit(500, 300);
            imagem.setAlignment(Element.ALIGN_CENTER);
            documento.add(imagem);

            documento.close();
            System.out.println("Recibo PDF gerado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
