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
        if (userName.length()<= 5 && userName.contains("_"))
        {
            //Alerting the user that they have successfully captured their username if the condition for the username is met
            System.out.println("Username successfully captured.");
            
              return true;
        }
        else
        {
            //Otherwise the system will make the user aware if their username format does not match the condition for the username
            System.out.print("User name is not correctly formatted; ");
            System.out.print("please ensure that the username contains an underscore and is no more than five characters in length. ");
           
            return false;
        }
        
    }
   
    //Creating a boolean method to validate user password
    public boolean checkPasswordComplexity(String password)
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
    public boolean checkcellPhoneNumber(String cellPhoneNumber)
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
            System.out.println("You have logged in successfully");
            return true;
        }
        else
            return false;
         
    }
    
      //Creating a string method to return the login status
    
    String returnLoginStatus()
    {
     
        //Prompting the user to enter their full name so i can display their full name with the log in status
        System.out.print("Enter your first name: ");
        String userFirstName = userInput.nextLine();
        
        System.out.print("Enter your Last name: ");
        String userLastName = userInput.nextLine();
       
        //Creating a condition to validate the login status using isLoggedIn from the login page as a condition
      if (isLoggedIn)
      {
          return "Welcome " + userFirstName + " " + userLastName + " it is great to see you again.";
      }
       else
      {
          return "Username or password incorrect, please try again.";
      }
        
         
    }
    

}

public class QuickChat {

    public static void main(String[] args)
    {
          //Creating a scanner for the user to select an option from the menu page
        Scanner enterMenu = new Scanner(System.in);
        
        userRegistrationAndLogin chattingApp = new userRegistrationAndLogin();
        
        int choice;
        
        //Using a do while loop to repeatedly ask the user to choose an option on the menu page
        do
        {
            System.out.println("\n=======MENU======");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Login Status");
            System.out.println("4.Exit");
            
            System.out.println("Enter menu options");
            
            choice = enterMenu.nextInt();
            
            //Using a switch statement to give the user multiple options to choose from on the menu page
            switch(choice)
            {
                case 1:
                    chattingApp.registerUser();
                   break;
                   
                case 2:
                     chattingApp.LoginUser();
                     break;
                     
                case 3:
                     System.out.println(chattingApp.returnLoginStatus());
                     break;
                     
                case 4:
                    System.out.println("You have exited the menu page, goodbye");
                    break;
                    
                default:
                    System.out.println("You have entered an invalid option, please try again.");
                          
            }
        
        }
        //The menu page closes if the user chooses option 4  
        while(choice != 4);
         {
            enterMenu.close();
         }
    
    }
}
