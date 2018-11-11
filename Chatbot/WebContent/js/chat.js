$( document ).ready(function() {
    
	// Hide body and footer on initialization
	$('#body').hide();
	$('#footer').hide();
	var hidden = true;
	
	$('#header').on('click', () => {
		$('.body').toggle();
		$('.footer').toggle();
		
	});
	
	// Enter key while typing
	$('#human-message').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13')
			addHumanMessage();
	});
});

function showDivBottom() {
	var element = document.getElementById("body");
	element.scrollTop = element.scrollHeight;
}

function addHumanMessage() {
	
	// Immediately disable additional inputs until current message is not replied to
	$("#human-message").attr("disabled", "disabled");
	
	var message = $('#human-message').val();
	if(message == '')
		return false;
	$('#human-message').val('');
	$('#body').append('<div class="human-chat"><span class="human">'+message+'</span><span class="human-image"></span></div>');
	showDivBottom();
	
	// Show bot loading message
	addBotMessage();
	
	// Server call with data
	$.post("Controller", { message : message},
		function(data) {
			// alert("Data Loaded: " + data);
			if(data!=null) {
				$(".bot-chat .bot").last().removeClass("loading");
				$(".bot-chat .bot").last().append(data);
			}
			
			// Allow user to ask more questions
			$("#human-message").removeAttr("disabled"); 
		}
	);
	
}

function addBotMessage() {
	$('#body').append('<div class="bot-chat"><span class="chatbot-image"></span><span class="bot loading"></span></div>');
	showDivBottom();
}