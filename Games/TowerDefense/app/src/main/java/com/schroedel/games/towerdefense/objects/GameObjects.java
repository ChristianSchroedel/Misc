package com.schroedel.games.towerdefense.objects;

import android.os.Handler;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.entities.Enemy;
import com.schroedel.games.towerdefense.objects.entities.Entity;
import com.schroedel.games.towerdefense.objects.entities.Path;
import com.schroedel.games.towerdefense.objects.entities.Projectile;
import com.schroedel.games.towerdefense.objects.entities.Tower;
import com.schroedel.games.towerdefense.objects.movement.FollowWayPoints;
import com.schroedel.games.towerdefense.objects.movement.HomingTarget;
import com.schroedel.games.towerdefense.serializes.End;
import com.schroedel.games.towerdefense.serializes.EnemyWave;
import com.schroedel.games.towerdefense.serializes.Level;
import com.schroedel.games.towerdefense.serializes.Start;
import com.schroedel.games.towerdefense.serializes.WayPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Schrödel on 01.05.15.
 *
 * Top class managing game objects.
 */
public class GameObjects implements
	FollowWayPoints.OnEndReachedListener,
	HomingTarget.OnTargetReachedListener
{
	private static final int MAX_COUNTDOWN = 10;
	private static final int MAX_ENEMIES = 20;

	private static final int PLAYER_LIVES = 20;
	private static final int PLAYER_START_GOLD = 15;

	private Player player;

	private List<Entity> entities;

	private List<Enemy> enemies;
	private List<Tower> towers;
	private List<Path> paths;

	private Level level;
	private List<EnemyWave> enemyWaves;

	private EnemyWave currentWave;
	private int waveIndex;

	private int enemiesSpawned;
	private int secondsToNextWave;

	private Handler handler;
	private Runnable spanwRunnable = new Runnable()
	{
		@Override
		public void run()
		{
			if (enemiesSpawned >= MAX_ENEMIES)
				return;

			spawnEnemy(level.getStart());
			++enemiesSpawned;

			handler.postDelayed(spanwRunnable, 1200);
		}
	};
	private Runnable countdownRunnable = new Runnable()
	{
		@Override
		public void run()
		{
			if (secondsToNextWave >= 0)
			{
				if (countdownListener != null)
					countdownListener.onCountdownUpdated();

				--secondsToNextWave;

				handler.postDelayed(countdownRunnable, 1000);
			}
			else
				sendNextEnemyWave();
		}
	};

	private OnCountdownUpdatedListener countdownListener;
	private OnEnemyKilledListener enemyKilledListener;
	private OnEnemyReachedEndListener enemyReachedEndListener;
	private OnEnemyWaveClearedListener enemyWaveClearedListener;

	@Override
	public void onEndReached(Entity entity)
	{
		entities.remove(entity);

		if (entity instanceof Enemy)
		{
			Enemy enemy = (Enemy) entity;

			enemies.remove(enemy);
			enemyReachedEnd(enemy);

			if (enemies.isEmpty())
				prepareNextEnemyWave();
		}
	}

	@Override
	public void onTargetReached(Entity entity, Entity target)
	{
		if (target instanceof Enemy &&
			entity instanceof Projectile)
		{
			Projectile projectile = (Projectile) entity;
			Enemy enemy = (Enemy) target;

			enemy.hit(projectile.getDamage());
			projectile.destroy();

			entities.remove(projectile);

			// Make sure only alive enemies are hit (projectiles may home after
			// already killed enemies).
			if (!entities.contains(enemy))
				return;

			if (enemy.getHitpoints() <= 0)
			{
				entities.remove(enemy);
				enemies.remove(enemy);

				enemyKilled(enemy);
			}

			if (enemiesSpawned >= MAX_ENEMIES &&
				enemies.isEmpty())
			{
				enemyWaveCleared();
				prepareNextEnemyWave();
			}
		}
	}

	public interface OnCountdownUpdatedListener
	{
		void onCountdownUpdated();
	}

	public interface OnEnemyKilledListener
	{
		void onEnemyKilled(Enemy enemy);
	}

	public interface OnEnemyReachedEndListener
	{
		void onEnemyReachedEnd(Enemy enemy);
	}

	public interface OnEnemyWaveClearedListener
	{
		void onEnemyWaveCleared();
	}

	/**
	 * Sets listener listening to updates of the countdown until the next wave
	 * of enemies will be spawned.
	 *
	 * @param listener - listener
	 */
	public void setOnCountdownUpdatedListener(
		OnCountdownUpdatedListener listener)
	{
		this.countdownListener = listener;
	}

	/**
	 * Sets listener listening to events when an enemy was killed.
	 *
	 * @param listener - listener
	 */
	public void setOnEnemyKilledListener(OnEnemyKilledListener listener)
	{
		this.enemyKilledListener = listener;
	}

	/**
	 * Sets listener listening to events when an enemy reached the end of the
	 * level.
	 *
	 * @param listener - listener
	 */
	public void setOnEnemyReachedEndListener(OnEnemyReachedEndListener listener)
	{
		this.enemyReachedEndListener = listener;
	}

	/**
	 * Sets listener listening to events when an enemy wave has been cleared by
	 * the player.
	 *
	 * @param listener - listener
	 */
	public void setOnEnemyWaveClearedListener(
		OnEnemyWaveClearedListener listener)
	{
		this.enemyWaveClearedListener = listener;
	}

	/**
	 * Initializes game objects.
	 */
	public void init()
	{
		this.player = new Player(PLAYER_LIVES, PLAYER_START_GOLD);

		this.entities = new ArrayList<>();

		this.enemies = new ArrayList<>();
		this.towers = new ArrayList<>();

		this.handler = new Handler();
	}

	/**
	 * Resets game objects.
	 */
	public void reset()
	{
		handler.removeCallbacks(countdownRunnable);
		handler.removeCallbacks(spanwRunnable);
	}

	/**
	 * Sets current level.
	 *
	 * @param level - current level
	 */
	public void setLevel(Level level)
	{
		this.level = level;
		this.enemyWaves = level.getEnemyFlavour().getWaves();
		this.waveIndex = 0;

		paths = createPathFromLevel(level);

		entities.addAll(paths);
	}

	/**
	 * Creates enemy path entities from level.
	 *
	 * @param level - level
	 * @return - created path
	 */
	private List<Path> createPathFromLevel(Level level)
	{
		Start start = level.getStart();
		List<WayPoint> wayPoints = level.getWayPoints();

		List<Path> paths = new ArrayList<>();

		{
			WayPoint first = wayPoints.get(0);

			int x = start.x < first.x ? start.x : first.x;
			int y = start.y < first.y ? start.y : first.y;

			int dx = start.x - first.x;
			int dy = start.y - first.y;

			if (Math.abs(dx) > Math.abs(dy))
			{
				if (dx > 0)
					++dx;

				for (int i = 0; i < Math.abs(dx); ++i)
				{
					paths.add(
						new Path(
							(x + i) * GameView.xDensityFactor,
							y * GameView.yDensityFactor,
							GameView.xDensityFactor,
							GameView.yDensityFactor,
							null));
				}
			}
			else
			{
				if (dy > 0)
					++dy;

				for (int i = 0; i < Math.abs(dy); ++i)
				{
					paths.add(
						new Path(
							x * GameView.xDensityFactor,
							(y + i) * GameView.yDensityFactor,
							GameView.xDensityFactor,
							GameView.yDensityFactor,
							null));
				}
			}
		}

		for (int i = 0; i < wayPoints.size()-1; ++i)
		{
			WayPoint first = wayPoints.get(i);
			WayPoint second = wayPoints.get(i+1);

			int x = first.x < second.x ? first.x : second.x;
			int y = first.y < second.y ? first.y : second.y;

			int dx = first.x - second.x;
			int dy = first.y - second.y;

			if (Math.abs(dx) > Math.abs(dy))
			{
				if (dx > 0)
					++dx;

				for (int j = 0; j < Math.abs(dx); ++j)
				{
					paths.add(
						new Path(
							(x + j) * GameView.xDensityFactor,
							y * GameView.yDensityFactor,
							GameView.xDensityFactor,
							GameView.yDensityFactor,
							null));
				}
			}
			else
			{
				if (dy > 0)
					++dy;

				for (int j = 0; j < Math.abs(dy); ++j)
				{
					paths.add(
						new Path(
							x * GameView.xDensityFactor,
							(y + j) * GameView.yDensityFactor,
							GameView.xDensityFactor,
							GameView.yDensityFactor,
							null));
				}
			}
		}

		return paths;
	}

	/**
	 * Prepares next wave of enemies.
	 */
	public void prepareNextEnemyWave()
	{
		secondsToNextWave = MAX_COUNTDOWN;

		handler.post(countdownRunnable);
	}

	/**
	 * Sends next wave of enemies.
	 */
	private void sendNextEnemyWave()
	{
		enemiesSpawned = 0;

		if (waveIndex >= enemyWaves.size())
			return;

		currentWave = this.enemyWaves.get(waveIndex++);
		handler.post(spanwRunnable);
	}

	/**
	 * Returns player.
	 *
	 * @return - player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Returns all game entities.
	 *
	 * @return - game entities
	 */
	public List<Entity> getEntities()
	{
		return entities;
	}

	/**
	 * Returns all placed towers.
	 *
	 * @return - tower
	 */
	public List<Tower> getTowers()
	{
		return towers;
	}

	/**
	 * Returns all path entities.
	 *
	 * @return - path entities
	 */
	public List<Path> getPaths()
	{
		return paths;
	}

	/**
	 * Returns enemy way points of current level.
	 *
	 * @return - way points
	 */
	public List<WayPoint> getWayPoints()
	{
		return level.getWayPoints();
	}

	/**
	 * Returns start point of current level.
	 *
	 * @return - start point
	 */
	public Start getStartPoint()
	{
		return level.getStart();
	}

	/**
	 * Returns end point of current level.
	 *
	 * @return - end point
	 */
	public End getEndPoint()
	{
		return level.getEnd();
	}

	/**
	 * Returns seconds until next wave of enemies will be spawned.
	 */
	public int getSecondsToNextWave()
	{
		return secondsToNextWave;
	}

	/**
	 * Returns index of current wave.
	 */
	public int getWaveIndex()
	{
		return waveIndex;
	}

	/**
	 * Returns total amount of enemy waves.
	 */
	public int getAmountOfWaves()
	{
		return enemyWaves.size();
	}

	/**
	 * Updates game objects.
	 *
	 * @param dt - delta time
	 */
	public void update(float dt)
	{
		for (int i = 0; i < entities.size(); ++i)
			entities.get(i).update(dt);

		checkTowerRange();
	}

	/**
	 * Adds a new tower.
	 *
	 * @param tower - tower
	 */
	public synchronized void addTower(Tower tower)
	{
		towers.add(tower);
		entities.add(tower);
	}

	/**
	 * Spawns a new enemy at the start of the level.
	 *
	 * @param start - start of the level
	 */
	private synchronized void spawnEnemy(Start start)
	{
		Enemy enemy = new Enemy(
			start.x*GameView.xDensityFactor,
			start.y*GameView.yDensityFactor,
			currentWave.getHitPoints(),
			null,
			level.getWayPoints(),
			this);

		enemies.add(enemy);
		entities.add(enemy);
	}

	/**
	 * Checks if any tower is in range of an enemy.
	 */
	private void checkTowerRange()
	{
		Enemy closestEnemy;
		int smallestDistance;

		for (Tower tower : towers)
		{
			closestEnemy = null;
			smallestDistance = -1;

			for (int i = 0; i < enemies.size(); ++i)
			{
				Enemy enemy = enemies.get(i);

				int distance = tower.getDistance(enemy);

				if (distance < tower.getRange() &&
					tower.isReadyToFire())
				{
					// Get enemy closest to the tower
					if (smallestDistance == -1 ||
						distance < smallestDistance)
					{
						smallestDistance = distance;
						closestEnemy = enemy;
					}
				}
			}

			if (closestEnemy == null)
				continue;

			Projectile projectile = tower.shoot(closestEnemy, this);

			if (projectile != null)
				entities.add(projectile);
		}
	}

	/**
	 * Handles killed enemy.
	 *
	 * @param enemy - killed enemy
	 */
	private void enemyKilled(final Enemy enemy)
	{
		handler.post(new Runnable()
		{
			@Override
			public void run()
			{
				if (enemyKilledListener != null)
					enemyKilledListener.onEnemyKilled(enemy);
			}
		});
	}

	/**
	 * Handles enemy reaching end of level.
	 *
	 * @param enemy - enemy
	 */
	private void enemyReachedEnd(final Enemy enemy)
	{
		handler.post(new Runnable()
		{
			@Override
			public void run()
			{
				if (enemyReachedEndListener != null)
					enemyReachedEndListener.onEnemyReachedEnd(enemy);
			}
		});
	}

	/**
	 * Handles state when player killed all enemies of a wave.
	 */
	private void enemyWaveCleared()
	{
		handler.post(new Runnable()
		{
			@Override
			public void run()
			{
				if (enemyWaveClearedListener != null)
					enemyWaveClearedListener.onEnemyWaveCleared();
			}
		});
	}
}
