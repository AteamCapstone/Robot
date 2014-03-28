package edu.erau.ateam.robot.scheduler;

import java.util.HashSet;

import edu.erau.ateam.robot.scheduler.TaskStack.TaskNode;

/*
 * could either run synchrously or asynchronously depending on
 * child code
 * Could implement tick system with "pending priority queue 
 */

/** A class that can store and run tasks each tick
 * 
 * Loosely based off of CraftScheduler from Bukkit API
 * https://github.com/Bukkit/CraftBukkit/blob/master/src/main/java/org/bukkit/craftbukkit/scheduler/CraftScheduler.java
 */
public abstract class Scheduler{
	/** stack heads of synchronous and asynchronous tasks */
	private final TaskStack	runOnceTasks = new TaskStack();
	
	private final HashSet<Runnable> repeating = new HashSet<Runnable>();
	
	public void pushAsynchrous(Runnable task){
		synchronized(runOnceTasks){
			runOnceTasks.push(new AsyncWrapper(task));
		}
	}
	
	public void pushSynchronous(Runnable task){
		synchronized(runOnceTasks){
			runOnceTasks.push(task);
		}
	}
	
	public void pushAsynchrousRepeating(Runnable task){
		synchronized(repeating){
			repeating.add(new AsyncWrapper(task));
		}
	}
	
	public void pushSynchronousRepeating(Runnable task){
		synchronized(repeating){
			repeating.add(task);
		}
	}
	
	public boolean removeRepeating(Runnable task){
		boolean result;
		synchronized(repeating){
			result = repeating.remove(task);
		}
		return result;
	}
	
	/** Used to run all the synchronous and asynchronous tasks */
	protected void runTasks(){
		TaskNode node = null;
		
		synchronized(runOnceTasks){
			node = runOnceTasks.clear();
		}
		
		//run all the synchronous tasks
		while(node != null)node = node.runAndGetNext();
		
		synchronized(repeating){
			for(Runnable runnable: repeating)runnable.run();
		}
	}
}

/** A class used to create a separate thread based off runnable class */
final class AsyncWrapper implements Runnable{
	private final Runnable runner;

	AsyncWrapper(Runnable runner) {
		this.runner = runner;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	/* The repeating hashset relies upon the runners hashcode */
	@Override
	public int hashCode(){
		return runner.hashCode();
	}
}

/** Stack used to hold tasks */
class TaskStack{
	/** The top of this stack */
	private TaskNode top = null;

	/** Clears the stack and gets the old top */
	TaskNode clear(){
		TaskNode old = top;
		top = null;
		return old;
	}
	
	void push(Runnable task){
		final TaskNode node = new TaskNode(task);
		
		if(top != null)
			node.below = top;
			
		top = node;
	}
	
	class TaskNode{
		private TaskNode below;
		final Runnable task;
		
		TaskNode(Runnable task) {
			this.task = task;
		}
		
		/** Runs the runnable task in this   */
		TaskNode runAndGetNext(){
			task.run();
			return below;
		}
	}
}