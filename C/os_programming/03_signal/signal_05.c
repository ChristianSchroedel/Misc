/**
 * Praktikum Betriebssysteme
 *
 * Uebung 03 signals
 *
 * Author: Christian Schroedel
 * Date: 2013-06-06	
 * History: Running
 */

#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

volatile sig_atomic_t keep_going = 1;

void do_stuff(void);
void sigFunction(int signo);

int main()
{
	struct sigaction signalHandlerALRM;

	// Initialize mask
	sigset_t mask;
	sigemptyset(&mask);

	// Initialize signal handler
	signalHandlerALRM.sa_handler = sigFunction;
	signalHandlerALRM.sa_mask = mask;
	signalHandlerALRM.sa_flags = 0;

	// Check if sigaction was successful
	if( sigaction(SIGALRM, &signalHandlerALRM, NULL) == -1 )
		fprintf(stderr, "Error on handling SIGALRM\n");
	
	if( sigaction(SIGINT, &signalHandlerALRM, NULL) == -1 )
		fprintf(stderr, "Error on handling SIGINT\n");

	// Set alarm timer to 3 seconds
	alarm(3);

	while( keep_going )
	{
		do_stuff();
	}

	return 0;
}

void do_stuff(void)
{
	static int ct = 0;
	int i = 0;

	ct += 1;
	printf("Starting chunk number (%d) ...\n", ct);

	for (i=0; i<200; ++i)
	{
		usleep(1000);
	}

	printf("Finished chunk number (%d).\n", ct);
}

void sigFunction(int signo)
{
	// Switch sig number
	if( signo == SIGALRM )
	{
		printf("Received SIGALRM, Time is up\n");
		// Leave if SIGALRM received
		exit(signo);
	}	
	else if( signo == SIGINT )
	{	
		printf("Received SIGINT\n");
		// Break loop if SIGINT received
		keep_going = 0;
	}
}
