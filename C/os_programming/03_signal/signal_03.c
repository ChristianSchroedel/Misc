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
#include <stdlib.h>

// Function called by signal handler
void signalFunctionINT(int signo);

int main()
{
	struct sigaction signalHandlerINT;

	// Initialize mask
	sigset_t mask;
	sigemptyset(&mask);

	// Initialize signal handler
	signalHandlerINT.sa_handler = signalFunctionINT;
	signalHandlerINT.sa_mask = mask;
	signalHandlerINT.sa_flags = 0;

	// Check if sigaction was successful
	if( sigaction(SIGINT, &signalHandlerINT, NULL) == -1 )
		fprintf(stderr, "Error on handling SIGINT\n");

	while( 1 )
	{
		sleep(1);
	}
	
	return 0;
}

void signalFunctionINT(int signo)
{
	static int warning = 0;

	// Warning if SIGINT is received
	if( warning == 0 && signo == SIGINT )
	{
		printf("  SIGINT received, are you sure?\n");
		warning = 1;
	}
	// Exit on second SIGINT
	else if( warning == 1 && signo == SIGINT)
	{	
		printf("  Bye Bye\n");
		exit(signo);
	}
}
