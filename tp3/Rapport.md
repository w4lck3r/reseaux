# TP3

## Salmi badr-Eddine

### Questions 

### Exercice 1
1. 
- Initialization of the server socket.
- Processing a request involves accepting the connection. 
- Reading on the client socket.
- Sending the welcome message.
- Closing the client socket.

2. 
- Exceptions are raised for different points: 
    * The creation of the server socket.
    * The acceptance of the connection of a client socket to our server.
    * The error of sending and retrieving the OutPutStream of the client socket.
    * The error on the closing of the client socket.*

3. 
* We execute the java program which allows to launch our server on listening in waiting of connection. 
* We then use telnet to create a connection with the server.
    ```bash 
    telnet
    open 127.18.12.229 7654
    ```
* This shows us that the message is sent to the client and the connection is broken.

4. 
* The treatment works in a loop because we use an infinite loop
    ```
    while(True)
    ``` 
- We will be able to identify each different client socket connection and display them in the server's outputstream or log file.



### Exercice 2

1. 
- We have to create a new Thread for each client connection when the server is waiting with the accept method.
- It is enough to make a Thread of our client socket captured by the accept method and thus treat in a different Thread the different actions.

2. 
* By retrieving the input stream from the socket.

3. 
* To retransmit the strings we store the clients in a list. So we only have to iterate on it and get the output stream of all the sockets in the list except the socket that sent the string itself.

4. 
- We keep track of the client's acceptance, we print his information such as his
    * inetAdress
    * remote port
    * local port