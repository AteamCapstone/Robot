package edu.erau.ateam.prototype.scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/** A scheduler that is thread safe with swing classes */
public final class SwingScheduler extends Scheduler{
	public final Timer timer = new Timer(0, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			runTasks();
		}
	});
}
