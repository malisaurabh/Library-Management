package Library;


import java.util.Scanner;

public class ViewOrders implements IOOperation{

	@Override
public void oper(Database database,User user) {
		
	System.out.println("\n Enter the Bookname ");
		Scanner s= new Scanner(System.in);
		String bookname = s.next();
		int i= database.getBook(bookname);
		if(i>-1) {
			System.out.println("Book\t\tuser\t\tQty\t\tPrice");
			for(Order order: database.getallOrders()) {
				if(order.getBook().getName().matches(bookname)) {
					System.out.println(order.getBook().getName()+"\t\t"+
				order.getUser().getname()+"\t\t"+order.getQty()+"\t\t"+order.getPrice());
				}
				
			}
			System.out.println();
			
		}else {
			System.out.println("Book doesn't exist!\n");
		}
		user.menu(database, user);
		  
	}

}
