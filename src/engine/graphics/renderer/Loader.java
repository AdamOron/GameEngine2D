package engine.graphics.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import engine.graphics.models.RawModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Loader
{
	private static Loader instance = null;
	
	private ArrayList<Integer> vaos, vbos, textures;

	public static Loader getInstance()
	{
		if(instance == null)
		{
			instance = new Loader();
		}
		
		return instance;
	}
	
	private Loader()
	{
		vaos = new ArrayList<Integer>();
		vbos = new ArrayList<Integer>();
		textures = new ArrayList<Integer>();
	}
	
	public RawModel loadToVAO(float[] vertices, float[] textureCoords, int[] indices)
	{
		int vaoId = createVAO();
		
		bindIndicesBuffer(indices);
		storeDataInAttribList(0, 3, vertices);
		storeDataInAttribList(1, 2, textureCoords);
		
		unbindVAO();
		
		return new RawModel(vaoId, vertices.length);
	}
	
	public int loadTexture(String filename)
	{
		Texture texture = null;
		
		try
		{
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/" + filename + ".png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		int textureId = texture.getTextureID();
		
		textures.add(textureId);
		
		return textureId;
	}
	
	public void clear()
	{
		for(int vao : vaos)
		{
			GL30.glDeleteVertexArrays(vao);
		}
		
		for(int vbo : vbos)
		{
			GL15.glDeleteBuffers(vbo);
		}
		
		for(int texture : textures)
		{
			GL11.glDeleteTextures(texture);
		}
	}
	
	private int createVAO()
	{
		int vaoId = GL30.glGenVertexArrays();
		
		GL30.glBindVertexArray(vaoId);
		
		vaos.add(vaoId);
		
		return vaoId;
	}
	
	private void storeDataInAttribList(int attribNumber, int coordsSize, float[] data)
	{
		int vboId = GL15.glGenBuffers();
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		GL20.glVertexAttribPointer(attribNumber, coordsSize, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		vbos.add(vboId);
	}
	
	private void unbindVAO()
	{
		GL30.glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices)
	{
		int vboId = GL15.glGenBuffers();
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
		
		IntBuffer buffer = storeDataInIntBuffer(indices);
		
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		vbos.add(vboId);
	}

	private IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
}
