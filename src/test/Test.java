package test;

import engine.core.Engine;
import engine.core.entities.*;
import engine.core.entities.Rect;
import engine.core.physics.Collider;
import engine.core.scripts.MovementScript;
import engine.graphics.renderer.DisplayManager;

public class Test extends Engine
{
	@Override
	protected void prepare()
	{
		final int GROUND_LAYER = 0;
		
		// Terrain
		
		Rect terrainRect = new Rect(0, -DisplayManager.getHeight(), 1000f, 200f);
		terrainRect.setLayer(GROUND_LAYER);
		
		Entity terrainEntity = terrainRect.makeEntity(Textures.WHITE);
		appendEntity(terrainEntity);
		
		//Collider terrainCollider = terrainRect.makeCollider();
		//usePhysics(terrainEntity, terrainCollider);

		// Player
		
		Rect playerRect = new Rect(0, 0, 100f, 100f);
		
		Entity playerEntity = playerRect.makeEntity(Textures.BLACK);
		appendEntity(playerEntity);
		
		//Collider playerCollider = playerRect.makeCollider();
		//usePhysics(playerEntity, playerCollider);
		
		appendScript(new MovementScript(playerEntity.getTransform()));
	}
	
	public static void main(String[] args)
	{
		new Test().run();
	}

}
