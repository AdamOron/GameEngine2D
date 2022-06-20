package engine.graphics.shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends Shader
{
	private static final String VERTEX_FILENAME = "src/engine/graphics/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILENAME = "src/engine/graphics/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	
	public StaticShader()
	{
		super(VERTEX_FILENAME, FRAGMENT_FILENAME);
	}

	@Override
	protected void bindAttribs()
	{
		super.bindAttrib(0, "position");
		super.bindAttrib(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations()
	{
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}
}
