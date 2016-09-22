
cmake_minimum_required (VERSION 2.6)

#compile with c++11
add_compile_options(-std=c++0x)

# different compiler options for debug and release
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -Wall -g")
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -Wall -O2")

# create project and executable
project(BoredRobotsTests)

# add all test files to executable, required for gtest to find them
AUX_SOURCE_DIRECTORY("${CMAKE_SOURCE_DIR}/../src/BoredRobotsTests" TestFiles)
add_executable (BoredRobotsTests ${TestFiles})

include_directories("${CMAKE_SOURCE_DIR}/../extlibs/SFML/include")
include_directories("${CMAKE_SOURCE_DIR}/../src/BoredRobots")
include_directories("${CMAKE_SOURCE_DIR}/../src/BoredRobotsTests")
include_directories(${gtest_SOURCE_DIR}/include ${gtest_SOURCE_DIR})

# add source of sfml -> compile it by ourself, so it should work for all platforms
add_subdirectory("${CMAKE_SOURCE_DIR}/../extlibs/gtest-1.7.0" "gtest_binary")
add_subdirectory("${CMAKE_SOURCE_DIR}/../extlibs/SFML" "sfml_binary")
add_subdirectory("${CMAKE_SOURCE_DIR}/../src/BoredRobots" "bored_robots_binary")
add_subdirectory("${CMAKE_SOURCE_DIR}/../src/BoredRobotsTests" "bored_robots_test_binary")

# set used libraries and link them
set(SFML_LIBRARIES "sfml-system" "sfml-graphics" "sfml-window")

# Link test executable against gtest & gtest_main
target_link_libraries(BoredRobotsTests gtest_main gtest BoredRobotsTestsLib
BoredRobotsLib ${SFML_LIBRARIES})
