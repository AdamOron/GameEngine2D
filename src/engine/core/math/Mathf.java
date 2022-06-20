package engine.core.math;

import engine.graphics.renderer.DisplayManager;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Mathf
{
	private static final float UNIT = 1f; // 1 UNIT = 1PX
	
	public static Matrix4f createTransformationMatrix(Transform transform)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		Vector3f scale = new Vector3f(UNIT / DisplayManager.getWidth(), UNIT / DisplayManager.getHeight(), 1f);
		
		Matrix4f.translate(mult(scale, transform.pos), matrix, matrix);
		
		Matrix4f.rotate(toRadians(transform.rot.x), new Vector3f(1f, 0f, 0f), matrix, matrix);
		Matrix4f.rotate(toRadians(transform.rot.y), new Vector3f(0f, 1f, 0f), matrix, matrix);
		Matrix4f.rotate(toRadians(transform.rot.z), new Vector3f(0f, 0f, 1f), matrix, matrix);
		
		Matrix4f.scale(mult(scale, transform.scale), matrix, matrix);
		
		return matrix;
	}
	
	public static float toRadians(float angle)
	{
		return (float) Math.toRadians(angle);
	}
	
	public static Vector3f mult(Vector3f a, float b)
	{
		return new Vector3f(a.x * b, a.y * b, a.z * b);
	}
	
	public static Vector3f mult(Vector3f a, Vector3f b)
	{
		return new Vector3f(a.x * b.x, a.y * b.y, a.z * b.z);
	}
}
