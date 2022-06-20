package engine.core.scripts;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input
{
	public static boolean isButtonDown(int button)
	{
		return Mouse.isButtonDown(button);
	}
	
	public static boolean isKeyDown(int key)
	{
		return Keyboard.isKeyDown(key);
	}
}
