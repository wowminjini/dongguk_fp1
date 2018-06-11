import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

class AddressBook {
    private String name;
    private String phone;

    public AddressBook(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public String toString() {
        return String.format("%s(%s)", name, phone);
    }
}
public class AddressBookManager {
    private Map<String, AddressBook> books = null;
    public AddressBookManager() {
        books = new HashMap<String, AddressBook>();
    }
    public void add(String name, String phone) {
        AddressBook book = new AddressBook(name, phone);
        books.put(name, book);
    }
    public AddressBook remove(String name) {
        AddressBook book = findByName(name);
        if (book != null) {
            books.remove(name);
        }
        return book;
    }
    public void printAll() {
        for (String key : books.keySet()) {
            AddressBook book = books.get(key);
            System.out.println(book.toString());
        }
    }
    public AddressBook findByName(String name) {
        AddressBook book = null;
        if (books.containsKey(name)) {
            book = books.get(name);
        }
        return book;
    }
    public boolean save(String path) {
        if (path.equals("user.txt") == true) {
            try {
                FileWriter writer = new FileWriter(path);
                for (String key : books.keySet()) {
                    AddressBook book = books.get(key);
                    writer.write(book.toString() + "\r\n");
                }
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public boolean load(String path) {
        if (path.equals("user.txt") == true) {
            try {
                FileWriter writer = new FileWriter(path);
                for (String key : books.keySet()) {
                    AddressBook book = books.get(key);
                    writer.write(key + "\r\n");
                }
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public static void showMenu() {
        System.out.println("===== Menu =====");
        System.out.println("1. Add Address");
        System.out.println("2. PrintAll");
        System.out.println("3. Find By Name");
        System.out.println("4. Remove By Name");
        System.out.println("5. Save");
        System.out.println("6. Load");
        System.out.println("7. Quit");
    }

    public static int getMenu(Scanner sc) {
        return Integer.parseInt(sc.nextLine().trim());
    }

    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;

        AddressBookManager bookManager = new AddressBookManager();

        while(true) {
            showMenu();
            int menu = getMenu(sc);
            if (menu == 1) {
                String name = sc.nextLine();
                String phone = sc.nextLine();
                bookManager.add(name, phone);
            } else if (menu == 2) {
                bookManager.printAll();
            } else if (menu == 3) {
                String name = sc.nextLine();
                AddressBook book = bookManager.findByName(name);
                if (book != null) {
                    System.out.println("Find: " + book.toString());
                } else {
                    System.out.println("There is no address : " + name);
                }
            } else if (menu == 4) {
                String name = sc.nextLine();
                AddressBook book = bookManager.remove(name);
                if (book != null) {
                    System.out.println("Delete: " + book.toString());
                } else {
                    System.out.println("There is no address : " + name);
                }
            } else if (menu == 5) {
                if (bookManager.save("user.txt") == false) {
                    System.out.println("Fail to save");
                }
            } else if (menu == 6) {
                if (bookManager.load("user.txt") == false) {
                    System.out.println("Fail to load");
                }
            } else if (menu == 7) {
                break;
            }
        }
    }
}