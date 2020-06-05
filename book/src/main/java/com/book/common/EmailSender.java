package com.book.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EmailSender {

	 private static OutputStream out = null;
	 private static BufferedReader in = null;
	    
	 /**
     * send a captcha to the email
     *
     * @param receiver	email of the receiver
     * @param captcha	a verification combine by six digits
     * 
     * @throws IOException
     */
	 public static void send(String receiver, String captcha) throws IOException {

        String buffer = null;
        Socket socket = null;
        RandomAccessFile accessFile = null;
        try {
            socket = new Socket("smtp.163.com", 25);
            out = socket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(in.readLine());
            outWrite("helo sis");                       // say hello

            System.out.println(in.readLine());

            outWrite("auth login");                     // prepare for login in
            System.out.println(in.readLine());

            outWrite(Coder.textToBase64(""));         						// base64 of account
            System.out.println(in.readLine());
            
            outWrite(Coder.textToBase64(""));    // base64 of authrize
            System.out.println(in.readLine());

            outWrite("mail from: <qwsa374293896@163.com>");
            System.out.println(in.readLine());
            
            outWrite("rcpt to: <" + receiver + ">");
            System.out.println(in.readLine());

            outWrite("data");
            System.out.println(in.readLine());

            outWrite("From: <qwsa374293896@163.com>");
            outWrite("To: <" + receiver + ">");
            outWrite("Subject: 书友网验证信息");
            SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZ", Locale.ENGLISH);
            Date day = new Date();
            outWrite("Date: " + df.format(day));
            outWrite("Mime-Version: 1.0");
            outWrite("Content-Type: multipart/mixed;   boundary=\"a\"");                // the split sign
            outWrite("");                                                               // blank line (format)
            outWrite("--a");                                                            // split (to start writing content)
            outWrite("Content-Type: text/plain;    charset=\"gb18030\"");
            outWrite("Content-Transfer-Encoding: base64");
            outWrite("");                                                               // blank line (format)

            // content
            String content = "欢迎您的到来, 您的验证码是: " + captcha + ", 请于5分钟内返回页面输入该验证码。";
            outWrite(Coder.textToBase64(content, "gb18030"));     // base64 of content
            outWrite("");
            outWrite("--a--");
            outWrite("");
            outWrite(".");                  // ending
            System.out.println(in.readLine());
            outWrite("quit");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.flush();
            out.close();
        }

    }
    
    /**
     * output string to the socket
     * @param str   the output string
     */
	private static void outWrite(String str) {
		try {
            out.write((str + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
