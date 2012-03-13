package org.bardes.state;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.bardes.entities.Slide;

public class ProjectorState 
{
	Lock lock = new ReentrantLock();
	Condition changed = lock.newCondition(); 
	
	private Slide currentSlide;

	public Slide getCurrentSlide() 
	{
		return currentSlide;
	}

	public void setCurrentSlide(Slide currentSlide) 
	{
		lock.lock();
		try
		{
			if (!this.currentSlide.equals(currentSlide))
			{
				changed.signal();
			}
			this.currentSlide = currentSlide;
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public Slide waitForSlideChange(long wait) throws InterruptedException 
	{
		lock.lock();
		try
		{
			changed.await(wait, TimeUnit.MILLISECONDS);
		}
		finally
		{
			lock.unlock();
		}
		return currentSlide;
	}
}
