package dao;

import models.Administrator;
import models.RegularUser;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private Map<String, Administrator> administratorMap = new HashMap<>();
    private Map<String, RegularUser> regularUserMap = new HashMap<>();

    public void createAdministrator(Administrator admin)
    {
        administratorMap.put(admin.getUserId(), admin);
    }

    public Administrator readAdministratorById(String adminId)
    {
        return administratorMap.get(adminId);
    }

    public void removeAdministrator(String adminId)
    {
        administratorMap.remove(adminId);
    }

    public void createRegularUser(RegularUser user)
    {
        regularUserMap.put(user.getUserId(), user);
    }

    public RegularUser readRegularUserById(String userId)
    {
        return regularUserMap.get(userId);
    }

    public RegularUser readRegularUserByName(String userName)
    {
        for (RegularUser user : regularUserMap.values())
        {
            if (user.getUserName().equalsIgnoreCase(userName))
            {
                return user;
            }
        }
        return null;
    }

    public void removeRegularUser(String userId)
    {
        regularUserMap.remove(userId);
    }

    public Map<String, Administrator> getAllAdministrators()
    {
        return new HashMap<>(administratorMap);
    }

    public Map<String, RegularUser> getAllRegularUsers()
    {
        return new HashMap<>(regularUserMap);
    }
}
