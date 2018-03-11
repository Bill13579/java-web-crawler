package main;

public class Main {
	
	public static void main(String[] args) {
		
		String start_url = "http://192.168.3.138/java-web-crawler-test/index.html";
		
		Bot bot = new Bot(start_url);
		bot.start();
		
	}

}
