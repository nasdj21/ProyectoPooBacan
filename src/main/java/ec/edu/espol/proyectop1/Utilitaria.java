/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import java.util.Properties;
import javax.mail.*;
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
 
    
}
    

