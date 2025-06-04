package Wheels.ArquivoEmail;

import Wheels.Cliente;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Properties;

public class EnvioEmail {
    public void enviarEmail() {
        List<Cliente> clientes = Cliente.listaClientes;

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado");
            return;
        }

        String[] opcoes = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            opcoes[i] = cliente.getName() + " <" + cliente.getEmail() + ">";
        }

        String selecionado = (String) JOptionPane.showInputDialog(null,
                "Selecione o cliente para enviar o email:",
                "Enviar Email",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (selecionado == null) {
            return;
        }

        Cliente clienteSelecionado = null;
        for (Cliente c : clientes) {
            String comboString = c.getName() + " <" + c.getEmail() + ">";
            if (comboString.equals(selecionado)) {
                clienteSelecionado = c;
                break;
            }
        }

        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado");
            return;
        }

        enviarEmailPara(clienteSelecionado);
    }

    private void enviarEmailPara(Cliente cliente) {
        String remetente = "mateus.santos@al.infnet.edu.br";
        String senha = "skam jzgw icgu fuva";

        String destinatario = cliente.getEmail();
        String assunto = "Teste de email";
        String corpo = "Olá" + cliente.getName() + ", Recibo do Wheels!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Porta 587 é para envio de email

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);

            // Parte do texto
            MimeBodyPart corpoTexto = new MimeBodyPart();
            corpoTexto.setText(corpo);

            // Parte do anexo
            MimeBodyPart anexo = new MimeBodyPart();
            File arquivo = new File("recibo.pdf");
            DataSource source = new FileDataSource(arquivo);
            anexo.setDataHandler(new DataHandler(source));
            anexo.setFileName(arquivo.getName());

            // Combina as partes
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(corpoTexto);
            multipart.addBodyPart(anexo);

            message.setContent(multipart);
            // Acabou a parte de enviar arquivo junto

            Transport.send(message);
            System.out.println("Mensagem enviada com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
