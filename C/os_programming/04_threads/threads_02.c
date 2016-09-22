/**
 * Praktikum Betriebssysteme
 *
 * Uebung 04 Threads
 *
 * Author: Christian Schrödel
 * Date: 2013-06-13
 * History: Running
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int val_a, val_b, val_c;
// Return values of scanf to prevent bad program behaviour
int inp_a, inp_b;

void *add(void *none);
void *print(void *none);

int main()
{
	pthread_t thread_add, thread_print;

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

		// Creates thread to add values
		if( pthread_create(&thread_add, NULL, add, NULL) ) exit(1);
		// Waits until adding is finished
		pthread_join(thread_add, NULL);

		// Creates thread to print values and result
		if( pthread_create(&thread_print, NULL, print, NULL) ) exit(2);
		// Waits until printing is finished
		pthread_join(thread_print, NULL);
	}

	return 0;
}

void *add(void *none)
{
	val_c = val_a + val_b;
	// Exits thread
	return NULL;
}

void *print(void *none)
{
	printf("%d + %d = %d\n", val_a, val_b, val_c);
	// Exits thread
	return NULL;
}
