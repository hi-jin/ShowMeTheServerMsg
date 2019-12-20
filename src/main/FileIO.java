package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

public class FileIO {

	static PrintWriter out = null;
	static BufferedReader in = null;
	
	static Vector<String> frontWrapper;
	static Vector<String> serverMsg;
	static Vector<String> endWrapper;
	static String filePath = "../../../../var/www/html/index.html";
	
	public static void showMsg() {
		readMsg();
		for(int i = 0; i < serverMsg.size(); i++) {
			System.out.println(i+1 + ". " + serverMsg.get(i).replace("      ", ""));
		}
	}
	
	public static void clearMsg() {
		serverMsg.clear();
	}
	
	public static void addMsg(String msg) {
		serverMsg.add("      " + msg + "<br>");
	}
	
	public static void removeMsg(int index) {
		serverMsg.remove(index-1);
	}
	
	public static void writeMsg() {
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			Iterator<String> it = frontWrapper.iterator();
			while(it.hasNext()) {
				out.println(it.next());
			}
			it = serverMsg.iterator();
			while(it.hasNext()) {
				out.println(it.next());
			}
			it = endWrapper.iterator();
			while(it.hasNext()) {
				out.println(it.next());
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readMsg() {
		frontWrapper = new Vector<>();
		serverMsg = new Vector<>();
		endWrapper = new Vector<>();
		
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = in.readLine()) != null) {
				frontWrapper.add(line);
				if(line.contains("<span id='msg'")) {
					frontWrapper.add(line);
					break;
				}
			}
			while((line = in.readLine()) != null) {
				if(line.contains("</span>")) {
					endWrapper.add(line);
					break;
				} else {
					serverMsg.add(line);
				}
			}
			while((line = in.readLine()) != null) {
				endWrapper.add(line);
			}
		} catch (FileNotFoundException e) {
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
				out.println("<!DOCTYPE html>");
				out.println("<html lang='en' dir='ltr'>");
				out.println("  <head>");
				out.println("    <meta charset='utf-8'>");
				out.println("    <title>ShowMeTheServerMsg</title>");
				out.println("  </head>");
				out.println("  <body>");
				out.println("    <span id='msg'>");
				out.println("    </span>");
				out.println("  </body>");
				out.println("</html>");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
