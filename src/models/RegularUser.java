package models;

public class RegularUser implements User
{
    private  String userId;
    private String email;
    private  String userName;
    private  String userPassword;
    private  String creditCard;
    private int balance;

    // implemeting user specific things beside credita card and balance

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

    public String getCreditCard()
    {
        return creditCard;
    }

    public int getBalance()
    {
        return balance;
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

    public void setCreditCard(String creditCard)
    {
        this.creditCard = creditCard;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }
}
