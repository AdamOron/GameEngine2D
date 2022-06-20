package engine.core.entities;

import org.lwjgl.util.vector.Vector3f;
import engine.core.math.Transform;
import engine.core.physics.Collider;
import engine.graphics.models.TexturedModel;
import engine.graphics.renderer.Loader;
import engine.graphics.textures.ModelTexture;

public class Rect extends GameObject
{
	private static final float[] textureCoords = {0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f};
	private static final int[] indices = {0, 1, 3, 3, 1, 2};
	
	public Transform transform;
	public float width, height;
	
	public Rect(float x, float y, float width, float height)
	{
		this.transform = new Transform(new Vector3f(x, y, 0), new Vector3f(0, 0, 0), 1f);
		this.width = width;
		this.height = height;
	}
	
	public Entity makeEntity(ModelTexture texture)
	{
		float[] vertices = {-width, height, 0, -width, -height, 0, width, -height, 0, width, height, 0};
		
		TexturedModel model = new TexturedModel(Loader.getInstance().loadToVAO(vertices, textureCoords, indices), texture);
		
		return new Entity(model, transform);
	}
	
	public Collider makeCollider()
	{
		return new Collider.RectCollider(this);
	}
}
