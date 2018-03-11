package main;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Bot {
	
	private String start_url;
	
	public Bot(String start_url) {
		this.start_url = start_url;
	}
	
	public void start() {
		crawl(this.start_url);
	}
	
	private void crawl(String url) {
		
		String html = getHTML(url);
		Set<String> crawledURLs = new HashSet<String>();
		Set<String> pendingURLs = new HashSet<String>();
		
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("a");
		
		for (Element e: elements) {
			String href = e.attr("href");
			href = processLink(href, url);
			System.out.println(href);
		}
		System.out.println(processLink("../", url));
		
	}
	
	private String processLink(String link, String base) {
		
		try {
			URL u = new URL(base);
			if (link.startsWith("./")) {
				link = link.substring(2, link.length());
				link = u.getProtocol() + "://" + u.getAuthority() + stripFilename(u.getPath()) + link;
			} else if (link.startsWith("#")) {
				link = base + link;
			} else if (link.startsWith("javascript:")) {
				link = null;
			} else if (link.startsWith("../") || (!link.startsWith("http://") && !link.startsWith("https://"))) {
				link = u.getProtocol() + "://" + u.getAuthority() + stripFilename(u.getPath()) + link;
			}
			return link;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private String stripFilename(String path) {
		int pos = path.lastIndexOf("/");
		return pos <= -1 ? path : path.substring(0, pos+1);
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