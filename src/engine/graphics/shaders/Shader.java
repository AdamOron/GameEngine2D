package engine.graphics.shaders;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public abstract class Shader
{
	private int shaderId;
	private int vertexShaderId;
	private int fragmentShaderId;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public Shader(String vertexFile, String fragmentFile)
	{
		this.vertexShaderId = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		this.shaderId = GL20.glCreateProgram();
		
		GL20.glAttachShader(shaderId, vertexShaderId);
		GL20.glAttachShader(shaderId, fragmentShaderId);
		
		bindAttribs();
		
		GL20.glLinkProgram(shaderId);
		GL20.glValidateProgram(shaderId);
		
		getAllUniformLocations();
	}
	
	public void start()
	{
		GL20.glUseProgram(shaderId);
	}

	public void stop()
	{
		GL20.glUseProgram(0);
	}
	
	public void clear()
	{
		stop();
		
		GL20.glDetachShader(shaderId, vertexShaderId);
		GL20.glDetachShader(shaderId, fragmentShaderId);
		
		GL20.glDeleteShader(vertexShaderId);
		GL20.glDeleteShader(fragmentShaderId);
		
		GL20.glDeleteProgram(shaderId);
	}
	
	protected void bindAttrib(int attrib, String varName)
	{
		GL20.glBindAttribLocation(shaderId, attrib, varName);
	}
	
	protected abstract void bindAttribs();
	
	protected int getUniformLocation(String uniformName)
	{
		return GL20.glGetUniformLocation(shaderId, uniformName);
	}
	
	protected abstract void getAllUniformLocations();
	
	protected void loadMatrix(int location, Matrix4f matrix)
	{
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
	protected void loadVector(int location, Vector3f value)
	{
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	
	protected void loadFloat(int location, float value)
	{
		GL20.glUniform1f(location, value);
	}
	
	protected void loadInt(int location, int value)
	{
		GL20.glUniform1i(location, value);
	}
	
	protected void loadBoolean(int location, boolean value)
	{
		GL20.glUniform1f(location, value ? 1f : 0f);
	}
	
	private static int loadShader(String filename, int type)
	{
		StringBuilder shaderSource = new StringBuilder();
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			
			while((line = reader.readLine()) != null)
			{
				shaderSource.append(line).append("//\n");
			}
			
			reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
		{
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		
		return shaderID;
	}
}
