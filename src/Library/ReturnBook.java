package Library;

import java.util.Scanner;

public class ReturnBook implements IOOperation{

	@Override
	public void oper(Database database,User user) {
		System.out.println("Eneter the book name :");
		Scanner s = new Scanner(System.in);
		String bookname=s.next();
		if(!database.getBrw().isEmpty()) {
			for(Borrowing b :database.getBrw()) {
				if(b.getBook().getName().matches(bookname)&&
						b.getUser().getname().matches(user.getname())) {
					Book book =b.getBook();
					int i= database.getAllBooks().indexOf(book);
					if(b.getDaysLeft()<0) {
						System.out.println("You are late !"+
								 "You have to pay"+Math.abs(b.getDaysLeft()*50)+"as fine!");
					}
					book.setBrwcopies(book.getBrwcopies()+1);
					database.returnbook(b,book,i);
					System.out.println("Book Returened\nThank You !\n");
					break;
				}else {
					System.out.println("You didn't Borrow this book.....\n");
				}
			}
		}else {
			System.out.println("You didn't Borrow this book.....\n");
		}
		user.menu(database, user);
	}

}
