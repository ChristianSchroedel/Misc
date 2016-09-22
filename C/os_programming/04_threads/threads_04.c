/**
 * Praktikum Betriebssysteme
 *
 * Uebung 04 Threads
 *
 * Author: Christian Schrödel
 * Date: 2013-06-19
 * History: Running
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX_COUNT 100

// Values to build sum
int sharedArray[10];

void *write(void *none);
void *read(void *none);

int main()
{
	// Loop counter
	int cnt = 0;
	// Pthread names
	pthread_t thread_write, thread_read;
	// Initializes random function
	srand(time(NULL));

	// Main loop
	while( cnt < MAX_COUNT )
	{
		// Creates thread to add values
		if( pthread_create(&thread_write, NULL, write, NULL) ) exit(1);
		// Waits for write thread to finish
		pthread_join(thread_write, NULL);

		// Creates thread to print values and result
		if( pthread_create(&thread_read, NULL, read, NULL) ) exit(2);
		// Waits for read thread to finish
		pthread_join(thread_read, NULL);

		cnt += 10;
	}

	return 0;
}

void *write(void *none)
{
	int i;

	printf("\nWriting ... \n");

	// Writes 10 numbers in shared array
	for( i = 0; i < 10; i++ )
	{
		// Gets new random value
		int randValue = rand() % 20;

		printf("Insert new value: %d\n", randValue);

		sharedArray[i] = randValue;
	}

	// Exits thread
	return NULL;
}

void *read(void *none)
{
	int i;

	printf("\nReading ... \n");

	// Reads 10 numbers from shared array
	for( i = 0; i < 10; i++ )
	{
		printf("sharedArray[%d] = %d\n", i, sharedArray[i]);
	}
	
	// Exits thread
	return NULL;
}
