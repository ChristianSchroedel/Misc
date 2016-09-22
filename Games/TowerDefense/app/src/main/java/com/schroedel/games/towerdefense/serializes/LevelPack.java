package com.schroedel.games.towerdefense.serializes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Christian Schrödel on 15.05.15.
 *
 * Level pack containing game levels.
 */
@Root
public class LevelPack
{
	@Attribute private String name;
	@ElementList private List<Level> levels;

	public String getName()
	{
		return name;
	}

	public List<Level> getLevels()
	{
		return levels;
	}
}
