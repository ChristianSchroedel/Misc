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

// Values to build sum
int val_a, val_b, val_c;
// Status of input loop
int finished = 0;
// Create mutex and condition
pthread_mutex_t mutex;
pthread_cond_t conditionInput, conditionAdd, conditionPrint;

void *add(void *none);
void *print(void *none);

int main()
{
	// Return values of scanf to prevent bad program behaviour
	int inp_a, inp_b;
	// Pthread names
	pthread_t thread_add, thread_print;

	// Creates thread to add values
	if( pthread_create(&thread_add, NULL, add, NULL) ) exit(1);
	// Creates thread to print values and result
	if( pthread_create(&thread_print, NULL, print, NULL) ) exit(2);

	// Initializes mutex
	if( pthread_mutex_init(&mutex, NULL) ) exit(3);
	
	// Initializes condition
	if( pthread_cond_init(&conditionInput, NULL) ) exit(4);
	// Initializes condition
	if( pthread_cond_init(&conditionAdd, NULL) ) exit(5);
	// Initializes condition
	if( pthread_cond_init(&conditionPrint, NULL) ) exit(6);

	// Input loop
	while( 1 )
	{
		// Gets two int from user input
		printf("\n---- Enter two values (exit with entering val_a: 0, val_b: 0) ----\n\n");
		printf("val_a: "); inp_a = scanf("%d", &val_a);
		printf("val_b: "); inp_b = scanf("%d", &val_b);

		// Leaves loop if nothing to add
		if( (val_a == 0 && val_b == 0) )
		{
			printf("Bye bye\n");
			break;
		}
		// Leaves loop if bad input
		else if( inp_a == 0 || inp_b == 0 )
		{
			printf("Bad input format, bye\n");
			break;
		}

		// Signals that input handling is finished
		pthread_cond_signal(&conditionInput);

		// Waits for signal of print thread
		pthread_cond_wait(&conditionPrint, &mutex);
	}

	// Terminates threads
	pthread_cancel(thread_add);
	pthread_cancel(thread_print);

	// Destroys conditions
	pthread_cond_destroy(&conditionInput);
	pthread_cond_destroy(&conditionAdd);
	pthread_cond_destroy(&conditionPrint);

	return 0;
}

void *add(void *none)
{
	// Adds values while input loop is running
	while( 1 )
	{	
		// Locks mutex and waits for signal of conditionInput
		pthread_mutex_lock(&mutex);
		pthread_cond_wait(&conditionInput, &mutex);

		val_c = val_a + val_b;

		// Sends signal that add is finished and unlocks mutex
		pthread_cond_signal(&conditionAdd);
		pthread_mutex_unlock(&mutex);
	}

	// Exits thread
	return NULL;
}

void *print(void *none)
{
	// Prints values and result while input loop is running
	while( 1 )
	{
		// Locks mutex and waits for signal of conditionAdd
		pthread_mutex_lock(&mutex);
		pthread_cond_wait(&conditionAdd, &mutex);
		
		printf("\n%d + %d = %d\n", val_a, val_b, val_c);

		// Sends signal that print is finished and unlocks mutex
		pthread_cond_signal(&conditionPrint);
		pthread_mutex_unlock(&mutex);
	}
	
	// Exits thread
	return NULL;
}
