package com.schroedel.games.towerdefense.controls;

import android.util.Log;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.GameObjects;
import com.schroedel.games.towerdefense.objects.Player;
import com.schroedel.games.towerdefense.objects.entities.Tower;

/**
 * Created by Christian Schrödel on 01.05.15.
 *
 * Top class handling game inputs.
 */
public class GameInput implements GameView.OnGameViewTouchedListener
{
	private GameView gameView;
	private GameObjects gameObjects;

	/**
	 * Creates object handling input by the player.
	 *
	 * @param gameView - game view
	 * @param gameObjects - game objects
	 */
	public GameInput(GameView gameView, GameObjects gameObjects)
	{
		this.gameView = gameView;
		this.gameObjects = gameObjects;

		gameView.setOnGameViewTouchedListener(this);
	}

	@Override
	public void onTowerTouched(Tower tower)
	{
		Log.d("GameInput", "tower touched");
	}

	@Override
	public void onEmptyCoordinateTouched(int x, int y)
	{
		Player player = gameObjects.getPlayer();

		Log.d(
			"GameInput",
			"empty coordinate touched - x:" + x + " y: " + y);

		if (!player.hasEnoughGold(Tower.value))
		{
			Log.d("GameInput", "not enough gold");
			return;
		}

		gameObjects.addTower(
			new Tower(
				x * GameView.xDensityFactor,
				y * GameView.yDensityFactor,
				null,
				null));

		Log.d(
			"GameInput",
			"placed new tower");

		player.removeGold(Tower.value);

		gameView.updatePlayerGold(player.getGold());
	}
}
