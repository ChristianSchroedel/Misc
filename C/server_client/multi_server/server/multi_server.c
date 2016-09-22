#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <time.h>

#define BUF 1024

/**
 * Prints an error message and exits.
 *
 * @param error_msg - Error message to print
 */
void error_exit(char *error_msg)
{
	fprintf(stderr, "%s: %s\n", error_msg, strerror(errno));
	exit(1);
}

/**
 * Creates a new socket.
 * 
 * @param af - Type of address
 * @param type - Type of transtport layer protocol
 * @param protocol - Protocol
 * @return - Created socket
 */
int create_socket(int af, int type, int protocol)
{
	int sock;
	const int y = 1;

	if ((sock = socket(af, type, protocol)) < 0)
		error_exit("Could not create socket");

	setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &y, sizeof(int));

	return sock;
}

/**
 * Binds connection settings to socket.
 *
 * @param sock - Socket to bind to
 * @param address - Address information
 * @param port - Port
 */
void bind_socket(int *sock, unsigned long address, unsigned short port)
{
	struct sockaddr_in server;

	memset(&server, 0, sizeof(server));

	server.sin_family = AF_INET;
	server.sin_addr.s_addr = htonl(address);
	server.sin_port = htons(port);

	if (bind(*sock, (struct sockaddr *)&server, sizeof(server)) < 0)
		error_exit("Could not bind socket");
}

/**
 * Sets how many client can be queued.
 *
 * @param sock - Socket
 */
void listen_socket(int *sock)
{
	if (listen(*sock, 5) == -1)
		error_exit("Failure on listen");
}

/**
 * Accepts connection between sockets (blocks until client connected!).
 *
 * @param socket - Socket waiting for connection
 * @param new_socket - Socket trying to connect
 */
void accept_socket(int *socket, int *new_socket)
{
	struct sockaddr_in client;
	unsigned int len;

	len = sizeof(client);

	if ((*new_socket = accept(*socket, (struct sockaddr *)&client, &len)) < 0 )
		error_exit("Failure on accept");	
}

/**
 * Receives data from a socket connection.
 *
 * @param socket - Socket to receive data from
 * @param data - buffer to store data in
 * @param size - length of buffer
 */
void receive_data(int *socket, char *data, size_t size)
{
	unsigned int len;

	len = read(*socket, data, size);

	if (len > 0 || len != -1)
		data[len] = '\0';
	else
		error_exit("Failure on receiving data");
}

/**
 * Creates a callback message for the client.
 *
 * @param msg - Message received from client
 * @return - Created callback message from server
 */
char *create_callback(char *msg)
{
	time_t ticks = time(NULL);

	char *bgn_tok = "Received message: '";
	char *mid_tok = "' at ";
	char *time_stamp = ctime(&ticks);

	char *callback = malloc(
						(strlen(bgn_tok) +
						strlen(msg) +
						strlen(mid_tok) +
						strlen(time_stamp)+1) * sizeof(char));

	memset(callback, 0, strlen(callback));

	strcat(callback, bgn_tok);
	strcat(callback, msg);
	strcat(callback, mid_tok);
	strcat(callback, time_stamp);

	return callback;
}

int main(int argc, char *argv[])
{
	int server_socket;
	int new_socket;
	int client_socket;

	int i;
	int ready;
	int socket_max;
	int max = -1;

	// Possible client sockets are stored here (max is FD_SETSIZE)
	int client_sockets[FD_SETSIZE];
	// Holds all socket connections
	fd_set all_sockets;
	fd_set read_socket;

	char *buffer = malloc(BUF);

	// Create our server socket, bind it and listen to it for connections
	socket_max = server_socket = create_socket(AF_INET, SOCK_STREAM, 0);
	bind_socket(&server_socket, INADDR_ANY, 5000);
	listen_socket(&server_socket);

	// Initialize all possible client connections as invalid
	for (i = 0; i < FD_SETSIZE; i++)
		client_sockets[i] = -1;

	// Set all FDs empty
	FD_ZERO(&all_sockets);
	// Add our server socket to FDs
	FD_SET(server_socket, &all_sockets);

	printf("======================\n");
	printf("= Server is alive!!! =\n");
	printf("======================\n");

	while (1)
	{
		read_socket = all_sockets;

		// Returns amount of sockets that are ready to send data
		ready = select(socket_max+1, &read_socket, NULL, NULL, NULL);

		// Check if our server is alive
		if (FD_ISSET(server_socket, &read_socket))
		{
			// Connect new client with server
			accept_socket(&server_socket, &new_socket);

			for (i = 0; i < FD_SETSIZE; i++)
			{
				// Add client at prev invalid socket position
				if (client_sockets[i] < 0)
				{
					printf("Added new client\n");
					client_sockets[i] = new_socket;
					break;
				}
			}

			if (i == FD_SETSIZE)
				error_exit("Server overloaded - too many clients");

			// Add new client to socket connections
			FD_SET(new_socket, &all_sockets);

			// Set the new socket as last socket
			if (new_socket > socket_max)
				socket_max = new_socket;

			if (i > max)
				max = i;

			if (--ready <= 0)
				continue;
		}

		// Receive messages from all sockets which are ready
		for (i = 0; i <= max; i++)
		{
			// Get valid client socket
			if ((client_socket = client_sockets[i]) < 0)
				continue;

			// Check if socket is connected to server
			if (FD_ISSET(client_socket, &read_socket))
			{
				// Receive data from client
				receive_data(&client_socket, buffer, BUF-1);
				printf("Received message: %s\n", buffer);

				// Send a callback to the client
				char *callback = create_callback(buffer);
				// -1 is returned if client is no longer alive
				int res = send(
							client_socket,
							callback,
							strlen(callback),
							MSG_NOSIGNAL);

				free(callback);

				// Check if client is alive or about to finish
				if (res < 0 || !strcmp(buffer, "quit"))
				{
					// Close connection to client, remove it from active socket
					// connections and set its index back to invalid
					close(client_socket);
					FD_CLR(client_socket, &all_sockets);
					client_sockets[i] = -1;
					printf("Client closed connection\n");
				}

				// Empty our message buffer
				memset(buffer, 0, strlen(buffer));

				if (--ready <= 0)
					break;
			}
		}
	}

	printf("Server shut down\n");

	return 0;
}
