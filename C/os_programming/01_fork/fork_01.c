/**
 * Praktikum Betriebssysteme	
 * 
 * Uebung 01 fork/exec
 *
 * Author: Christian Schroedel
 * Date: 2013-04-11
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

/**
 * Main routine
 */
int main ()
{
	pid_t childPID = fork(); // Create new child

	if( !childPID )
	{

		printf("Child: start\n");
#ifdef ORPHAN
		sleep(2);
#endif
		printf("Child: myPID = %d, parentPID = %d\n", getpid(), getppid());
		printf("Child: exiting\n");
		exit(0);
	}
	else if( childPID == -1 )
	{
		fprintf(stderr, "No child created!!!\n");
		exit(1);
	}
	else if( childPID > 0 )
	{

		printf("Parent: start\n");
#ifdef ZOMBIE
		sleep(2);
#endif
		printf("Parent: myPID = %d, childPID = %d\n", getpid(), childPID);
		printf("Parent: exiting\n");
	}


	return 0;
}
