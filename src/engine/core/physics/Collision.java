package engine.core.physics;

public class Collision
{
	public static boolean check(Collider a, Collider b)
	{
		return a.accept(b);
	}
}
