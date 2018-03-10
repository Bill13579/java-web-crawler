package main;

import java.io.*;
import java.net.*;

public class Bot {
	
	private String start_url;
	
	public Bot(String start_url) {
		this.start_url = start_url;
	}
	
	public void start() {
		
		String html = getHTML(this.start_url);
		System.out.println(html);
		
	}
	
	private String getHTML(String url) {
		
		URL u;
		try {
			u = new URL(url);
			
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("User-Agent", "BBot/1.0");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			String line;
			String html = "";
			while ((line = reader.readLine()) != null) {
				html += line + "\n";
			}
			html = html.trim();
			
			return html;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
