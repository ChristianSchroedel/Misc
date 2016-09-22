#ifndef __GAME_LOGIC_H_2015_11_08__
#define __GAME_LOGIC_H_2015_11_08__

#include <list>

#include "GameObject.h"
namespace BoredRobots
{
	/**
	 * @brief The GameLogic class
	 *
	 * according to the model view controller design pattern, this class
	 * implements the model or logic part... some further meaningful comments
	 */
	class GameLogic
	{
	private:
		/// all know objects
		std::list<GameObject*> gameObjects;

	public:
		GameLogic();
		~GameLogic();
		void update(float dt);
		const std::list<GameObject*>* getGameObjects()const {return &gameObjects;}
		void addGameObject(GameObject* gameObject);
	};
}

#endif // __GAME_LOGIC_H_2015_11_08__
