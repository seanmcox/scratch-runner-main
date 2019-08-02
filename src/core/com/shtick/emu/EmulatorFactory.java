/**
 * 
 */
package com.shtick.emu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author scox
 *
 */
public interface EmulatorFactory {
	/**
	 * @param filename The name of a file to check.
	 * @return true if the given filename appears to be a file of the correct type to be run by this ScratchRuntimeFactory and false otherwise.
	 */
	public boolean isValidFilename(String filename);
	
	/**
	 * 
	 * @return An identifier for the emulator implementation created by the instance of EmulatorFactory.
	 */
	public String getEmulatorIdentifier();

	/**
	 * 
	 * @param projectFile
	 * @return An Emulator instance.
	 * @throws IOException 
	 */
	public Emulator createEmulator(File projectFile) throws IOException;

	/**
	 * 
	 * @param projectFile
	 * @param parameters
	 * @return An Emulator instance.
	 * @throws IOException 
	 */
	public Emulator createEmulator(File projectFile, HashMap<String,Object> parameters) throws IOException;
}
