 
 
#include "../BoredRobots/ExampleRectangle.h"

#include "gtest/gtest.h"

TEST (ExampleRectAngleTest, Constructor)
{
	// test only construction of example rectangle
	BoredRobots::ExampleRectangle exampleRectangle(10.0f, 10.0f);
	// if no exception is thrown assert true
	ASSERT_EQ(true, true);
}
