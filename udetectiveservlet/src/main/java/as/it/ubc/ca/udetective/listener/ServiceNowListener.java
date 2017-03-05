package as.it.ubc.ca.udetective.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;

import as.it.ubc.ca.udetective.job.ServiceNowJob;

/**
 * Servlet listener for Quartz
 * 
 * @author Armenak Grigoryan
 */
public class ServiceNowListener implements ServletContextListener {
    
    private static final Logger log = getLogger(ServiceNowListener.class); 
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        log.info("Context Initialized");

        try {
            // Setup the Job class and the Job group
            JobDetail job = newJob(ServiceNowJob.class).withIdentity(
                            "CronQuartzJob", "ServiveNow").build();

            Trigger trigger = newTrigger()
                .withIdentity("Trigger_1", "ServiveNow")
                .withSchedule(simpleSchedule()
                    .withIntervalInMinutes(1)
                    .repeatForever()
                    .withMisfireHandlingInstructionNextWithExistingCount())
                .build();                        


            // Setup the Job and Trigger with Scheduler & schedule jobs
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        log.info("Context Destroyed");
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error(e.toString());
        }
    }
}