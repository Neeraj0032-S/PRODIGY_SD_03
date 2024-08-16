package task2;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email: " + email;
    }
}

class ContactManager {
    private List<Contact> contacts;
    private final String filePath = "contacts.dat";

    public ContactManager() {
        contacts = loadContactsFromFile();
    }

    public void addContact(String name, String phoneNumber, String email) {
        contacts.add(new Contact(name, phoneNumber, email));
        saveContactsToFile();
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    public void editContact(int index, String name, String phoneNumber, String email) {
        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            contact.setName(name);
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            saveContactsToFile();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            saveContactsToFile();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    private List<Contact> loadContactsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveContactsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class contactmanagement {

	public static void main(String[] args) {
		  Scanner scanner = new Scanner(System.in);
	        ContactManager contactManager = new ContactManager();

	        while (true) {
	            System.out.println("\nContact Manager");
	            System.out.println("1. Add Contact");
	            System.out.println("2. View Contacts");
	            System.out.println("3. Edit Contact");
	            System.out.println("4. Delete Contact");
	            System.out.println("5. Exit");
	            System.out.print("Choose an option: ");
	            int option = scanner.nextInt();
	            scanner.nextLine();  // Consume newline

	            switch (option) {
	                case 1:
	                    System.out.print("Enter name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter phone number: ");
	                    String phoneNumber = scanner.nextLine();
	                    System.out.print("Enter email: ");
	                    String email = scanner.nextLine();
	                    contactManager.addContact(name, phoneNumber, email);
	                    break;
	                case 2:
	                    contactManager.viewContacts();
	                    break;
	                case 3:
	                    System.out.print("Enter the contact index to edit: ");
	                    int editIndex = scanner.nextInt() - 1;
	                    scanner.nextLine();  // Consume newline
	                    System.out.print("Enter new name: ");
	                    name = scanner.nextLine();
	                    System.out.print("Enter new phone number: ");
	                    phoneNumber = scanner.nextLine();
	                    System.out.print("Enter new email: ");
	                    email = scanner.nextLine();
	                    contactManager.editContact(editIndex, name, phoneNumber, email);
	                    break;
	                case 4:
	                    System.out.print("Enter the contact index to delete: ");
	                    int deleteIndex = scanner.nextInt() - 1;
	                    contactManager.deleteContact(deleteIndex);
	                    break;
	                case 5:
	                    System.out.println("Exiting program.");
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    }
	}