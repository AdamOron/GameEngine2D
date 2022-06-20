package engine.core.math;

import org.lwjgl.util.vector.Vector3f;

public class Transform
{
	public Vector3f pos;
	public Vector3f rot;
	public float scale;
	
	public Transform(Vector3f pos, Vector3f rot, float scale)
	{
		this.pos = pos;
		this.rot = rot;
		this.scale = scale;
	}
	
	public Transform()
	{
		this(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0f, 0f), 1f);
	}
	
	public Transform(float scale)
	{
		this(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0f, 0f), scale);
	}
}
