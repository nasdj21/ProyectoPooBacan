/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import java.util.Properties;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
/**
 *
 * @author nicolassierra
 */
public class Utilitaria {
    
    private Utilitaria(){}
    
    public static int nextID(String nomfile){
        int id = 0;
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                id = Integer.parseInt(tokens[0]);
            }

                
            
        }
        catch (Exception e){}
        return id+1;
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
 
        
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash)
    {
        
        BigInteger number = new BigInteger(1, hash);
 
        
        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
    
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
    
        String remitente = "eliezeracebo@gmail.com";

        String claveemail = "cuwlxtypzzjzdnbs";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", claveemail);   
        props.put("mail.smtp.auth", "true");   
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.port", "587"); 

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   
        }
      }
    
//    public static void enviarConGMail(String destinatario, String asunto, String cuerpo, Properties propiedades) {
//    try {
//        String remitente = propiedades.getProperty("correo.remitente");
//        String claveemail = propiedades.getProperty("correo.clave");
//        String smtpHost = propiedades.getProperty("correo.smtp.host");
//        String smtpPort = propiedades.getProperty("correo.smtp.port");
//        String smtpAuth = propiedades.getProperty("correo.smtp.auth");
//        String smtpStartTLS = propiedades.getProperty("correo.smtp.starttls.enable");
//
//        // Configurar las propiedades de JavaMail con las configuraciones
//        Properties props = new Properties();
//        props.put("mail.smtp.host", smtpHost);
//        props.put("mail.smtp.port", smtpPort);
//        props.put("mail.smtp.auth", smtpAuth);
//        props.put("mail.smtp.starttls.enable", smtpStartTLS);
//
//        
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(remitente, claveemail);
//            }
//        });
//
//        // Crear el mensaje de correo
//        MimeMessage message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(remitente));
//        message.addRecipient(RecipientType.TO, new InternetAddress(destinatario));
//        message.setSubject(asunto);
//        message.setText(cuerpo);
//
//        // Enviar el correo
//        Transport.send(message);
//
//    } catch (MessagingException e) {
//        e.printStackTrace();
//    }
//}
//
//}

        
//        Properties propiedades = new Properties();
//        try {
//            
//            propiedades.load(new FileInputStream("config.properties"));
//
//            String remitente = propiedades.getProperty("correo.remitente");
//            String claveemail = propiedades.getProperty("correo.clave");
//            String smtpHost = propiedades.getProperty("correo.smtp.host");
//            String smtpPort = propiedades.getProperty("correo.smtp.port");
//            String smtpAuth = propiedades.getProperty("correo.smtp.auth");
//            String smtpStartTLS = propiedades.getProperty("correo.smtp.starttls.enable");
//
//            
//            Properties props = System.getProperties();
//            props.put("mail.smtp.host", smtpHost);
//            props.put("mail.smtp.user", remitente);
//            props.put("mail.smtp.clave", claveemail);
//            props.put("mail.smtp.auth", smtpAuth);
//            props.put("mail.smtp.starttls.enable", smtpStartTLS);
//            props.put("mail.smtp.port", smtpPort);
//
//            Session session = Session.getDefaultInstance(props);
//            MimeMessage message = new MimeMessage(session);
//
//            message.setFrom(new InternetAddress(remitente));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//            message.setSubject(asunto);
//            message.setText(cuerpo);
//
//            Transport transport = session.getTransport("smtp");
//            transport.connect(smtpHost, remitente, claveemail);
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//        } catch (IOException | MessagingException e) {
//            e.printStackTrace();
//        }
    
}

    

