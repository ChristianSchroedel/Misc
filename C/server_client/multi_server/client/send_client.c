#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

int main(int argc, char *argv[])
{
	int sock_fd = 0;
	int n = 0;
	char recv_buff[1024];
	struct sockaddr_in serv_addr;

	// We can't connect without IP address
	if (argc != 2)
	{
		printf("Usage: %s <IP>\n", argv[0]);
		return 1;
	}

	// Initialize our buffer
	memset(recv_buff, 0, sizeof(recv_buff));

	// Create a socket
	if ((sock_fd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		fprintf(stderr, "Could not create socket\n");
		return 1;
	}
	
	// Initialize our socket
	memset(&serv_addr, 0, sizeof(serv_addr));

	// Set our connection parameter (type, port, address)
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(5000);

	if (inet_pton(AF_INET, argv[1], &serv_addr.sin_addr) <= 0)
	{
		fprintf(stderr, "Could not convert IP address\n");
		return 1;
	}

	// Connect to server
	if (connect(sock_fd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
	{
		fprintf(stderr, "Could not connect to %s\n", argv[1]);
		return 1;
	}

	// Do our work until we are done
	while (1)
	{
		char msg[100];

		memset(msg, 0, 100);
		printf("Enter your message (max 100 characters): \n");

		// Get entered line
		fgets(msg, 100, stdin);

		// Do nothing if there is nothing to send
		if (!strlen(msg))
			continue;

		// Remove new line
		if ((strlen(msg) > 0) && (msg[strlen(msg)-1] == '\n'))
			msg[strlen(msg)-1] = '\0';

		// Send message to server
		write(sock_fd, msg, strlen(msg));

		// Wait for callback
		if ((n = read(sock_fd, recv_buff, sizeof(recv_buff)-1)) <= 0)
		{
			fprintf(
				stderr,
				"Receiving answer from server '%s' failed\n",
				argv[1]);

			break;
		}

		recv_buff[n] = 0;

		printf("Received callback from server:\n");
		printf("%s\n", recv_buff);

		// Finish if quit was sent
		if (!strcmp(msg, "quit"))
		{
			printf("Shutting down client ...\n");
			close(sock_fd);
			break;
		}

	}

	printf("Client shut down\n");

	return 0;
}
