package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.LogManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.DialogRuntimeResponseGeneric;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeIntent;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public Controller() {
		service.setUsernameAndPassword("1e89cd9a-fc4f-4455-a518-0d3d1cfa3b2b", // replace with service username
                "hA7UEyNs3PU3"); // replace with service password
	}
	
	// Set up Assistant service.
    static Assistant service = new Assistant("2018-07-10");
    static String workspaceId = "53a9983c-c2f6-4de8-b944-623ac1967ceb"; // replace with workspace ID
    
    // Initialize with empty value to start the conversation.
    static MessageOptions options = new MessageOptions.Builder(workspaceId).build();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String humanMessage = request.getParameter("message");
		String botReply = getBotReply(humanMessage);
		PrintWriter writer = response.getWriter();
		writer.println(botReply);
	}
	
	
	public String getBotReply(String humanMessage) {
		
		String botReply = "";
		
		LogManager.getLogManager().reset();

	    
		// Send message to Assistant service.
		MessageResponse response = service.message(options).execute();

		// If an intent was detected, print it to the console.
		List<RuntimeIntent> responseIntents = response.getIntents();
		if (responseIntents.size() > 0) {
			System.out.println("Detected intent: #" + responseIntents.get(0).getIntent());
		}

		// Print the output from dialog, if any. Assumes a single text response.
		List<DialogRuntimeResponseGeneric> responseGeneric = response.getOutput().getGeneric();
		if (responseGeneric.size() > 0) {
			botReply = responseGeneric.get(0).getText();
			System.out.println(botReply);
		}
		System.out.print(">> " + humanMessage);
		InputData input = new InputData.Builder(humanMessage).build();
		options = new MessageOptions.Builder(workspaceId).input(input).build();
		
		return botReply;
	}

}
