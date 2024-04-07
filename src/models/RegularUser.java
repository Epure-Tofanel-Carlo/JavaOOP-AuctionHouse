package models;

import java.util.ArrayList;
import java.util.List;
public class RegularUser implements User
{
    private  String userId;
    private String email;
    private  String userName;
    private  String userPassword;
    private  String creditCard;
    private int balance;
    private List<Bid> bids;


    public RegularUser()
    {
    }

    public RegularUser(String userId, String email, String userName, String userPassword, String creditCard, int balance)
    {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.userPassword = userPassword;
        this.creditCard = creditCard;
        this.balance = balance;
        this.bids = null;
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

    public String getCreditCard()
    {
        return creditCard;
    }

    public int getBalance()
    {
        return balance;
    }

    public List<Bid> getBids() {
        return bids;
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
    public void addBid(Bid bid) {
        bids.add(bid);
    }

    @Override
    public String toString() {
        return "RegularUser{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", balance=" + balance +
                ", bids=" + bids +
                '}';
    }
}
