package com.schroedel.games.towerdefense;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.schroedel.games.towerdefense.graphics.GameView;

/**
 * Created by Christian Schrödel on 01.05.15.
 *
 * Game activity.
 */
public class GameActivity extends AppCompatActivity
{
	private Game game;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		GameView gameView = (GameView) findViewById(R.id.sv_gameView);
		gameView.setLivesView((TextView) findViewById(R.id.lives));
		gameView.setGoldView((TextView) findViewById(R.id.gold));
		gameView.setCountdownView(
			(TextView) findViewById(R.id.countdown_next_wave));
		gameView.setOnGameViewReadyListener(
			new GameView.OnGameViewReadyListener()
			{
				@Override
				public void onGameViewReady()
				{
					game.start();
				}
			}
		);

		game = new Game(gameView);
		game.loadLevelPack("levels.xml");
		game.loadEnemyPack("enemies.xml");
		game.setOnGameOverListener(
			new Game.OnGameOverListener()
			{
				@Override
				public void onGameOver()
				{
					if (game.isGameLost())
						showGameOver();
					else
						showGameWon();

					game.stop();
				}
			});
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		setKeepScreenOnEnabled(true);
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		setKeepScreenOnEnabled(false);

		game.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
			R.menu.menu_game,
			menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Sets keep screen on flag state.
	 *
	 * @param enabled - next state
	 */
	private void setKeepScreenOnEnabled(boolean enabled)
	{
		Window window = getWindow();

		if (enabled)
			window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		else
			window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	/**
	 * Shows Game Over message.
	 */
	private void showGameOver()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.title_game_over);
		builder.setMessage(R.string.info_game_over);
		builder.setPositiveButton(
			R.string.btn_retry,
			new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					game.start();
				}
			});
		builder.create().show();
	}

	/**
	 * Shows game won message.
	 */
	private void showGameWon()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.title_game_won);
		builder.setMessage(R.string.info_game_won);
		builder.setPositiveButton(
			R.string.btn_play_again,
			new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialogInterface, int i)
				{
					game.start();
				}
			});
		builder.create().show();
	}
}
