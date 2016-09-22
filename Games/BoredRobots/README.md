# BoredRobots
Some bored robots wanna play!

## Build

We are using CMake. Simply go to build/ and type

    cmake ..

to generate the project.

You can then use the generated Makefile to build some bored robots.

### Dependencies

Since we also build SFML by ourself, you might need some library dependencies of SFML

#### Ubuntu

    sudo apt-get install libxcb-image0-dev libudev-dev libjpeg-dev libopenal-dev libvorbis-dev libflac-dev libfreetype6-dev
    
### Source Tree

BoredRobots
 |
 |_ Application
 |   |_ CMakeLists.txt (build actual game)
 | 
 |_ Tests
 |   |_ CMakeLists.txt (test project of bored robots using gtest) 
 |
 |_ extlibs
 |   |_ gtest-1.7.0 (test framework used in "Tests" Application)
 |   |
 |   |_ SFML (graphics framework used in this game)
 |
 |_ src 
     |_ BoredRobots (soure of the actual game project)
     |
     |_ BoredRobotsTests (test project of BoredRobots)
     
## Coding Rules
- Write Tests for your classes
- Use useful variable names, variable names consisting of only on or two letters are not useful
     