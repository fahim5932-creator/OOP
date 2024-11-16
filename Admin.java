package Lab7;

import java.io.FileNotFoundException;

public class Admin extends User {
    private IAdminPrivileges adminPrivileges;

    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password, "Admin");
        adminPrivileges = new AdminPrivilegesCSV();
    }

    public void modifyPrivileges(String username, String newPrivilege) {
        adminPrivileges.modifyPrivilege(username, newPrivilege);
    }

    public void addUser(String name, String email, String password, String id, String userType){
        adminPrivileges.addUser(name, email, password, id, userType);
    }

    public void addAdmin(String name, String email, String password, String id){
        adminPrivileges.addAdmin(name, email, password, id);
    }

    public StringBuilder readUser() throws FileNotFoundException {
        IReader reader = new ReaderCSV();
        return reader.read();
    }

    public StringBuilder readAdmin() throws FileNotFoundException {
        return adminPrivileges.readAdmin();
    }

    public void modify(String oldWord, String newWord){
        IModifier modifier = new ModifierCSV();
        modifier.modify(oldWord, newWord);
    }

    public boolean renameFile(String path, String newName){
        return adminPrivileges.changeFileName(path, newName);

    }


}