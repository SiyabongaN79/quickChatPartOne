package com.mycompany.quickchat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// Message class
class Message {
    
    String messageID;
    String recipient;
    String messageText;
    String messageHash;
    int messageNumber;
   static int  totalMessages = 0;
    
    //creating a random object to create a random number
    Random random = new Random();
    
      // Constructor
    public Message(String recipient, String messageText, int messageNumber) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
        totalMessages++;
    }
    
    //Generate 10 random digits using a for loop and a string builder
    public String generateMessageID(){
        
        StringBuilder messageID = new StringBuilder();
        
        //Use a for loop to generate the ten unique numbers
        
        for (int i = 0; i < 10; i++){
            
            messageID.append(random.nextInt(10));
        }
        
        return String.valueOf(messageID);
    }
 
    // Validate ID length
    public boolean checkMessageID() {
        return messageID.length() == 10;
    }
    
     // Validate recipient cell number
    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 10) {
            return "Recipient number valid.";
        } else {
            return "Recipient number invalid. Must start with +27 and be <= 10 characters.";
        }
    }
    
     // Create message hash
    public String createMessageHash() {
        String[] words = messageText.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }
    
      // Allow user to choose send/store/disregard
    public String sentMessage(Scanner sc) {
        System.out.println("\nChoose an option:");
        System.out.println("1. Send message");
        System.out.println("2. Disregard message (press 0 to delete)");
        System.out.println("3. Store message to send later");

        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {
            case 1:
                return "Message successfully sent";
            case 0:
                return "Message deleted";
            case 3:
                storeMessage();
                return "Message successfully stored";
            default:
                return "Invalid choice";
        }
    }
    
        // Print message details
    public String printMessages() {
        return "Message ID: " + messageID + "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    // Return total messages
    public static int returnTotalMessages() {
        return totalMessages;
    }
    
      // Store message in JSON file
    public void storeMessage() {
        JSONObject msgObj = new JSONObject();
        msgObj.put("MessageID", messageID);
        msgObj.put("MessageHash", messageHash);
        msgObj.put("Recipient", recipient);
        msgObj.put("Message", messageText);

        JSONArray msgArray = new JSONArray();
        msgArray.add(msgObj);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(msgArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void startMessaging(){
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to QuickChat!");

        System.out.print("How many messages do you want to send? ");
        int numMessages = sc.nextInt();
        sc.nextLine(); 

        int messageCounter = 1;

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Send Message");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Quit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (messageCounter <= numMessages) {
                        System.out.print("Enter recipient cell number (number should start with +27 the followed by ten digits1): ");
                        String recipient = sc.nextLine();

                        System.out.print("Enter message (message should have a maximum of 250 characters): ");
                        String messageText = sc.nextLine();

                        if (messageText.length() > 250) {
                            System.out.println("Please enter a message of less than 250 characters.");
                            break;
                        }

                        Message msg = new Message(recipient, messageText, messageCounter);
                        System.out.println(msg.printMessages());
                        System.out.println(msg.sentMessage(sc));

                        messageCounter++;
                    } else {
                        System.out.println("You have reached the maximum number of messages.");
                    }
                    break;

                case 2:
                    System.out.println("Coming soon...");
                    break;

                case 3:
                    System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    System.out.println("Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        
        
    }
    
}



