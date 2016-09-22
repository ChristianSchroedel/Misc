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
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <wait.h>
#include <errno.h>

#define SHM_LENGTH 11 // Length of shared memory
#define VALUES_TO_WRITE 50 // Delimeter for amount of numbers to write in shared memory

void shm_write(volatile char *address, int count);
void shm_read(volatile char *address, int count);

volatile int writeCounter = 0; // Count amount of written data

/**
 * Main routine
 */
int main()
{
	int state = 0, fd = -1, pipefd[2], rt = 0;
	pid_t child = 0;

	char magic;
	char *name = "shm_file";
	volatile char *sharedMem;

	if( (fd = open(name, O_RDWR | O_CREAT, S_IRUSR | S_IWUSR)) == -1 ) // Create shared memory file descriptor 'shm_file'
	{
		fprintf( stderr, "Failed to create shared memory file descriptor\n");
		exit(1);
	}

	if( ftruncate(fd, SHM_LENGTH) == -1 ) // Truncate created file
	{
		fprintf( stderr, "Could not truncate file\n");
		exit(2);
	}

	sharedMem = mmap(0, SHM_LENGTH, PROT_WRITE | PROT_READ, MAP_SHARED, fd, 0); // Allocate shared memory
	sharedMem[0] = 0; // Enable write access for shared memory

	msync((void*)sharedMem, SHM_LENGTH, MAP_SHARED); // Synch shared memory after modification (first bit was set)
	while( writeCounter < VALUES_TO_WRITE )
	{
		if( pipe(pipefd) == -1 )
		{
			fprintf(stderr, "Failed to create pipe\n");
			exit(3);
		}

		child = fork();	// Create new Child

		if( !child )
		{
			close(pipefd[0]); // Parent is write only
			shm_write(sharedMem, SHM_LENGTH-1); // Parent writes in shared memory
			close(pipefd[1]); // Signal writing is finished			
			exit(0);
		}
		else if( child == -1 )
		{
			fprintf(stderr, "Error on fork - no child\n");
			exit(4);
		}
		else if( child > 0 )
		{
			close(pipefd[1]); // Child is read only
			rt = read(pipefd[0], (void*)&magic, 1); // Wait until parent finishes writing

			if( rt > 0 )
				printf("errno: %d\n", rt = errno);

			shm_read(sharedMem, SHM_LENGTH-1); // Child reads from shared memory
			wait(&state); 
		}

		writeCounter += SHM_LENGTH-1;
	}
	
	printf("Finished Transmission\n");
	munmap((void*)sharedMem, SHM_LENGTH);

	return 0;
}

/**
 * Write to shared memory
 *
 * @param address	pointer to shared memory
 * @param count		amount of data to read
 */
void shm_write(volatile char *address, int count)
{
	printf("==========\nWriting ...\n==========\n");

	// Write n numbers in shared memory
	for( count = 0; count < 10; count++ )
	{
		address[count+1] = writeCounter;
		printf("writing: %d\n", address[count+1]);
		writeCounter++;
	}
	
	msync((void*)address, SHM_LENGTH, MAP_SHARED); // Synch shared memory after modifications
	sleep(1);
}

/**
 * Read from shared memory
 *
 * @param address	pointer to shared memory
 * @param count		amount of data to read
 */
void shm_read(volatile char *address, int count)
{
	printf("==========\nReading ...\n==========\n");

	// Read n numbers from shared memory
	for( count = 0; count < 10; count++ )
		printf("reading: %d\n", address[count+1]); 

	msync((void*)address, SHM_LENGTH, MAP_SHARED); // Synch shared memory after modifications
	sleep(1);
}
