package com.schroedel.games.towerdefense;

import com.schroedel.games.towerdefense.controls.GameInput;
import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.listener.CountdownListener;
import com.schroedel.games.towerdefense.listener.EnemyEndReachedListener;
import com.schroedel.games.towerdefense.listener.EnemyKilledListener;
import com.schroedel.games.towerdefense.objects.GameObjects;
import com.schroedel.games.towerdefense.objects.Player;
import com.schroedel.games.towerdefense.serializes.EnemyPack;
import com.schroedel.games.towerdefense.serializes.Level;
import com.schroedel.games.towerdefense.serializes.LevelPack;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

/**
 * Created by Christian Schrödel on 01.05.15.
 *
 * Main game object.
 */
public class Game implements Runnable
{
	private static final int MAX_FPS = 30;

	public static boolean isDebugModeEnabled = true;

	private boolean isRunning;
	private boolean isGameLost;

	private GameView view;
	private GameObjects objects;

	private LevelPack levelPack;
	private EnemyPack enemyPack;

	private OnGameOverListener onGameOverListener;

	public interface OnGameOverListener
	{
		void onGameOver();
	}

	public Game(GameView view)
	{
		this.view = view;
		this.objects = new GameObjects();

		objects.setOnCountdownUpdatedListener(
			new CountdownListener(
				view,
				objects));
		objects.setOnEnemyKilledListener(
			new EnemyKilledListener(
				view,
				objects));
		objects.setOnEnemyReachedEndListener(
			new EnemyEndReachedListener(
				view,
				objects));
		objects.setOnEnemyWaveClearedListener(
			new GameObjects.OnEnemyWaveClearedListener()
			{
				@Override
				public void onEnemyWaveCleared()
				{
					int waveIndex = objects.getWaveIndex();
					int maxWaveIndex = objects.getAmountOfWaves();

					if (waveIndex >= maxWaveIndex)
					{
						isGameLost = false;

						if (onGameOverListener != null)
							onGameOverListener.onGameOver();
					}
				}
			});

		GameInput input = new GameInput(view, objects);

		view.setObjects(objects);
		view.setOnGameViewTouchedListener(input);
	}

	/**
	 * Loads level pack from a given XML file.
	 *
	 * @param file - xml file name
	 */
	public void loadLevelPack(String file)
	{
		try
		{
			InputStream source = view.getContext().getAssets().open(file);
			Serializer serializer = new Persister();
			levelPack = serializer.read(LevelPack.class, source);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Loads enemy pack from a given XML file.
	 *
	 * @param file - xml file name
	 */
	public void loadEnemyPack(String file)
	{
		try
		{
			InputStream source = view.getContext().getAssets().open(file);
			Serializer serializer = new Persister();
			enemyPack = serializer.read(EnemyPack.class, source);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sets listener to Game Over event.
	 *
	 * @param listener - onGameOverListener
	 */
	public void setOnGameOverListener(OnGameOverListener listener)
	{
		this.onGameOverListener = listener;
	}

	/**
	 * Starts game loop.
	 */
	public synchronized void start()
	{
		isRunning = true;
		isGameLost = false;

		Level level = levelPack.getLevels().get(0);
		level.setEnemyFlavour(enemyPack.getEnemyFlavour(level.getEnemyType()));

		objects.init();
		objects.setLevel(level);
		objects.prepareNextEnemyWave();

		final Player player = objects.getPlayer();
		player.setOnLifeLostListener(
			new Player.OnLifeLostListener()
			{
				@Override
				public void onLifeLost()
				{
					if (player.getLives() <= 0)
					{
						isGameLost = true;

						if (onGameOverListener != null)
							onGameOverListener.onGameOver();
					}
				}
			});

		initPlayerInformation();

		Thread gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Initializes player attributes and views displaying gold and lives.
	 */
	private void initPlayerInformation()
	{
		Player player = objects.getPlayer();

		view.updatePlayerLives(player.getLives());
		view.updatePlayerGold(player.getGold());
	}

	/**
	 * Stops game loop.
	 */
	public synchronized void stop()
	{
		isRunning = false;

		objects.reset();
	}

	/**
	 * Checks if game is lost or not.
	 */
	public boolean isGameLost()
	{
		return isGameLost;
	}

	@Override
	public void run()
	{
		float maxTicks = 1000/MAX_FPS;
		float dt;
		long last = System.currentTimeMillis();

		while (isRunning)
		{
			long now = System.currentTimeMillis();

			dt = (float) ((now-last)/1000.0);
			//Log.d("GameLoop", "dt: " + String.format("%.3f", dt));

			objects.update(dt);
			view.render();

			long sleepTime = (long) (maxTicks-dt);

			if (sleepTime > 0)
			{
				try
				{
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

			last = now;
		}
	}
}
