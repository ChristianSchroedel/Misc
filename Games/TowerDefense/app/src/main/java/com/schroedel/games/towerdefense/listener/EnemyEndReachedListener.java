package com.schroedel.games.towerdefense.listener;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.entities.Enemy;
import com.schroedel.games.towerdefense.objects.GameObjects;
import com.schroedel.games.towerdefense.objects.Player;

/**
 * Created by Christian Schrödel on 11.10.15.
 *
 * Listens to events when an enemy reached the end of the level.
 */
public class EnemyEndReachedListener implements
	GameObjects.OnEnemyReachedEndListener
{
	private GameView gameView;
	private GameObjects objects;

	/**
	 * Creates new listener listening to leaked enemies to update game view and
	 * lives of player.
	 *
	 * @param gameView - game view
	 * @param objects - game objects
	 */
	public EnemyEndReachedListener(GameView gameView, GameObjects objects)
	{
		this.gameView = gameView;
		this.objects = objects;
	}

	@Override
	public void onEnemyReachedEnd(Enemy enemy)
	{
		Player player = objects.getPlayer();
		player.removeLife();

		gameView.updatePlayerLives(player.getLives());
	}
}
