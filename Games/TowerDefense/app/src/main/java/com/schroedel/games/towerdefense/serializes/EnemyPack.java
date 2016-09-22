package com.schroedel.games.towerdefense.serializes;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Christian Schrödel on 18.10.15.
 *
 * Pack of different enemy flavours.
 */
public class EnemyPack
{
	@ElementList private List<EnemyFlavour> enemyFlavours;

	/**
	 * Returns specific flavour of enemies from enemy pack.
	 *
	 * @param name - name of flavour
	 * @return - enemy flavour
	 */
	public EnemyFlavour getEnemyFlavour(String name)
	{
		for (EnemyFlavour enemyFlavour : enemyFlavours)
		{
			if (enemyFlavour.getName().equals(name))
				return enemyFlavour;
		}

		return null;
	}
}
