package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

public class AccountService {

    private DBService dbService = new DBService();
   public void addNewUser(UserProfile userProfile) throws DBException {
        dbService.addUser(userProfile.getLogin(), userProfile.getPassword());
    }
    public UserProfile getUserByLogin(String login) {
        UsersDataSet usersDataSet = dbService.getUserByName(login);
        return new UserProfile(usersDataSet.getName(), usersDataSet.getPassword());
    }

}
