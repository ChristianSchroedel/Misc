package com.schroedel.games.towerdefense.objects;

/**
 * Created by Christian Schrödel on 11.10.15.
 *
 * Player object holding gold, lives etc.
 */
public class Player
{
	private int lives;
	private int gold;

	private OnLifeLostListener lifeLostListener;

	public interface OnLifeLostListener
	{
		void onLifeLost();
	}

	/**
	 * Creates player with set lives and gold.
	 *
	 * @param lives - lives
	 * @param gold - gold
	 */
	public Player(int lives, int gold)
	{
		this.lives = lives;
		this.gold = gold;
	}

	/**
	 * Sets listener listening to events when the player lost a life.
	 *
	 * @param lifeLostListener - listener
	 */
	public void setOnLifeLostListener(OnLifeLostListener lifeLostListener)
	{
		this.lifeLostListener = lifeLostListener;
	}

	/**
	 * Returns remaining lives.
	 *
	 * @return - lives left
	 */
	public int getLives()
	{
		return lives;
	}

	/**
	 * Returns available gold.
	 *
	 * @return - gold
	 */
	public int getGold()
	{
		return gold;
	}

	/**
	 * Removes a life from the player.
	 */
	public synchronized void removeLife()
	{
		--lives;

		if (lifeLostListener != null)
			lifeLostListener.onLifeLost();
	}

	/**
	 * Adds additional gold to the player.
	 *
	 * @param goldToAdd - gold to add
	 */
	public void addGold(int goldToAdd)
	{
		gold += goldToAdd;
	}

	/**
	 * Removes gold from the player.
	 *
	 * @param goldToRemove - gold to remove
	 */
	public void removeGold(int goldToRemove)
	{
		gold -= goldToRemove;
	}

	/**
	 * Checks if player has enough gold for certain actions.
	 *
	 * @param gold - amount of gold needed
	 */
	public boolean hasEnoughGold(int gold)
	{
		return this.gold >= gold;
	}
}
