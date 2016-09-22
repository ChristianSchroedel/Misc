package com.schroedel.games.towerdefense.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.schroedel.games.towerdefense.Game;
import com.schroedel.games.towerdefense.R;
import com.schroedel.games.towerdefense.objects.entities.Entity;
import com.schroedel.games.towerdefense.objects.GameObjects;
import com.schroedel.games.towerdefense.objects.entities.Path;
import com.schroedel.games.towerdefense.objects.entities.Tower;
import com.schroedel.games.towerdefense.serializes.End;
import com.schroedel.games.towerdefense.serializes.Start;
import com.schroedel.games.towerdefense.serializes.WayPoint;

import java.util.List;

/**
 * Created by Christian Schrödel on 01.05.15.
 *
 * Top class handling presentation of game.
 */
public class GameView extends SurfaceView
{
	private OnGameViewReadyListener readyListener;
	private OnGameViewTouchedListener gameObjectTouchedListener;

	private TextView tvLives;
	private TextView tvGold;
	private TextView tvCountdown;

	private GameObjects objects;

	public static int xDensityFactor;
	public static int yDensityFactor;

	public interface OnGameViewReadyListener
	{
		void onGameViewReady();
	}

	public interface OnGameViewTouchedListener
	{
		void onTowerTouched(Tower tower);
		void onEmptyCoordinateTouched(int x, int y);
	}

	/**
	 * Creates game view surface.
	 *
	 * @param context - context
	 * @param attr - attribute set used to create view
	 */
	public GameView(Context context, AttributeSet attr)
	{
		super(context, attr);

		getHolder().addCallback(new SurfaceHolder.Callback()
		{
			@Override
			public void surfaceCreated(SurfaceHolder holder)
			{
				xDensityFactor = getWidth()/16;
				yDensityFactor = getHeight()/9;

				if (readyListener != null)
					readyListener.onGameViewReady();
			}

			@Override
			public void surfaceChanged(
				SurfaceHolder holder,
				int format,
				int width,
				int height)
			{

			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder)
			{

			}
		});
	}

	/**
	 * Registers listener receiving callback when game view is ready.
	 *
	 * @param readyListener - listener
	 */
	public void setOnGameViewReadyListener(
		OnGameViewReadyListener readyListener)
	{
		this.readyListener = readyListener;
	}

	/**
	 * Registers listener receiving callback when a game object was touched.
	 *
	 * @param gameObjectTouchedListener - listener
	 */
	public void setOnGameViewTouchedListener(
		OnGameViewTouchedListener gameObjectTouchedListener)
	{
		this.gameObjectTouchedListener = gameObjectTouchedListener;
	}

	/**
	 * Sets game objects to render.
	 *
	 * @param objects - game objects
	 */
	public void setObjects(GameObjects objects)
	{
		this.objects = objects;
	}

	/**
	 * Sets view showing remaining lives of player.
	 *
	 * @param tvLives - view showing lives
	 */
	public void setLivesView(TextView tvLives)
	{
		this.tvLives = tvLives;
	}

	/**
	 * Sets view showing gold of the player.
	 *
	 * @param tvGold - view showing gold
	 */
	public void setGoldView(TextView tvGold)
	{
		this.tvGold = tvGold;
	}

	/**
	 * Sets view showing countdown until next wave of enemies will be sent.
	 *
	 * @param tvCountdown - view showing countdown
	 */
	public void setCountdownView(TextView tvCountdown)
	{
		this.tvCountdown = tvCountdown;
	}

	/**
	 * Returns view showing countdown until next wave of enemies.
	 */
	public TextView getCountdownView()
	{
		return tvCountdown;
	}

	/**
	 * Updates currently displayed amount of lives the player has left.
	 *
	 * @param lives - lives left
	 */
	public void updatePlayerLives(int lives)
	{
		String infoLivesLeft = getResources().getString(R.string.info_lives);

		tvLives.setText(String.format(infoLivesLeft, lives));
	}

	/**
	 * Updates currently displayed amount of gold the player has.
	 *
	 * @param gold - gold
	 */
	public void updatePlayerGold(int gold)
	{
		String infoGold = getResources().getString(R.string.info_gold);

		tvGold.setText(String.format(infoGold, gold));
	}

	@Override
	public boolean onTouchEvent(@NonNull MotionEvent event)
	{
		int action = event.getAction();

		int eventX = (int) event.getX();
		int eventY = (int) event.getY();

		if (action == MotionEvent.ACTION_DOWN)
		{
			for (Tower tower : objects.getTowers())
			{
				if (tower.rect.contains(eventX, eventY) &&
					gameObjectTouchedListener != null)
				{
					gameObjectTouchedListener.onTowerTouched(tower);
					return true;
				}
			}

			for (Path path : objects.getPaths())
			{
				if (path.rect.contains(eventX, eventY))
					return true;
			}

			// If there was no known entity touched we can assume there is
			// nothing.
			if (gameObjectTouchedListener != null)
				gameObjectTouchedListener.onEmptyCoordinateTouched(
					eventX/xDensityFactor,
					eventY/yDensityFactor);
		}

		return super.onTouchEvent(event);
	}

	/**
	 * Renders game objects.
	 */
	public synchronized void render()
	{
		SurfaceHolder surfaceHolder = getHolder();

		Canvas canvas = surfaceHolder.lockCanvas(null);

		render(canvas);

		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	/**
	 * Draws game objects onto canvas.
	 *
	 * @param canvas - canvas
	 */
	private void render(Canvas canvas)
	{
		canvas.drawColor(Color.BLACK);

		List<Entity> entities = objects.getEntities();

		for (int i = 0; i < entities.size(); ++i)
			entities.get(i).render(canvas);

		// Only render certain objects when debug mode is enabled.
		if (Game.isDebugModeEnabled)
		{
			renderStartAndEndPoint(
				canvas,
				objects.getStartPoint(),
				objects.getEndPoint());
			renderWayPoints(canvas, objects.getWayPoints());
			renderLevelGrid(canvas);
		}
	}

	/**
	 * Renders enemy way points onto canvas.
	 *
	 * @param canvas - canvas
	 * @param wayPoints - way points
	 */
	private void renderWayPoints(Canvas canvas, List<WayPoint> wayPoints)
	{
		Paint paint = new Paint();
		paint.setColor(Color.CYAN);

		for (WayPoint wayPoint : wayPoints)
		{
			int x = wayPoint.x*xDensityFactor + xDensityFactor/2;
			int y = wayPoint.y*yDensityFactor + yDensityFactor/2;

			Rect rect = new Rect(x-5, y-5, x+5, y+5);

			canvas.drawRect(rect, paint);
		}
	}

	/**
	 * Renders level grid onto canvas.
	 *
	 * @param canvas - canvas
	 */
	private void renderLevelGrid(Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);

		for (int x = 1; x < 16; ++x)
			canvas.drawLine(
				x*xDensityFactor,
				0,
				x*xDensityFactor,
				9*xDensityFactor,
				paint);

		for (int y = 1; y < 9; ++y)
			canvas.drawLine(
				0,
				y*yDensityFactor,
				16*xDensityFactor,
				y*yDensityFactor,
				paint);
	}

	/**
	 * Renders start and end point of level onto canvas.
	 *
	 * @param canvas - canvas
	 * @param start - start point
	 * @param end - end point
	 */
	private void renderStartAndEndPoint(Canvas canvas, Start start, End end)
	{
		Paint paintStart = new Paint();
		paintStart.setColor(Color.GRAY);

		Paint paintEnd = new Paint();
		paintEnd.setColor(Color.MAGENTA);

		canvas.drawRect(
			new Rect(
				start.x*xDensityFactor,
				start.y*yDensityFactor,
				(start.x+1)*xDensityFactor,
				(start.y+1)*yDensityFactor),
			paintStart);

		canvas.drawRect(
			new Rect(
				end.x*xDensityFactor,
				end.y*yDensityFactor,
				(end.x+1)*xDensityFactor,
				(end.y+1)*yDensityFactor),
			paintEnd);
	}
}
