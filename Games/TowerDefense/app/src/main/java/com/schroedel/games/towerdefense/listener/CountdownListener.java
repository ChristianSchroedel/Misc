package com.schroedel.games.towerdefense.listener;

import android.view.View;
import android.widget.TextView;

import com.schroedel.games.towerdefense.R;
import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.GameObjects;

/**
 * Created by Christian Schrödel on 11.10.15.
 *
 * Listener listening to updates of countdown until next wave of enemies will
 * be spawned.
 */
public class CountdownListener implements GameObjects.OnCountdownUpdatedListener
{
	private GameView gameView;
	private GameObjects gameObjects;

	private String infoNextWave;

	/**
	 * Creates new listener to update countdown until next wave.
	 *
	 * @param gameView - game view
	 * @param gameObjects - game objects
	 */
	public CountdownListener(GameView gameView, GameObjects gameObjects)
	{
		this.gameView = gameView;
		this.gameObjects = gameObjects;

		infoNextWave =
			gameView.getResources().getString(R.string.info_next_wave);
	}

	@Override
	public void onCountdownUpdated()
	{
		int waveIndex = gameObjects.getWaveIndex()+1;
		int countdownValue = gameObjects.getSecondsToNextWave();

		TextView tvCountdown = gameView.getCountdownView();
		tvCountdown.setText(
			String.format(infoNextWave, waveIndex, countdownValue));

		if (countdownValue > 0 &&
			tvCountdown.getVisibility() == View.GONE)
			tvCountdown.setVisibility(View.VISIBLE);

		if (countdownValue == 0)
			tvCountdown.setVisibility(View.GONE);
	}
}
