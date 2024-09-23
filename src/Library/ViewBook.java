package Library;

import java.util.ArrayList;

public class ViewBook implements IOOperation{

	@Override
	public void oper(Database database,User user) {
		
		ArrayList<Book> books =database.getAllBooks();
		System.out.println("Name\t\t Author\t\t publisher\t CLA\t\t Qty\t\t Price"
		+"\t\t Brw cps");
		for(Book b: books) {
			System.out.println(b.getName()+"\t\t "+b.getAuthor()+"\t\t"+b.getPublisher()+"\t\t"+
		b.getAdress()+"\t\t"+b.getQty()+"\t\t"+b.getPrice()+"\t\t"+b.getBrwcopies());
		}
		System.out.println();
		user.menu(database, user);
		
		
}

} 
