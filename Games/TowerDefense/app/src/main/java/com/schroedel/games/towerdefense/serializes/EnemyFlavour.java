package com.schroedel.games.towerdefense.serializes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Christian Schrödel on 18.10.15.
 *
 * Collection of enemy waves.
 */
@Root
public class EnemyFlavour
{
	@Attribute private String name;
	@ElementList private List<EnemyWave> waves;

	public String getName()
	{
		return name;
	}

	public List<EnemyWave> getWaves()
	{
		return waves;
	}
}
