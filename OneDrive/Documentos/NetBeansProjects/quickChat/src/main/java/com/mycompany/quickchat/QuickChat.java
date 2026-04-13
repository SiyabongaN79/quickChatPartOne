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
   
    //Creating a boolean method to validate user password
    boolean checkPasswordComplexity(String password)
    {
        //Using a conditional statement to validate the user's password
        if (password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$"))
            
        { 
            System.out.println("Password successfully captured.");
        
           return true;
        }
        else
        {
            System.out.print("Password is not correctly formatted; please ensure that password");
            System.out.print(" contains atleast eight characters, a capital letter, a number and a special character.");
            
            return false;
        }
    }
    
     //Creating a boolean to validate user's cell phone number
    boolean checkcellPhoneNumber(String cellPhoneNumber)
    {
        //Using a conditional statement to validate the user's phone number
        if (cellPhoneNumber.matches("^\\+[2][7][0-9]{9}$"))
        {
            System.out.println("Cell phone number successfully added.");
            
            return true;
        }
        else
        {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code");
            
            return false;
        }
    }
    
    //Creating a method to register the user
    String registerUser()
    {
       System.out.println("\n ========UserRegistration=======");
       
       //The following lines of code are prompting the user to enter user info using a scanner
       System.out.println("Enter your username, username should be no more than five characters and should contain an underscore.");
       String userName = userInput.nextLine();
       
       System.out.println("Enter the password, password should at least be eight characters long and contain a capital letter, contain a number and a special character.");
       String password = userInput.nextLine();
       
       System.out.println("Enter cell phone number, cell phone number should contain country code and ten digits.");
       String cellPhoneNumber = userInput.nextLine();
       
       //Using a conditional statement to verify if the user's input matches the conditions set out on the username,password and phonenumber methods
       if ( checkUserName(userName) && checkPasswordComplexity(password) && checkcellPhoneNumber(cellPhoneNumber) )
           
       { 
           storeduserName = userName;
           storedpassword = password;
           storedcellPhoneNumber = cellPhoneNumber;
           
           return "The above conditions have been met, user has been registered successfully";
       }
       
       else
       {
           return "Invalid details please try again";
       }
    }
    
     //Creating a boolean to verify the log in details entered match the log in details stored when the user registers.
    boolean LoginUser()
    {
       System.out.println("\n ========UserLogin=======");
        
       System.out.println("Enter your username, username should be no more than five characters and should contain an underscore.");
       String userName = userInput.nextLine();
       
       System.out.println("Enter the password, password should at least be eight characters long and contain a capital letter, contain a number and a special character.");
       String password = userInput.nextLine();
       
       System.out.println("Enter cell phone number, cell phone number should contain country code and ten digits.");
       String cellPhoneNumber = userInput.nextLine();
        
       
       
       
       if (storeduserName.equals(userName) && storedpassword.equals(password) && storedcellPhoneNumber.equals(cellPhoneNumber))
        {
            isLoggedIn = true;
            return true;
        }
        else
            return false;
         
    }

public class QuickChat {

    public static void main(String[] args) {
    
    }
}
