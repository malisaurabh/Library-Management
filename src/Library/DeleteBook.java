package Library;

import java.util.Scanner;

public class DeleteBook implements IOOperation {

	@Override
	public void oper(Database database,User user) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Book Name :");
		 String bookname = s.next();
		 int i=database.getBook(bookname);
		 if(i>-1) {
			 database.deletebook(i);
			 System.out.println("Book deleted Successfully.....!\n");
		 }else {
			 System.out.println("Book doesn't exist....!\n");
		 }
		 user.menu(database, user);
		
	}

}
