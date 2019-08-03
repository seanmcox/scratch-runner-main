/**
 * 
 */
package com.shtick.app.emu.factory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.shtick.emu.Emulator;
import com.shtick.emu.EmulatorFactory;
import com.shtick.utils.scratch3.runner.core.ScratchRuntimeFactory;

/**
 * @author scox
 *
 */
public class Scratch3EmulatorFactory implements EmulatorFactory{
	private ScratchRuntimeFactory factory;

	/**
	 * 
	 * @param factory
	 */
	public Scratch3EmulatorFactory(ScratchRuntimeFactory factory) {
		this.factory = factory;
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.EmulatorFactory#isValidFilename(java.lang.String)
	 */
	@Override
	public boolean isValidFilename(String filename) {
		return factory.isValidFilename(filename);
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.EmulatorFactory#getEmulatorIdentifier()
	 */
	@Override
	public String getEmulatorIdentifier() {
		return "Scratch 3 reference implementation.";
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.EmulatorFactory#createEmulator(java.io.File)
	 */
	@Override
	public Emulator createEmulator(File projectFile) throws IOException {
		return new Scratch3Emulator(factory.createScratchRuntime(projectFile));
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.EmulatorFactory#createEmulator(java.io.File, java.util.HashMap)
	 */
	@Override
	public Emulator createEmulator(File projectFile, HashMap<String, Object> parameters) throws IOException {
		int stageWidth = 480;
		int stageHeight = 360;
		if(parameters.containsKey("stageWidth")) {
			Object value = parameters.get("stageWidth");
			int newValue = 0;
			if(value instanceof Number) {
				newValue = ((Number)value).intValue();
			}
			else if(value instanceof String) {
				try {
					newValue = Integer.parseInt((String)value);
				}
				catch(NumberFormatException t) {}
			}
			if(newValue>0) {
				stageWidth = newValue;
			}
		}
		if(parameters.containsKey("stageHeight")) {
			Object value = parameters.get("stageHeight");
			int newValue = 0;
			if(value instanceof Number) {
				newValue = ((Number)value).intValue();
			}
			else if(value instanceof String) {
				try {
					newValue = Integer.parseInt((String)value);
				}
				catch(NumberFormatException t) {}
			}
			if(newValue>0) {
				stageHeight = newValue;
			}
		}
		return new Scratch3Emulator(factory.createScratchRuntime(projectFile,stageWidth,stageHeight));
	}

}
