 
#include "../BoredRobots/ExampleRectangle.h"
#include "../BoredRobots/MoveToWayPoint.h"

#include "gtest/gtest.h"

TEST (MoveToWayPointTest, MoveToPointNotStarted)
{
	BoredRobots::ExampleRectangle rectangle(10.0f, 10.0f);

	BoredRobots::MoveToWayPoint moveToWayPoint(&rectangle, sf::Vector2f(20.0f, 20.0f));

	// nothing should happen, because move start were not callen yet
	moveToWayPoint.move(1.0f);

	ASSERT_EQ(rectangle.getVector()->x, 10.0f);
	ASSERT_EQ(rectangle.getVector()->y, 10.0f);
}


TEST (MoveToWayPointTest, MoveToPointStarted)
{
	BoredRobots::ExampleRectangle rectangle(10.0f, 10.0f);

	BoredRobots::MoveToWayPoint moveToWayPoint(&rectangle, sf::Vector2f(20.0f, 20.0f));

	moveToWayPoint.start();
	moveToWayPoint.move(1.0f);

	// movement vector has lenght 1 and x = y
	float movementX = std::sqrt(0.5);
	float movementY = std::sqrt(0.5);

	ASSERT_FLOAT_EQ(rectangle.getVector()->x, 10.0f + movementX);
	ASSERT_FLOAT_EQ(rectangle.getVector()->y, 10.0f + movementY);
}
