<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>BotUI</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<script src="js/chat.js"></script>
		<link href="css/chat.css" rel="stylesheet" media="screen" />
	</head>

<body>



<div class="chatbot" id="chatbot">
	<!-- Chatbot header This will have the name -->
	<!-- When collapsed, only this will be visible -->
	<div class="header" id="header">
		The BOT
		<i class="material-icons close" id="close">close</i>
	</div>
	
	<!-- Chatbot body, this will have the chats -->
	<div class="body" id="body">
		<div class="bot-chat">
			<span class="chatbot-image"></span>
			<span class="bot">Hi I'm a bot. I don't have a name yet. You can call me whatever you want. I'll help you if you're stuck somewhere!</span>
		</div>
		<!-- <div class="human-chat">
			<span class="human">Hello this is human</span>
			<span class="human-image"></span>
		</div> -->
		
	</div>
	
	<!-- Chatbot text chat, through here the user can enter text messages -->
	<div class="footer" id="footer">
		<input id="human-message" placeholder="Type your message and hit enter" class="text-box" type="text">
		<i class="material-icons send-message" onClick="addHumanMessage()">send</i>
	</div>
	
</div>

</body>

</html>