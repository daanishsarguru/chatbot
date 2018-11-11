package com;

import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.DialogRuntimeResponseGeneric;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeIntent;

public class TestChatbotClass {
  public static void main(String[] args) {

	  Scanner sc = new Scanner(System.in); 
	  
    // Suppress log messages in stdout.
    LogManager.getLogManager().reset();

    // Set up Assistant service.
    Assistant service = new Assistant("2018-07-10");
    service.setUsernameAndPassword("1e89cd9a-fc4f-4455-a518-0d3d1cfa3b2b", // replace with service username
                                   "hA7UEyNs3PU3"); // replace with service password
    String workspaceId = "53a9983c-c2f6-4de8-b944-623ac1967ceb"; // replace with workspace ID

    // Initialize with empty value to start the conversation.
    MessageOptions options = new MessageOptions.Builder(workspaceId).build();

    // Main input/output loop
    do {
      // Send message to Assistant service.
      MessageResponse response = service.message(options).execute();

      // If an intent was detected, print it to the console.
      List<RuntimeIntent> responseIntents = response.getIntents();
      if(responseIntents.size() > 0) {
        System.out.println("Detected intent: #" + responseIntents.get(0).getIntent());
      }

      // Print the output from dialog, if any. Assumes a single text response.
      List<DialogRuntimeResponseGeneric> responseGeneric = response.getOutput().getGeneric();
      if(responseGeneric.size() > 0) {
        System.out.println(responseGeneric.get(0).getText());
      }

      // Prompt for next round of input.
      String inputUser = sc.nextLine(); 
      System.out.print(">> "+inputUser);
      //String inputText = System.console().readLine();
      String inputText = inputUser;
      InputData input = new InputData.Builder(inputText).build();
      options = new MessageOptions.Builder(workspaceId).input(input).build();
    } while(true);
  }
}
