/**
 * Praktikum Betriebssysteme
 *
 * Uebung 04 threads
 *
 * Author: Christian Schroedel
 * Date: 2013-06-13
 * History: Running
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

void *showThreadId(void *num);

int main()
{
	pthread_t thread1, thread2, thread3;
	int t_num1 = 1, t_num2 = 2, t_num3 = 3;

	// Creates threads running function showTreadId and pass an int for easy spotting in output
	if( pthread_create(&thread1, NULL, showThreadId, (void *)&t_num1) ) exit(1);
	if( pthread_create(&thread2, NULL, showThreadId, (void *)&t_num2) ) exit(2);
	if( pthread_create(&thread3, NULL, showThreadId, (void *)&t_num3) ) exit(3);

	// Waits for threads to finish
	pthread_join(thread1, NULL);
	pthread_join(thread2, NULL);
	pthread_join(thread3, NULL);

	return 0;
}

void *showThreadId(void *num)
{
	int i;
	int *t_num;
	// Gets id of current thread
	pthread_t myId = pthread_self();

	// Casts received parameter to needed type
	t_num = (int *)num;

	for( i = 0; i < 5; i++ )
	{
		// Prints thread id
		printf("Running thread %d with id: %ld\n-----\n", *t_num, (unsigned long)myId);
		sleep(1);
	}
	
	// Exits thread
	return NULL;
}
