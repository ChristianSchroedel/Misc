/**
 * Praktikum Betriebssysteme
 *
 * Uebung 03 signals
 *
 * Author: Christian Schroedel
 * Date: 2013-06-06
 * History: running
 */

#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void signalFunction(int signo);

typedef void (*signalhandler_t)(int);

int main()
{
	// Initialize new signal handler with handler function
	signalhandler_t sigHandler = signalFunction;

	// Register new signals to signal handler
	signal(SIGUSR1, sigHandler);
	signal(SIGUSR2, sigHandler);

	while( 1 )
	{
		sleep(1);
	}
	
	return 0;
}

void signalFunction(int signo)
{
	// Switch sig numbers
	if( signo == SIGUSR1 )
		printf("Received signal %d: SIGUSR1\n", signo);
	else if( signo == SIGUSR2 )
		printf("Received signal %d: SIGUSR2\n", signo);
}
