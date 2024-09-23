package Library;

import java.util.Scanner;

public class NormalUser extends User {
	public NormalUser(String name) {
		super(name);
		this.operation = new IOOperation[] {
			new ViewBook(),
			new AddBook(),
			new Search(),
			new placeOrder(),
			new BorrowBook(),
			new Calculatefine(),
			new ReturnBook(),
			new Exit(),
		};
		
	}
	public NormalUser(String name,String email,String phonenumber) {
		super(name,email,phonenumber);
		this.operation = new IOOperation[] {
				new ViewBook(),
				new AddBook(),
				new Search(),
				new placeOrder(),
				new BorrowBook(),
				new Calculatefine(),
				new ReturnBook(),
				new Exit(),
			};
	}
	@Override
	public void menu(Database database,User user) {
		System.out.println("1.View Books");
		System.out.println("2.Add Books");
		System.out.println("3.search");
		System.out.println("4.place Order");
		System.out.println("5.Borrow Book");
		System.out.println("6.Calculate fine");
		System.out.println("7.Return Book");
		System.out.println("8.Exit");
		Scanner s= new Scanner(System.in);
		int n=s.nextInt();
		this.operation[n-1].oper(database,user);
		s.close();
	}
	public String toString() {
		return  name+"<N/>" +email+ "<N/>" +phonenumber+ "<N/>" +"Normal" ;
	}
}
