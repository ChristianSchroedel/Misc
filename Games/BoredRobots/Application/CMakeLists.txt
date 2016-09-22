
cmake_minimum_required (VERSION 2.6)

#compile with c++11
add_compile_options(-std=c++0x)

# different compiler options for debug and release
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -Wall -g")
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -Wall -O2")

# create project and executable
project(BoredRobots)
add_executable (BoredRobots ../src/BoredRobots/main.cpp)

include_directories("${CMAKE_SOURCE_DIR}/../extlibs/SFML/include")

# add source of sfml -> compile it by ourself, so it should work for all platforms
add_subdirectory("${CMAKE_SOURCE_DIR}/../extlibs/SFML" "${CMAKE_CURRENT_BINARY_DIR}/sfml_binary")
add_subdirectory("${CMAKE_SOURCE_DIR}/../src/BoredRobots" "${CMAKE_CURRENT_BINARY_DIR}/bored_robots_binary")

# set used libraries and link them
set(SFML_LIBRARIES "sfml-system" "sfml-graphics" "sfml-window")
target_link_libraries(BoredRobots BoredRobotsLib ${SFML_LIBRARIES} )
