package ca.ubc.it.as.udetective.job;

import ca.ubc.it.as.udetective.dataretriever.IRetriever;
import ca.ubc.it.as.udetective.dataretriever.Pipeline;
import ca.ubc.it.as.udetective.dataretriever.RetrieverException;
import ca.ubc.it.as.udetective.dataretriever.ServiceNowRetriever;
import ca.ubc.it.as.udetective.inputds.IDataSource;
import ca.ubc.it.as.udetective.inputds.ServiceNowDataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quartz job to retrieve data from ServiceNow
 * 
 * @author Armenak Grigoryan
 */
public class ServiceNowJob implements Job {
    
    private static final Logger log = getLogger(ServiceNowJob.class); 
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("Quartz: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        Pipeline pipeline = new Pipeline();
        try {
            pipeline.execute();
        } catch (RetrieverException re) {
            throw new JobExecutionException(re.toString());
        }
    }
}