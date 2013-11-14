package edu.erau.ateam.prototype.pages;

/** A class used to interface with C/C++ */
final class JniController {
	static final JniController instance = new JniController();
	
	private JniController(){}
	
	static{
		try {
			System.loadLibrary("PrototypeNative");
		} catch (Error e) {
			System.out.println("PrototypeNative.dll not found");
		}
	}
	
	/** adds two numbers in C++ using Jni 
	 * @author KestlerC
	 */
	native int demoJni(int x, int y);
}
