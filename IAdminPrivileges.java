package Lab7;

public interface IAdminPrivileges {
    void modifyPrivilege(String username, String newPrivilege);
    void addUser(String name, String email, String password, String id, String userType);
    void addAdmin(String name, String email, String password, String id);
    StringBuilder readAdmin();
    boolean changeFileName(String path, String newName);
}