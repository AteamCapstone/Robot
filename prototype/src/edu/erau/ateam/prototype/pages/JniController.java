package edu.erau.ateam.prototype.pages;

import java.io.File;

/** A class used to interface with C/C++ */
final class JniController {
	static final JniController instance = new JniController();
	
	private JniController(){}
		
	
	static{
		try {
			System.load(new File("PrototypeNative.dll").getAbsolutePath());
		} catch (Error e2) {
			System.out.println("PrototypeNative.dll not found");
			e2.printStackTrace();
		}
	}
	
	/** adds two numbers in C++ using Jni 
	 * @author KestlerC
	 */
	native int demoJni(int x, int y);
}
