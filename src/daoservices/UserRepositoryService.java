package daoservices;

import dao.UserDao;
import models.Administrator;
import models.RegularUser;
import models.Bid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserRepositoryService {

    private UserDao userDao;

    public UserRepositoryService() {
        this.userDao = new UserDao();
    }

    public void createAdministrator(Administrator admin)
    {
        if (admin == null || admin.getUserId() == null)
        {
            throw new IllegalArgumentException("Administrator or ID cannot be null");
        }
        if (userDao.readAdministratorById(admin.getUserId()) != null)
        {
            throw new IllegalArgumentException("Administrator with this ID already exists");
        }
        userDao.createAdministrator(admin);
    }

    public Administrator readAdministratorById(String adminId)
    {
        if (adminId == null || adminId.isEmpty())
        {
            throw new IllegalArgumentException("Administrator ID cannot be null or empty");
        }
        return userDao.readAdministratorById(adminId);
    }

    public void deleteAdministrator(String adminId)
    {
        if (readAdministratorById(adminId) == null)
        {
            throw new IllegalArgumentException("Administrator with ID " + adminId + " not found");
        }
        userDao.removeAdministrator(adminId);
    }

    public void createRegularUser(RegularUser user)
    {
        if (user == null || user.getUserId() == null)
        {
            throw new IllegalArgumentException("RegularUser or ID cannot be null");
        }
        if (userDao.readRegularUserById(user.getUserId()) != null)
        {
            throw new IllegalArgumentException("A RegularUser with this ID already exists");
        }
        userDao.createRegularUser(user);
    }

    public RegularUser readRegularUserById(String userId)
    {
        if (userId == null || userId.isEmpty())
        {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userDao.readRegularUserById(userId);
    }

    public RegularUser readRegularUserByName(String userName)
    {
        if (userName == null || userName.isEmpty())
        {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userDao.readRegularUserByName(userName);
    }

    public void deleteRegularUser(String userId)
    {
        if (readRegularUserById(userId) == null)
        {
            throw new IllegalArgumentException("RegularUser with ID " + userId + " not found");
        }
        userDao.removeRegularUser(userId);
    }

    public List<Administrator> getAllAdministrators()
    {
        return new ArrayList<>(userDao.getAllAdministrators().values());
    }

    public List<RegularUser> getAllRegularUsers() {
        return new ArrayList<>(userDao.getAllRegularUsers().values());
    }

   public List<Bid> getAllBidsByUserId(String userId)
   {
        RegularUser user = readRegularUserById(userId);
        if (user == null)
        {
            throw new IllegalArgumentException("RegularUser with ID " + userId + " not found");
        }
        return user.getBids();
    }

    public List<RegularUser> getUsersByBidOnItem(UUID itemId)
    {
        return getAllRegularUsers().stream()
                .filter(user -> user.getBids().stream().anyMatch(bid -> bid.getItem().getItemId().equals(itemId)))
                .collect(Collectors.toList());
    }


}
