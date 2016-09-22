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

#define SHM_LENGTH 11 // Length of shared memory
#define VALUES_TO_WRITE 50 // Delimeter for amount of numbers to write in shared memory

void shm_write(volatile char *address, int count);
void shm_read(volatile char *address, int count);

/**
 * Main routine
 */
int main()
{
	int state = 0, fd = -1;
	pid_t child = 0;

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

	child = fork();	// Create new Child

	if( !child )
	{
		// Child writes in shared memory
		while( 1 )
		{
			if( sharedMem[0] == 0 ) // Check for write access
				shm_write(sharedMem, SHM_LENGTH-1);
			else if( sharedMem[0] == -1 ) // Check for EOT
				exit(0);
		}
	}
	else if( child == -1 )
	{
		fprintf(stderr, "Error on fork - no child\n");
		exit(3);
	}
	else if( child > 0 )
	{
		// Parent reads from shared memory
		while( 1 )
		{	
			if( sharedMem[0] == 1 ) // Check for read access
				shm_read(sharedMem, SHM_LENGTH-1);
			else if( sharedMem[0] == -1 ) // Check for EOT
			{
				shm_read(sharedMem, SHM_LENGTH-1); // Do a last read from sharedMem
				wait(&state); // Wait for child process to finish
				printf("Finished Transmission\n");
				break;
			}
		}
	}

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
	static int writeCounter = 1; // Count amount of written data

	printf("Writing ...\n");

	// Write n numbers in shared memory
	for( count = 0; count < 10; count++ )
	{
		address[count+1] = writeCounter;
		printf("writing: %d\n", address[count+1]);
		writeCounter++;
	}
	
	address[0] = 1; // Enable read access for shared memory
	
	if( writeCounter >= VALUES_TO_WRITE )
		address[0] = -1; // Signal EOT

	msync((void*)address, SHM_LENGTH, MAP_SHARED); // Synch shared memory after modifications
}

/**
 * Read from shared memory
 *
 * @param address	pointer to shared memory
 * @param count		amount of data to read
 */
void shm_read(volatile char *address, int count)
{
	printf("Reading ...\n");

	// Read n numbers from shared memory
	for( count = 0; count < 10; count++ )
		printf("reading: %d\n", address[count+1]); 

	address[0] = 0; // Enable write access for shared memory
	
	msync((void*)address, SHM_LENGTH, MAP_SHARED); // Synch shared memory after modifications
}
