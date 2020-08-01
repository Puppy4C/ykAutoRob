package cn.xgg.robbe.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.xgg.robbe.core.TbRobbe;

public class TbTaskAutoUpdateJob implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TbRobbe robbe =  (TbRobbe) context.getJobDetail().getJobDataMap().get("robbe");
		robbe.updateTaskList();
	}

}
