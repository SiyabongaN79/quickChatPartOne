package com.mycompany.quickchat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// Message class
class Message {
    
    //Arrays for storing 
    static int max = 100;
    static String [] messageIDs = new String[max];
    static String [] messageHashes = new String [max];
    static String [] disregardedMessages = new String [max];
    static String [] storedMessages = new String [max];
    static String [] sentMessages = new String [max];
    static String [] Recipient = new String [max];
    
    
    static Scanner input = new Scanner(System.in);
    String messageID;
    String recipient;
    String messageText;
    String messageHash;
    int messageNumber;
   static int  totalMessages = 0;
   static int disregaredCount = 0;
   static int sentMessageCount = 0;
    
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
    System.out.println("2. Disregard message");
    System.out.println("3. Store message to send later");
    System.out.println("4. Stored Message");

    int choice = sc.nextInt();
    sc.nextLine(); // consume newline

    switch (choice) {
        case 1:
            System.out.println("Welcome to QuickChat!");
            System.out.print("How many messages do you want to send? ");
            int numMessages = sc.nextInt();
            sc.nextLine();

            int messageCounter = 1;

            while (messageCounter <= numMessages) {
                System.out.print("Enter recipient cell number (must start with +27 followed by 10 digits): ");
                String recipient = sc.nextLine();

                System.out.print("Enter message (max 250 characters): ");
                String messageText = sc.nextLine();

                if (messageText.length() > 250) {
                    System.out.println("Please enter a message of less than 250 characters.");
                    continue; // skip this iteration, let them try again
                }

                // Create a new Message object
                Message msg = new Message(recipient, messageText, messageCounter);

                // Store details in arrays
                sentMessages[sentMessageCount] = messageText;
                Recipient[sentMessageCount] = recipient;
                messageIDs[sentMessageCount] = msg.messageID;
                messageHashes[sentMessageCount] = msg.messageHash;
                sentMessageCount++;

                // Show confirmation + details
                System.out.println(msg.printMessages());
                System.out.println("Message successfully sent!");

                messageCounter++;
            }
            return "Finished sending " + numMessages + " messages.";

        case 2:
            disregardMessage();
            return "Message disregarded";

        case 3:
              System.out.print("Enter recipient cell number (must start with +27 followed by 10 digits): ");
              String recipient = sc.nextLine();

              System.out.print("Enter message (max 250 characters): ");
              String messageText = sc.nextLine();

              if (messageText.length() > 250) {
              System.out.println("Please enter a message of less than 250 characters.");
              return "Message too long";
              }

              // Create a new Message object
              Message msg = new Message(recipient, messageText, sentMessageCount + 1);

              // Store details in arrays
              storedMessages[sentMessageCount] = messageText;
              Recipient[sentMessageCount] = recipient;
              messageIDs[sentMessageCount] = msg.messageID;
              messageHashes[sentMessageCount] = msg.messageHash;
              sentMessageCount++;

              // Actually call storeMessage() to write to JSON
              msg.storeMessage();

              System.out.println("Message stored for later sending:");
              System.out.println(msg.printMessages());

              return "Message successfully stored";

        case 4:
            StoredMessageMenu();
            return "Stored message menu opened";

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
        
        public static void disregardMessage(){
        System.out.println("Enter message to disregard");
        String msg = input.nextLine();
        disregardedMessages[disregaredCount++] = msg;
        //System.out.println("Message Disregarded");
        }
        
        public static void StoredMessageMenu(){
            int option;
            
            do{
                System.out.println("\n=========STORED MESSAGE============");
                System.out.println("1.Display sender and Recepient ");
                System.out.println("2.Display the longest message");
                System.out.println("3.Search by message ID");
                System.out.println("4.Search by recipient");
                System.out.println("5.Delete message by hash");
                System.out.println("6.Display full report");
                
                option = input.nextInt();
                
                switch(option){
                    case 1:
                        displayRecepient();
                        break;
                    case 2:
                        displayLongestMessage();
                        break;
                    case 3:
                        System.out.println("Enter the message ID");
                        searchMessageID(input.nextLine());
                        break;
                    case 4:
                        System.out.println("Enter Message recipient");
                        searchByRecipient(input.nextLine());
                        break;
                    case 5:
                        System.out.println("Enter message Hash");
                        deleteMessageByHash(input.nextLine());
                        break;
                    case 6:
                        displayFullReport();
                        break;
                    default :
                        System.out.println("Invalid option selected");
                        
                }
                    
            }
            while(option !=5);
        }
            
            public static void displayRecepient(){
                
                if (sentMessageCount == 0){
                    System.out.println("No stored Messages");
                }
                
                for (int i = 0 ; i < sentMessageCount; i++){
                  System.out.println("Recipient " + Recipient[i]);  
                }
            }  
            
            public static void displayLongestMessage(){
                
                 if (sentMessageCount == 0){
                    System.out.println("No stored Messages");
                }
                
                int longest = 0;
                for (int i = 1; i < sentMessageCount; i++ ){
                    if(storedMessages[i].length()> storedMessages[longest].length()){
                        longest = i;
                    }
                }
                
                System.out.println("\n Longest Message");
                System.out.println("ID " + messageIDs[longest]);
                System.out.println("Recipient " + Recipient[longest]);
                System.out.println("Message " + storedMessages[longest]);
            }
            
            public static void searchMessageID(String id){
                
                for(int i = 0; i < sentMessageCount; i++){
                    
                    if(messageIDs[i].equalsIgnoreCase(id)){
                         System.out.println("Recipient " + Recipient[i]);
                         System.out.println("Message " + storedMessages[i]);
                    }
                }
                
            }
            
            public static void searchByRecipient(String recipient){
                
                boolean found = false;
                for(int i = 0; i < sentMessageCount; i++){
                    if(Recipient[i].equalsIgnoreCase(recipient)){
                        System.out.println("ID " + messageIDs[i]);
                        System.out.println("Recipient " + Recipient[i]);
                        System.out.println("Message " + storedMessages[i]);
                        found = true;
                    }
                }
                
                if(! found){
                    System.out.println("No messages found");
                }
                
            }
            
            public static void deleteMessageByHash(String hash){
                
                for(int i = 0; i < sentMessageCount; i++){
                    if(messageHashes[i].equalsIgnoreCase(hash)){
                        for(int j = i; j < sentMessageCount; j++){
                            messageIDs[j] = messageIDs[j+i];
                            messageHashes[j] = messageHashes[j + i];
                            Recipient[j] = Recipient[j+i];
                            storedMessages[j] = storedMessages[j+i];   
                        }
                        
                        sentMessageCount --;
                        System.out.println("Message deleted successfully.");
                    }
                    
                }
                
            }
            
            public static void displayFullReport(){
                
                System.out.println("\n==========FULL REPORT==========");
                for(int i = 0; i < sentMessageCount; i++){
                    System.out.println("Messages " + (i + 1));
                    System.out.println("ID " + messageIDs[i]);
                    System.out.println("Message Hash " + messageHashes[i]);
                    System.out.println("Recipient " + Recipient[i]);
                    System.out.println("Message " + storedMessages[i]); 
                    System.out.println("\n=============END OF REPORT==============");
                }
            }
 
}



