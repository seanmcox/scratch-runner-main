package com.shtick.emu;

import javax.swing.JPanel;

/**
 * 
 * @author sean.cox
 *
 */
public interface Emulator {
	/**
	 * 
	 * @return The JPanel component implemented to represent the screen for the emulator.
	 */
	public JPanel getEmulatorComponent();
	
	/**
	 * Starts the project running from its current state.
	 */
	public void start();

	/**
	 * 
	 * @return true if the project is currently running (start has been called, and stop hasn't been called since) and false otherwise.
	 */
	public boolean isRunning();
	
	/**
	 * Exits the application.
	 */
	public void stop();

	/**
	 * 
	 * @return true if stop has been called and false otherwise.
	 */
	public boolean isStopped();
}
