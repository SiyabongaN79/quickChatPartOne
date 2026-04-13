package com.mycompany.quickchat;

//Importing the scanner to prompt the user
import java.util.*;


//creating a template or blueprint of a chatting app using a class and methods
class userRegistrationAndLogin
{
    //Creating a scanner object on a global scanner so that any method that wants to access the scanner in this class can
    Scanner userInput = new Scanner(System.in);
    
    //Chatting app attributes
    String storeduserName;
    String storedpassword;
    String storedcellPhoneNumber;
    boolean isLoggedIn = false;
    
     //Creating a boolean method to validate user's username
   public boolean checkUserName(String userName)
    {
        //Using a conditional statement to validate the user's username
        if (userName.length()== 5 && userName.contains("_"))
        {
            System.out.println("Username successfully captured.");
            
              return true;
        }
        else
        {
            System.out.print("User name is not correctly formatted; ");
            System.out.print("please ensure that the username contains an underscore and is no more than five characters in length. ");
           
            return false;
        }
        
    }

public class QuickChat {

    public static void main(String[] args) {
    
    }
}
