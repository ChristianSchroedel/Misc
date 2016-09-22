/**
 * Praktikum Betriebssysteme
 *
 * Uebung 04 Threads
 *
 * Author: Christian Schrödel
 * Date: 2013-06-19
 * History: First implementation (no mutexes used yet)
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX_COUNT 100
#define STEP_NUM 10

// Values to build sum
int sharedArray[10];
// Loop count
int cnt = 10;

// Creates mutexes and condition
pthread_mutex_t mutexWrite, mutexRead;
pthread_cond_t condition;

void *write(void *none);
void *read(void *none);

int main()
{
	// Pthread names
	pthread_t thread_write, thread_read;

	// Initializes random function
	srand(time(NULL));

	pthread_cond_init(&condition, NULL);

	// Creates threads
	if( pthread_create(&thread_write, NULL, write, NULL) ) exit(1);
	if( pthread_create(&thread_read, NULL, read, NULL) ) exit(2);

	// Waits for threads to finish
	pthread_join(thread_write, NULL);
	pthread_join(thread_read, NULL);

	return 0;
}

void *write(void *none)
{
	int i;

	while( cnt < MAX_COUNT )
	{
		pthread_mutex_lock(&mutexWrite);

		printf("\nWriting ... \n");

		// Writes 10 numbers in shared array
		for( i = 0; i < STEP_NUM; i++ )
		{
			// Gets new random value
			int randValue = rand() % 20;

			printf("Insert new value: %d\n", randValue);

			sharedArray[i] = randValue;
		}

		pthread_cond_signal(&condition);
		pthread_mutex_unlock(&mutexWrite);
	}

	// Exits thread
	return NULL;
}

void *read(void *none)
{
	int i;

	while( cnt < MAX_COUNT )
	{
		pthread_mutex_lock(&mutexWrite);
		pthread_cond_wait(&condition, &mutexWrite);

		printf("\nReading ... \n");

		// Reads 10 numbers from shared array
		for( i = 0; i < STEP_NUM; i++ )
		{
			printf("sharedArray[%d] = %d\n", i, sharedArray[i]);
		}

		cnt += STEP_NUM;

		pthread_mutex_unlock(&mutexWrite);
	}

	// Exits thread
	return NULL;
}
