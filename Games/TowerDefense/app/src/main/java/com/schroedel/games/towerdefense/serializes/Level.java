package com.schroedel.games.towerdefense.serializes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Christian Schrödel on 15.05.15.
 *
 * Game level.
 */
public class Level
{
	@Attribute private String name;
	@Attribute private String enemyType;
	@Element private Start start;
	@Element private End end;
	@ElementList private List<WayPoint> wayPoints;

	private EnemyFlavour enemyFlavour;

	public String getName()
	{
		return name;
	}

	public Start getStart()
	{
		return start;
	}

	public End getEnd()
	{
		return end;
	}

	public List<WayPoint> getWayPoints()
	{
		return wayPoints;
	}

	public String getEnemyType()
	{
		return enemyType;
	}

	public EnemyFlavour getEnemyFlavour()
	{
		return enemyFlavour;
	}

	public void setEnemyFlavour(EnemyFlavour enemyFlavour)
	{
		this.enemyFlavour = enemyFlavour;
	}
}
