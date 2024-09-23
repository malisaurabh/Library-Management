package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.File;

public class Database {
private ArrayList<User> user =new ArrayList<User>();
private ArrayList<String> username = new ArrayList<String>();
private ArrayList<Book> books=new ArrayList<Book>();
private ArrayList<String> bookname= new ArrayList<String>();
private ArrayList<Order> orders =new ArrayList<Order>();
private ArrayList<Borrowing> borrowings =new ArrayList<Borrowing>();

private File usersfile=new File("C:/Library Mangement System/Data/User");
private File booksfile=new File("C:/Library Mangement System/Data/Book");
private File ordersfile=new File("C:/Library Mangement System/Data/Orders");
private File borrowingsfile=new File("C:/Library Mangement System/Data/Borrowings");
private File folder = new File("C:/Library Mangement System/Data");

 
public Database() {
	
	if(!folder.exists()) {
		folder.mkdirs();
	}
	if(!usersfile.exists()) {
		
		try{
			usersfile.createNewFile();
		}
		catch(Exception e) {
		}
		}
	if(! booksfile.exists()) {
		try{
			booksfile.createNewFile();
		}catch(Exception e) {}
		}
	if(! ordersfile.exists()) {
		try{
			ordersfile.createNewFile();
		}catch(Exception e) {}
		}
	if(! borrowingsfile.exists()) {
		try{
			borrowingsfile.createNewFile();
		}catch(Exception e) {}
		}
	//getUsers();
	getBooks();
	getOrders();
	getBorrowings();
}
public void AddUser(User s) {
	user.add(s);
	username.add(s.getname());
	saveUsers();
	
}

public int login(String phonenumber,String email) {
	int n= -1;
	for(User s:user) {
		if (s.getphonenumber().matches(phonenumber)&&s.getemail().matches(email)) {
			n=user.indexOf(s);
					break;
		}
	}
	return n;
}
public User getUser(int n) {
	return user.get(n);
}
public void AddBook(Book book) {
	books.add(book);
	bookname.add(book.getName());
	saveBooks();
}
private void getUsers() {
	String text1= "";
	try {
		BufferedReader br1 =new BufferedReader(new FileReader(usersfile));
		String s1;
		while((s1=br1.readLine())!=null) {
			text1=text1+s1;
		}
		br1.close();
	}catch(Exception e) {
		System.err.println(e.toString());
		}
	if(! text1.matches("")||text1.isEmpty()) {
		String[] a1 = text1.split("<NewUser/>");
		for(String s: a1) {
			String[] a2=s.split("<N/>");
			if(a2[3].matches("Admin")) {
				User users = new Admin(a2[0],a2[1],a2[2]);
				user.add(users);
				username.add(users.getname());
			}else {
				User users = new NormalUser(a2[0],a2[1],a2[2]);
				user.add(users);
				username.add(users.getname());
			}
		}
	}
}
private void saveUsers() {
	String text1="";
	for(User users:user) {
		text1=text1+users.toString()+"<NewUser/>\n";
	}
	try {
		PrintWriter pw = new PrintWriter(usersfile);
		pw.print(text1);
		pw.close();
	}catch(Exception e) {
		System.err.println(e.toString());
	}
}
private void saveBooks() {
	String text1="";
	for(Book book:books) {
		text1=text1+book.toString2()+"<NewBook/>\n";
	}
	try {
		PrintWriter pw = new PrintWriter(booksfile);
		pw.print(text1);
		pw.close();
	}catch(Exception e) {
		System.err.println(e.toString());
	}
}
private void getBooks() {
	String text1= "";
	try {
		BufferedReader br1 =new BufferedReader(new FileReader(booksfile));
	String s1;
		
		while((s1=br1.readLine())!=null) {
			
			text1=text1+s1;
		}
		br1.close();
	}catch(Exception e) {
		System.err.println(e.toString());
		}
	if(!text1.matches("")|| !text1.isEmpty()) {
		String[] a1 = text1.split("<NewBook/>");
		for(String s: a1) {
			Book book = parseBook(s);
			books.add(book);
			bookname.add(book.getName());
			
		}
	}
}
public Book parseBook(String s) {
    String[] a = s.split("<N/>");
    System.out.println(a);
    Book book = new Book();
    book.setName(a[0].trim());
    book.setAuthor(a[1].trim());
    book.setPublisher(a[2].trim());
    book.setAdress(a[3].trim());
    book.setQty(Integer.parseInt(a[4].trim()));
    book.setPrice(Double.parseDouble(a[5].trim()));
    book.setBrwcopies(Integer.parseInt(a[6].trim()));
    return book;

}
public ArrayList<Book> getAllBooks(){
		return books;
}
public int getBook(String bookname) {
	int i= -1;
	for(Book book : books) {
		if(book.getName().matches(bookname)) {
			i= books.indexOf(book); 
		}
		
	}
	return i;
}
public Book getBook(int i) {
	return books.get(i);
}
public void deletebook(int i) {
	books.remove(i);
	bookname.remove(i);
	saveBooks();
}
public void deleteAllData() {

	if(usersfile.exists()) {
		
		try{
			usersfile.delete();
		}
		catch(Exception e) {
		}
		}
	if( booksfile.exists()) {
		try{
			booksfile.delete();
		}catch(Exception e) {}
		}
	if( ordersfile.exists()) {
		try{
			ordersfile.delete();
		}catch(Exception e) {}
		}
	if( borrowingsfile.exists()) {
		try{
			borrowingsfile.delete();
		}catch(Exception e) {}
		}
}
public void addOrder(Order order,Book book,int bookindex) {
	 orders.add(order);
	 books.set(bookindex, book);
	 saveOrder();
	 saveBooks();
}
private void saveOrder() {
	String text1="";
	for(Order order:orders) {
		text1=text1+order.toString2()+"<NewOrder/>\n";
	}
	try {
		PrintWriter pw = new PrintWriter(ordersfile);
		pw.print(text1);
		pw.close();
	}catch(Exception e) {
		System.err.println(e.toString());
	}
}
private void getOrders() {
	String text1= "";
	try {
		BufferedReader br1 =new BufferedReader(new FileReader(ordersfile));
	String s1;
		
		while((s1=br1.readLine())!= null) {
			text1=text1+s1;
		}
		br1.close();
	}catch(Exception e) {
		System.err.println(e.toString());
		}
	if(!text1.matches("")|| !text1.isEmpty()) {
		String[] a1 = text1.split("<NewOrder/>");
		for(String s: a1) {
			Order order = parseOrder(s);
			orders.add(order);
		}
    }
  }
public boolean userExists(String name) {
	boolean f= false;
	for(User users:user) {
		if(users .getname().matches(name)) {
			f= true;
			break;
		}
	}
	return f;
}
private User getUserByName(String name) {
	User u = new NormalUser("");
	for(User users:user) {
		if(users .getname().toLowerCase().matches(name.toLowerCase())) {
			u=users;
			break;
		}
	}
	return u;
}
private Order parseOrder(String s) {
	String[]a = s.split("<N/>");
	Order order = new Order(books.get(getBook(a[0])),getUserByName(a[1]),
			Double.parseDouble(a[2]),Integer.parseInt(a[3]));
	return order;
 }
public ArrayList<Order> getallOrders(){
	return orders;
	
}
private void saveBorrowings() {
	String text1="";
	for(Borrowing borrowing : borrowings) {
		text1=text1+ borrowing.toString2()+"<NewBorrowing/>\n";
	}
	try {
		PrintWriter pw = new PrintWriter(borrowingsfile);
		pw.print(text1);
		pw.close();
	}catch(Exception e) {
		System.err.println(e.toString());
	}
}
private void  getBorrowings() {
	String text1= "";
	try {
		BufferedReader br1 =new BufferedReader(new FileReader(borrowingsfile));
	String s1;
		
		while((s1=br1.readLine())!= null) {
			text1=text1+s1;
		}
		br1.close();
	}catch(Exception e) {
		System.err.println(e.toString());
		}
	if(!text1.matches("")|| !text1.isEmpty()) {
		String[] a1 = text1.split("<NewBorrowing/>");
		for(String s: a1) {
			Borrowing borrowing = parseBorrowings(s);
			borrowings.add(borrowing);
		}
    }
}
private Borrowing parseBorrowings(String s) {
    String[] a = s.split("<N/>");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Correct pattern
    LocalDate start = LocalDate.parse(a[0], formatter);
    LocalDate finish = LocalDate.parse(a[1], formatter);
    Book book = getBook(getBook(a[3]));  // Fixed redundant method call
    User user = getUserByName(a[4]);
    Borrowing brw = new Borrowing(start, finish, book, user);
    return brw;
}

public void borrowbook(Borrowing brw,Book book, int bookindex) {
	borrowings.add(brw);
	books.set(bookindex, book);
	saveBorrowings();
	saveBooks();
}
public ArrayList<Borrowing> getBrw(){
	return borrowings;
}
public void returnbook(Borrowing b,Book book,int bookindex) {
	borrowings.remove(b);
	books.set(bookindex, book);
	saveBorrowings();
	saveBooks();
}
}
