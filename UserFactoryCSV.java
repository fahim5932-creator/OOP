package Lab7;

public class UserFactoryCSV implements IUserFactory{
    public User createUser(String data){
        String[] dataArray = data.split(",");

        if(dataArray[4].equals("Regular")){
            return new RegularUser(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);
        }
        else if(dataArray[4].equals("Power")){
            return new PowerUser(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);
        }
        return null;
    }
}