package com.schroedel.games.towerdefense.serializes;

import org.simpleframework.xml.Attribute;

/**
 * Created by Christian Schrödel on 18.10.15.
 *
 * Wave of enemies.
 */
public class EnemyWave
{
	@Attribute private int hitPoints;
	@Attribute private int amount;

	public int getHitPoints()
	{
		return hitPoints;
	}

	public int getAmount()
	{
		return amount;
	}
}
