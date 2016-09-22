/**
 * Praktikum Betriebssysteme
 *
 * Uebung 01 fork/exec
 *
 * Author: Christian Schroedel
 * Date: 2013-05-18
 * History: Added error handling and comments
 */

#include <stdio.h>
#include <sys/types.h>
#include <wait.h>
#include <unistd.h>

/**
 * Main routine
 */
int main()
{
	pid_t myPID = getpid(); // Get origin PID

	pid_t childPID = 0;
	pid_t childPIDs[10] = { }; // Store PIDs of children to wait for exit later
	int state = 0;
	int i = 0;

	// Create 10 childs of the same process
	while( myPID == getpid() && i < 10 )
	{
		childPID = fork(); // Create new child

		if( !childPID )
			printf("Child: started. PID: %d\n", getpid());
		else if( childPID == -1 )
		{
			fprintf(stderr, "Error on fork - no child");
			exit(1);
		}
		else if( childPID > 0 )
			childPIDs[i] = childPID;
		
		i++;
	}

	if( !childPID )
	{
		printf("Child: exiting. PID: %d\n", getpid());
		sleep(1); // Delay output
		exit(0);
	}
	else
	{
		// Wait for exit of children
		for( i = 0; i < 10; i++ )
		{	
			childPID = waitpid(childPIDs[i], &state, 0);

			if( childPID > 0 )
			{
				printf("Child %d exited successful\n", childPIDs[i]);
				sleep(1); // Delay output
			}
			else if( childPID == -1 )
			{
				fprintf(stderr, "Child %d did not exit properly\n", childPIDs[i]);
				exit(2);
			}
		}
	}

	return 0;
}
