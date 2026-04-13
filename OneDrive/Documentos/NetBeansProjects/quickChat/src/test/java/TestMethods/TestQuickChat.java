package TestMethods;

import com.mycompany.quickchat.QuickChat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestQuickChat 
{
    QuickChat quickchat = new QuickChat();
    
    @Test
    public void TestUserNameValid()
    {
        String expected = "Kyl_1";
        boolean actual = quickchat.checkUserName("Kyl_1");
        assertEquals(actual, "Welcome user, it is great to see you again");
    }
    
     @Test
    public void TestUserNameInvalid()
    {
        String expected = "Kyl_1";
        boolean actual = quickchat.checkUserName("Kyle!!!!!");
        assertEquals(actual, "Username is not correctly formatted, please ensure that your username contains an underscore");
    }
    
     @Test
    public void TestPasswordComplexityValid()
    {
        String expected = "Ch&&sec@ke99!";
        boolean actual = quickchat.checkPasswordComplexity("Ch&&sec@ke99!");
        assertEquals(actual, "Password successfully captured");
    }
    
     @Test
    public void TestPasswordComplexityInvalid()
    {
        String expected = "Ch&&sec@ke99!";
        boolean actual = quickchat.checkPasswordComplexity("password");
        assertEquals(actual, "Password incorrectly formatted; please ensure that password cointains atleast eight characters, a capital letter, a number and an underscore");
    }
    
     @Test
    public void TestCellPhoneNumber()
    {
        String expected = "+27838968976";
        boolean actual = quickchat.checkcellPhoneNumber("08966553");
        assertEquals(actual, "cell number successfully captured");
    }
    
     @Test
    public void TestCellPhoneNumberInvalid()
    {
        String expected = "+27838968976";
        boolean actual = quickchat.checkcellPhoneNumber("+27838968976");
        assertEquals(actual, "cell number is incorectly formatted or does not contain an international code; please correct the number and try again");
        
    }
    

    

}
