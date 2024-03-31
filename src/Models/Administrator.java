package Models;

public class Administrator implements User
{
    private  String userId;
    private String email;
    private  String userName;
    private  String userPassword;

    // implementing administrator specirfic things

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


}
