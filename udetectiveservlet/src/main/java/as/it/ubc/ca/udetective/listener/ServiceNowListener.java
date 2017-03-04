package as.it.ubc.ca.udetective.listener;

import as.it.ubc.ca.udetective.job.ServiceNowJob;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Servlet listener for Quartz
 * 
 * @author Armenak Grigoryan
 */
public class ServiceNowListener implements ServletContextListener {
        private Scheduler scheduler = null;

        @Override
        public void contextInitialized(ServletContextEvent servletContext) {
                System.out.println("Context Initialized");
                
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
                        
//                        // Create a Trigger that fires every 5 minutes.
//                        Trigger trigger = newTrigger()
//                        .withIdentity("TriggerName", "Group")
//                        //.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
//                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * *"))
//                        .build();

                        // Setup the Job and Trigger with Scheduler & schedule jobs
                        scheduler = new StdSchedulerFactory().getScheduler();
                        scheduler.start();
                        scheduler.scheduleJob(job, trigger);
                }
                catch (SchedulerException e) {
                        e.printStackTrace();
                }
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContext) {
                System.out.println("Context Destroyed");
                try 
                {
                        scheduler.shutdown();
                } 
                catch (SchedulerException e) 
                {
                        e.printStackTrace();
                }
        }    
}