# S.P.I.E.L. Chat Application

This is a Java network chat application. 
Multiple clients can connect to a single server using TCP/IP.

### User Guide:

##### Server-side information:
-	The server side is the intermediate between the clients; it will serve as the message runner for the clients communicating
-	The server has full control as to who can communicate within its domain; in addition, the server could disconnect one or all clients within the domain.
-	How do I use the server?
1.	Run the server application.
2.	Set your NETWORK PROFILE to PRIVATE. 
3.	To start the connections between clients, click “start” button.
4.	Clients that are connected will be displayed on the display screen.
5.	Messages being sent to clients will be displayed on the display screen.

-	How do I set network to private?
1.	Click the wireless icon on your taskbar.
2.	On the network that you’re connected to click “Properties”
3.	Click the option “Private”

-	How do I disconnect specific clients?
1.	To kick-out specific client, type the client name on the text-field and click “target” button.
	This function is case sensitive, so please enter the exact username.
	Wrong username will cause the application to do nothing.

-	How do I disconnect all clients?
1.	To kick-out all client, click the “fork bomb” button.
	The screen will state the disconnected clients.

-	How do I close the server?
1.	If there are ZERO CLIENTS currently connected to the server:
	Simply click the “exit” button
2.	If there are ONE OR MORE CLIENTS currently connected to the server:
	Click “fork bomb” button and then click “exit” button

-	BUTTONS:
1.	Start button = starts the server
2.	Fork Bomb button = disconnects all client
3.	Exit button = exit the server
4.	Target Client button = disconnects specific client 


##### Client-side information:
-	The client side is used as a way for the users to communicate with multiple clients within the server’s domain.
-	How do I start the client application?
1.	Enter a username in the username text-field.
2.	There are two conditions:
	If the server application is being executed using a remote computer, enter the server computer’s IP ADDRESS.
	If the server application is being executed using the same computer as the client, keep the text-field as “localhost”.
3.	Click the “Connect” button
	If a server is online, the client will connect.
	If a server is offline, the client will display invalid.

-	How do I send messages to other clients? **Assuming client is connected to a server
1.	Enter the message in the text-field.
2.	Click “send” button or simply press enter.

-	How do I disconnect from the server? **Assuming client is connected to a server
1.	Click the “disconnect” button.

-	How do I end the client?
1.	Click the “quit” button.

-	BUTTONS
1.	Connect button = connects to the server
2.	Disconnect button = disconnects client from the server
3.	Send button = sends messages to server
4.	Help button = displays instructions
5.	Quit button = closes the client
