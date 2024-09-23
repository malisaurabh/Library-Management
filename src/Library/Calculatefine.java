package Library;

import java.util.Scanner;

public class Calculatefine implements IOOperation {

	@Override
	public void oper(Database database,User user) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Book Name :");
		 String bookname = s.next();
		 boolean g = true;
		 for(Borrowing b: database.getBrw()) {
			 if(b.getBook().getName().matches(bookname)&& 
					 b.getUser().getname().matches(user.getname())) {
				 if(b.getDaysLeft()<0) {
					 System.out.println("You are late !"+
				 "You have to pay"+b.getDaysLeft()*50+"as fine!\n");
				 }else {
					 System.out.println("You don't pay to fine\n");
				 }
				 g = false;
				 break;
			 }
		 }
		 if(g) {
			 System.out.println("You dodn't borrow this book!");
		 }
		 user.menu(database, user);
		
	}

}
