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

typedef void (*sighandler_t)(int);

void signalFunctionINT(int signo);

int main()
{
	sighandler_t sigHandler = signalFunctionINT;
	signal(SIGINT, sigHandler);
	
	while( 1 )
	{
		sleep(1);
	}
	
	return 0;
}

void signalFunctionINT(int signo)
{
	static int warning = 0;

	if( warning == 0 && signo == SIGINT )
	{
		printf("  Received SIGINT, are you sure?\n");
		warning = 1;
	}
	else if( warning == 1 && signo == SIGINT )
	{
		printf("  Bye bye\n");
		exit(signo);
	}
}
