#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	if (argc > 1)
	{
		FILE *fp = NULL;

		if (!(fp = fopen(argv[1], "r")))
		{
			fprintf(stderr, "Invalid file\n");
			return 1;
		}

		fseek(fp, 0, SEEK_END);
		long size = ftell(fp);
		rewind(fp);

		char *buffer = malloc(size * sizeof(char));
		size_t result = fread(buffer, 1, size, fp);

		if (result != size)
			return 1;

		printf("### File content ###\n%s\n", buffer);

		fclose(fp);
		free(buffer);
	}
	else
		fprintf(stderr, "Missing file argument\n");

	return 0;
}
