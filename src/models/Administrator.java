package models;

public class Administrator implements User
{
    private  String userId;
    private String email;
    private  String userName;
    private  String userPassword;

    public Administrator()
    {
    }

    public Administrator(String userId, String email, String userName, String userPassword)
    {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @Override
    public String getUserId()
    {
        return userId;
    }
    @Override
    public String getEmail()
    {
        return email;
    }
    @Override
    public String getUserName()
    {
        return userName;
    }
    @Override
    public String getUserPassword()
    {
        return userPassword;
    }

    @Override
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }
    @Override
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    @Override
    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }


}
