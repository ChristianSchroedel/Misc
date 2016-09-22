package com.schroedel.games.towerdefense.serializes;

import org.simpleframework.xml.Attribute;

/**
 * Created by Christian Schrödel on 15.05.15.
 *
 * Way point for an enemy between start and end of a level.
 */
public class WayPoint
{
	@Attribute public int x;
	@Attribute public int y;
}
