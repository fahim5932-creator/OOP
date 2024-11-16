package Lab7;

import java.io.*;

public class AdminPrivilegesCSV implements IAdminPrivileges{
    UserManagementSystem manager;
    public AdminPrivilegesCSV() {
        manager = UserManagementSystem.getInstance();
    }

    @Override
    public void modifyPrivilege(String username, String newPrivilege) {
        File file = new File("src/Lab7/User.csv");
        StringBuilder Old = new StringBuilder();
        BufferedReader br = null;
        FileWriter fw = null;

        try{
            br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split(",");
                if(words[1].equals(username)){
                    words[4] = newPrivilege;
                    String temp = words[0] + "," + words[1] + "," + words[2] + "," + words[3] + "," + words[4];

                    Old.append(temp).append("\n");
                }
                else {
                    Old.append(line).append("\n");
                }

            }

            String newContent = Old.toString();

            fw = new FileWriter(file);
            fw.write(newContent);

            br.close();
            fw.close();
            manager.loadUser();
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(String id, String name, String email, String password,  String userType) {
        User user = manager.getUser(name);
        if(user == null){
            IUserFactory factory = new UserFactoryCSV();
            String data = id + "," + name + "," + email + "," + password + ","  + userType;
            User newUser = factory.createUser(data);
            manager.addUser(newUser);
            writeUser(newUser);
            manager.loadUser();
        }
        else {
            System.out.println("User already exists");
        }
    }

    public void addAdmin(String id, String name, String email, String password){
        Admin admin = manager.getAdmin(name);
        if(admin == null){
            Admin newAdmin = new Admin(id, name, email, password);
            manager.addAdmin(newAdmin);
            writeAdmin(newAdmin);
            manager.loadAdmin();
        }
        else {
            System.out.println("Admin already exists");
        }
    }

    private void writeUser(User user) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Lab7/User.csv", true));
            String line = user.id + "," + user.name + "," + user.email + "," + user.password + "," + user.userType + System.lineSeparator();
            bw.write(line);
            bw.flush(); // didn't work without it
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeAdmin(Admin admin){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Lab7/Admin.csv", true));
            String line = admin.id + "," + admin.name + "," + admin.email + "," + admin.password + System.lineSeparator();
            bw.write(line);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder readAdmin(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Lab7/Admin.csv"));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null){
                sb.append(line).append("\n");
            }
            reader.close();
            return sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean changeFileName(String path, String newName) {
        File file = new File(path);
        String[] temp = path.split("/");
        temp[temp.length - 1] = newName;
        StringBuilder newPath = new StringBuilder();
        for(int i = 0; i < temp.length; i++){
            newPath.append(temp[i]);
            if(i + 1 != temp.length){
                newPath.append("/");
            }
        }
        File newFile = new File(newPath.toString());
        return file.renameTo(newFile);
    }


}