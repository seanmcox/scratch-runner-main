/**
 * 
 */
package com.shtick.app.emu.factory;

import javax.swing.JPanel;

import com.shtick.emu.Emulator;
import com.shtick.utils.scratch3.runner.core.ScratchRuntime;

/**
 * @author scox
 *
 */
public class Scratch3Emulator implements Emulator {
	private ScratchRuntime runtime;

	/**
	 * 
	 * @param runtime
	 */
	public Scratch3Emulator(ScratchRuntime runtime) {
		this.runtime = runtime;
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.Emulator#getEmulatorComponent()
	 */
	@Override
	public JPanel getEmulatorComponent() {
		return runtime.getStagePanel();
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.Emulator#start()
	 */
	@Override
	public void start() {
		runtime.start();
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.Emulator#isRunning()
	 */
	@Override
	public boolean isRunning() {
		return runtime.isRunning();
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.Emulator#stop()
	 */
	@Override
	public void stop() {
		runtime.stop();
	}

	/* (non-Javadoc)
	 * @see com.shtick.emu.Emulator#isStopped()
	 */
	@Override
	public boolean isStopped() {
		return runtime.isStopped();
	}

}
