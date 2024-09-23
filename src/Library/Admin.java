package Library;

import java.util.Scanner;

public class Admin extends User {
	
	
public Admin(String name) {
	super(name);
	this.operation = new IOOperation[] {
			new ViewBook(),
			new AddBook(),
			new DeleteBook(),
			new Search(),
			new DeleteAllData(),
			new ViewOrders(),
			new Exit(),
	};
}
public Admin(String name,String email,String phonenumber) {
	super(name,email,phonenumber);
	this.operation = new IOOperation[] {
					new ViewBook(),
					new AddBook(),
					new DeleteBook(),
					new Search(),
					new DeleteAllData(),
					new ViewOrders(),
					new Exit(),
			
	};
}

@Override
public void menu(Database database,User user) {
	System.out.println("1.View Books");
	System.out.println("2.Add Books");
	System.out.println("3.Delete Books");
	System.out.println("4.search Books");
	System.out.println("5.Delete all data");
	System.out.println("6.View Orders");
	System.out.println("7.Exit");
	
	Scanner s= new Scanner(System.in);
	int n=s.nextInt();
	this.operation[n-1].oper(database, user);
	s.close();
}
public String toString() {
	return  name+"<N/>" +email+ "<N/>" +phonenumber+ "<N/>" +"Admin";
}



}
