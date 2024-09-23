package Library;

import java.util.Scanner;

public class BorrowBook implements IOOperation{

	@Override
	public void oper(Database database,User user) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Book Name :");
		 String bookname = s.next();
		 int i=database.getBook(bookname);
		 if(i > -1) {
			 Book book =database.getBook(i);
			 boolean n=true;
			 for(Borrowing b:database.getBrw()) {
				 if(b.getBook().getName().matches(bookname)&&
						 b.getUser().getname().matches(user.getname())) {
					 n=false;
					 System.out.println("You have borrowed book before...\n");
				 }
			 }
			if(n) {
				if(book.getBrwcopies()>1) {
					 Borrowing borrowing = new Borrowing(book, user);
					 book.setBrwcopies(book.getBrwcopies()-1);
					 database.borrowbook(borrowing ,book,i);
					 System.out.println("You must return the book 14 days from now\n"+
					 "Expiry Date :"+borrowing.getFinish()+"\n Enjoy...!\n");
				 }else {
					 System.out.println("This book isn't avalible for borrowing\n ");
				 }
			}
		 }else {
			 System.out.println("Book doesn't exist....!\n");
		 }
		user.menu(database, user);
		
	}

}
