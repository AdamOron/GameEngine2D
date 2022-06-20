package engine.core.scripts;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import engine.core.math.Transform;

public class MovementScript implements Behavior
{
	private Transform transform;
	
	private final float acc = 1f;
	
	private Vector3f vel;
	
	public MovementScript(Transform transform)
	{
		this.transform = transform;
		
		this.vel = new Vector3f(0, 0, 0);
	}
	
	@Override
	public void onCreate()
	{
		
	}

	@Override
	public void onUpdate()
	{
		updateVelocity();
		
		Vector3f.add(vel, transform.pos, transform.pos);
	}

	private void updateVelocity()
	{
		vel.x = 0;
		vel.y = 0;
		
		if(Input.isKeyDown(Keyboard.KEY_D))
		{
			vel.x = acc;
		}
		
		if(Input.isKeyDown(Keyboard.KEY_A))
		{
			vel.x = -acc;
		}
		
		if(Input.isKeyDown(Keyboard.KEY_W))
		{
			vel.y = acc;
		}
		
		if(Input.isKeyDown(Keyboard.KEY_S))
		{
			vel.y = -acc;
		}
		
		Vector3f.add(transform.pos, vel, transform.pos);
	}
	
	@Override
	public void onDestory()
	{
		
	}
}
