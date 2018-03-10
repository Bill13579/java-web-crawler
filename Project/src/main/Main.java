package main;

public class Main {
	
	public static void main(String[] args) {
		
		String start_url = "file:///data/Tutorials/Java%20Web%20Crawler/java-web-crawler/Test%20Page/index.html";
		
		Bot bot = new Bot(start_url);
		bot.start();
		
	}

}
