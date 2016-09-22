CC=gcc
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=file_reader.c
OBJECTS=$(SOURCES:.c=.o)
EXECUTABLE=reader

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $^ -o $@

.c.o:
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -rf *.o
	rm $(EXECUTABLE)
