package edu.erau.ateam.prototype.data;

/** A class the contains the credentials to connect to a third party website,
 * grab a schedule with the given credientials, and parse it into a schedule class
 */
public abstract class Scheduler {
	/** Gets a up-to-date parsed schedule */
	public abstract Schedule getSchedule();
}
