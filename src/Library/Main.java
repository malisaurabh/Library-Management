package Library;
import java.util.Scanner;
public class Main {
	  static Scanner s;
	  static Database database;
public static void main(String x[]) {
	 database=new Database();
	int num;
	System.out.println("Welcome to library Mangement System!");
		System.out.println("\n"+"0.Exit\n"+"1.Login\n2.New User");
		  s = new Scanner(System.in);
		  num = s.nextInt();
	 switch(num) {
	 case 1:login();break;
	 case 2:newuser();break;
	 }
}
private static void login() {
	System.out.println("enter phone number"); 
	String phonenumber = s.next();
	System.out.println("enter the email");
	String email = s.next();
	int n=database.login(phonenumber, email);
	if(n!= -1) {
		User user = database.getUser(n);
		user.menu( database, user);
	}
	else {
		System.out.println("User doesn't exit!");
	}
}
private static void newuser() {
	System.out.println("enter name");
	String name = s.next();
	if(database.userExists(name)) {
		System.out.println("User Exists!");
		newuser();
	}
	System.out.println("enter phone number");
	String phonenumber = s.next();
	System.out.println("enter email");
	String email = s.next();
	System.out.println("1.Admin\n2.Normal User");
	int n2=s.nextInt();
	User user;
	if(n2==1) {
		 user = new Admin(name,email,phonenumber);
	}
	else {
		 user =new NormalUser(name,email,phonenumber);
	}
	database.AddUser(user);
	user.menu(database,user);
}
}
