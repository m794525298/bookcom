package com.book.common;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EmailSender  {
	
	private static OutputStream out = null;
	private static BufferedReader in =null;
	
	public static void send(String subject,String content,String to) throws IOException {
		
        Socket socket = null;
        try {
            socket = new Socket("smtp.qq.com", 25);
            out = socket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(in.readLine());
            outWrite("helo qq.com");

            System.out.println(in.readLine());
            System.out.println(in.readLine());
            System.out.println(in.readLine());

            outWrite("auth login"); 
            System.out.println(in.readLine());

            outWrite(Coder.textToBase64("", "UTF-8")); 
            System.out.println(in.readLine());

            outWrite(Coder.textToBase64("", "UTF-8"));  
            System.out.println(in.readLine());

            outWrite("mail from:<" + "" + ">");
            System.out.println(in.readLine());

            outWrite("rcpt to:<" + to + ">");
            System.out.println(in.readLine());

            outWrite("data");
            System.out.println(in.readLine());

            outWrite("From: " + "");
            outWrite("To: " + to);
            outWrite("Subject: " + subject);
            SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZ", Locale.ENGLISH);
            Date day = new Date();
            outWrite("Date: " + df.format(day));
            outWrite("Mime-Version: 1.0");
            outWrite("Content-Type: multipart/mixed;   boundary=\"a\"");        
            outWrite("");                                                       
            outWrite("--a");                                                   
            outWrite("Content-Type: text/plain;    charset=\"ISO-8859-1\"");
            outWrite("Content-Transfer-Encoding: base64");
            outWrite("");                                                       
            outWrite(Coder.textToBase64(content, "UTF-8"));                               
            outWrite("");                                                      
            outWrite("--a--");


            outWrite(".");
            System.out.println(in.readLine());
            outWrite("quit");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.flush();
            out.close();
        }
	}

	private static void outWrite(String str) {
		try {
            out.write((str + "\r\n").getBytes());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}



}
