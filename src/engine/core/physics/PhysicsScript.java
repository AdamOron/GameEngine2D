package engine.core.physics;

import java.util.ArrayList;
import engine.core.entities.Entity;
import engine.core.scripts.Behavior;

public class PhysicsScript implements Behavior
{
	private Collider collider;
	
	private boolean isCollidable;
	
	public PhysicsScript(Entity entity, Collider collider)
	{
		this.collider = collider;
	}
	
	@Override
	public void onCreate()
	{
		
	}

	@Override
	public void onUpdate()
	{
		checkCollision();
	}

	private void checkCollision()
	{
//		for(PhysicsScript entity : entities)
//		{
//			if(this == entity) continue;
//			
//			if(Collision.check(this.collider, entity.collider))
//			{
//				System.out.println("Collision Detected");
//			}
//		}
	}
	
	@Override
	public void onDestory()
	{
		
	}
}
