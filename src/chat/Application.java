package chat;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Application {

	public static void main(String[] args) throws IOException 
	{
		ExecutorService pool = Executors.newFixedThreadPool(10);
		Scanner scanner = new Scanner(System.in);
		User user;
		
		if(args.length != 1)
		{
			System.out.println("Quantidade invalida de argumentos");
			System.exit(0);
		}
		
		System.out.println("Insira o seu nome:");
		user = new User(scanner.next());

		if("LISTEN".equals(args[0]))
		{
			user.beginChat();
		}
		else
		{
			user.enterChat(args[0]);
		}
		
		
		pool.execute(user.getReader());
		pool.execute(user.getSender());
		
		while(true)
		{
			user.insertInChat();
		}
	}

}
