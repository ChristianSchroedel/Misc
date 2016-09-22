#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>

int main(int argc, char *argv[])
{
	int sock_fd = 0;
	int n = 0;
	char recv_buff[1024];
	struct sockaddr_in serv_addr;

	// Check if number of arguments is valid
	if (argc != 2)
	{
		printf("\nUsage: %s <IP of server> \n", argv[0]);
		return 1;
	}

	// Initialize our buffer with zero
	memset(recv_buff, '0', sizeof(recv_buff));

	if ((sock_fd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		printf("\nError: Could not create socket\n");
		return 1;
	}

	// Initialize our socket object with zero
	memset(&serv_addr, '0', sizeof(serv_addr));

	// Set family/domain
	serv_addr.sin_family = AF_INET;
	// Set port to send to
	serv_addr.sin_port = htons(5000);

	// Convert human readable IP address into binary format to use it in our
	// socket object
	if (inet_pton(AF_INET, argv[1], &serv_addr.sin_addr) <= 0)
	{
		printf("\ninet_pton error occured\n");
		return 1;
	}

	// Connect to server
	if (connect(sock_fd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
	{
		printf("\nError: Connect failed\n");
		return 1;
	}

	// Read data from server
	while ((n = read(sock_fd, recv_buff, sizeof(recv_buff)-1)) > 0)
	{
		// Set null after data was received
		recv_buff[n] = 0;

		// Output received data
		if (fputs(recv_buff, stdout) == EOF)
			printf("\nError: Fputs error\n");
	}

	if (n < 0)
		printf("\nRead error\n");

	return 0;
}
