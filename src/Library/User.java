package Library;

public abstract class User {
protected String name;
protected String email;
protected String phonenumber;
protected IOOperation[] operation;
public User() {}
public User(String name) {
	this.name=name;
}
public User(String name,String email,String phonenumber) {
	this.name=name;
	this.email=email;
	this.phonenumber=phonenumber;
}

public String getname() {
	return name;
}
public String getemail() {
	return email;
}
public String getphonenumber() {
	return phonenumber;
}
 abstract public String toString();
	


abstract public void menu(Database database,User user);
}