/**
 * Praktikum Betriebssysteme
 *
 * Uebung 03 fork/exec
 *
 * Author: Christian Schroedel
 * Date: 2013-05-18
 * History: Added error handling and comments
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <wait.h>
#include <unistd.h>

/**
 * Main routine
 */
int main()
{
	int state = 0;
	int i;

	pid_t childPID = fork(); // Create new child

	// Child creates an other child process which creates another and so on ... up to 10 children overall
	for( i = 0; i < 10; i++ )
	{		
		if( !childPID )
		{
			printf("Child: started. PID: %d\n", getpid());
			sleep(1); // Delay output
			childPID = fork();
		}
		else if( childPID == -1 )
		{
			fprintf(stderr, "Error on fork - no new child");
			exit(1);
		}
		else if( childPID > 0 )
		{
			childPID = wait(&state); // Wait for exit of own child

			if( childPID > 0 )
			{
				printf("Child: exited. PID: %d\n", childPID);
				sleep(1); // Delay output
				exit(0);
			}
			else if( childPID == -1 )
			{
				fprintf(stderr, "Error on child exit\n");
				exit(2);
			}
		}
	}
	
	return 0;
}
