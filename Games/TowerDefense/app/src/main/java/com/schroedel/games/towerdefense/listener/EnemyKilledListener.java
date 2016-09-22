package com.schroedel.games.towerdefense.listener;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.entities.Enemy;
import com.schroedel.games.towerdefense.objects.GameObjects;
import com.schroedel.games.towerdefense.objects.Player;

/**
 * Created by Christian Schrödel on 11.10.15.
 *
 * Listens to events when an enemy was killed.
 */
public class EnemyKilledListener implements GameObjects.OnEnemyKilledListener
{
	private GameView gameView;
	private GameObjects objects;

	/**
	 * Listener listening to killed enemies to update game view and gold of
	 * player.
	 *
	 * @param gameView - game view
	 * @param objects - game objects
	 */
	public EnemyKilledListener(GameView gameView, GameObjects objects)
	{
		this.gameView = gameView;
		this.objects = objects;
	}

	@Override
	public void onEnemyKilled(Enemy enemy)
	{
		Player player = objects.getPlayer();
		player.addGold(Enemy.value);

		gameView.updatePlayerGold(player.getGold());
	}
}
