/**
 * Praktikum Betriebssysteme
 *
 * Uebung 02 shared memory/pipes
 *
 * Author: Christian Schroedel
 * Date: 2013-05-18
 * History: Added comments, modified prototypes, changed variable names
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <wait.h>
#include <errno.h>

#define VALUE_BLOCK 10 // Length of data block to write to pipe
#define VALUES_TO_WRITE_OVERALL 50 // Delimeter for amount of numbers to write in shared memory

volatile int writeCounter = 0; // Count amount of written data

/**
 * Main routine
 */
int main()
{
	int state = 0, pipefd[2], rt = 0;
	pid_t child = 0;

	char charBuffer[10] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	char outputBuffer[10] = { };

	while( writeCounter < VALUES_TO_WRITE_OVERALL )
	{
		if( pipe(pipefd) == -1 ) // Create new pipe
		{
			fprintf(stderr, "Failed to create pipe\n");
			exit(1);
		}

		child = fork();	// Create new Child

		if( !child )
		{
			close(pipefd[0]); // Child is write only
			printf("==========\nWriting ...\n==========\n");
			rt = write(pipefd[1], &charBuffer, VALUE_BLOCK); // Write to pipe
			sleep(1); // Delay output

			if( rt < 0 )
				printf("errno: %d\n", rt = errno);

			close(pipefd[1]); // Signal writing is finished
			exit(0);
		}
		else if( child == -1 )
		{
			fprintf(stderr, "Error on fork - no child\n");
			exit(2);
		}
		else if( child > 0 )
		{
			close(pipefd[1]); // Parent is read only

			// Wait until child finishes writing
			while( read(pipefd[0], (void*)&outputBuffer, VALUE_BLOCK) > 0 )
			{
				printf("==========\nReading ...\n==========\n");
				rt = write(STDOUT_FILENO, &outputBuffer, VALUE_BLOCK); // Write output direct to stdout
				sleep(1); // Delay output
			}

			if( rt < 0 )
				printf("errno: %d\n", rt = errno);

			rt = write(STDOUT_FILENO, "\n", 1);

			if( rt < 0 )
				printf("errno: %d\n", rt = errno);

			close(pipefd[0]);
			wait(&state);
		}
		writeCounter += 10;
	}
	
	printf("Finished Transmission\n");

	return 0;
}
