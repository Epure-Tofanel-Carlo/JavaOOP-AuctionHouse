package dao;

import models.Administrator;
import models.RegularUser;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private Map<String, Administrator> administratorMap = new HashMap<>();
    private Map<String, RegularUser> regularUserMap = new HashMap<>();

    public void createAdministrator(Administrator admin) {
        if (admin == null || admin.getUserId() == null) {
            throw new IllegalArgumentException("Administrator or ID cannot be null");
        }
        if (administratorMap.containsKey(admin.getUserId())) {
            throw new IllegalArgumentException("Administrator with this ID already exists");
        }
        administratorMap.put(admin.getUserId(), admin);
    }

    public Administrator readAdministratorById(String adminId) {
        return administratorMap.get(adminId);
    }

    public void removeAdministrator(String adminId) {
        administratorMap.remove(adminId);
    }

    public void createRegularUser(RegularUser user) {
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("RegularUser or ID cannot be null");
        }
        if (regularUserMap.containsKey(user.getUserId())) {
            throw new IllegalArgumentException("RegularUser with this ID already exists");
        }
        regularUserMap.put(user.getUserId(), user);
    }

    public RegularUser readRegularUserById(String userId) {
        return regularUserMap.get(userId);
    }

    public void removeRegularUser(String userId) {
        regularUserMap.remove(userId);
    }

    // Methods to return all administrators and regular users
    public Map<String, Administrator> getAllAdministrators() {
        return new HashMap<>(administratorMap);
    }

    public Map<String, RegularUser> getAllRegularUsers() {
        return new HashMap<>(regularUserMap);
    }
}
