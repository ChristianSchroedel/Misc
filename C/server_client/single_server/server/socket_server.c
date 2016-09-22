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

int main(int argc, char *argv[])
{
	int listen_fd = 0;
	int conn_fd = 0;
	struct sockaddr_in serv_addr;

	char send_buff[1025];
	time_t ticks;

	// Creates unnamed socket, returns socket descriptor
	// 'AF_INET' -> IPv4 addresses, 'SOCK_STREAM' -> use reliable transport
	// layer protocol (e.g. TCP), '0' -> default value for protocol to use
	// (TCP)
	listen_fd = socket(AF_INET, SOCK_STREAM, 0);
	// Initialize everything with zero
	memset(&serv_addr, '0', sizeof(serv_addr));
	memset(send_buff, '0', sizeof(send_buff));

	// Set family/domain
	serv_addr.sin_family = AF_INET;
	// Set Interface to listen to
	serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	// Set port for client requests
	serv_addr.sin_port = htons(5000);

	// Assigns details specified in 'serv_addr' to created socket
	bind(listen_fd, (struct sockaddr *)&serv_addr, sizeof(serv_addr));

	// Set socket listening, '10' specifies maximum number of queued client
	// connections
	listen(listen_fd, 10);

	while (1)
	{
		// Blocks until incoming client request -> returns client socket
		// descriptor
		conn_fd = accept(listen_fd, (struct sockaddr *)NULL, NULL);

		ticks = time(NULL);
		snprintf(send_buff, sizeof(send_buff), "%.24s\r\n", ctime(&ticks));
		// Send current time from 'send_buff' to client socket
		write(conn_fd, send_buff, strlen(send_buff));

		// Closes connection to client
		close(conn_fd);
		sleep(1);
	}

	return 0;
}
