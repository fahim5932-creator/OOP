package Lab7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserManagementSystem {
    private static UserManagementSystem manager;
    private Map<String, User> users = new HashMap<>();
    private Map<String, Admin> admins = new HashMap<>();

    private UserManagementSystem(){
        loadUser();
        loadAdmin();
    }

    public static UserManagementSystem getInstance() {
        if (manager == null) {
            manager = new UserManagementSystem();
        }
        return manager;
    }

    public void loadUser(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Lab7/User.csv"));
            String line;
            IUserFactory factory = new UserFactoryCSV();
            while((line = reader.readLine()) != null){
                User user = factory.createUser(line);
                users.put(user.name, user);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAdmin(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/Lab7/Admin.csv"));
            String line;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                Admin admin = new Admin(parts[0], parts[1], parts[2], parts[3]);
                admins.put(admin.name, admin);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User authenticateUser(String username, String password){
        // need to downcast in the main to use the functions of each child class
        User user = users.get(username);
        if(user != null && user.password.equals(password)){
            System.out.println("User " + user.name + " authenticated");
            return user;
        }
        System.out.println("User " + username + " not authenticated");
        return null;
    }

    public Admin authenticateAdmin(String username, String password){
        Admin admin = admins.get(username);
        if(admin != null && admin.password.equals(password)){
            System.out.println("Admin " + admin.name + " authenticated");
            return admin;
        }
        System.out.println("Admin " + username + " not authenticated");
        return null;
    }

    public void addUser(User user){
        users.put(user.name, user);
    }

    public void addAdmin(Admin admin){
        admins.put(admin.name, admin);
    }
    public User getUser(String username){
        return users.get(username);
    }

    public Admin getAdmin(String username){
        return admins.get(username);
    }




}