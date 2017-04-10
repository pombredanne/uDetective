package ca.ubc.it.as.udetective.dao;

import javax.sql.DataSource;

/**
 * @author UBC IT
 */
public abstract class AbstractDAO {
    
    /**
     * Data Source
     */
    private DataSource dataSource;
    
    /**
     * Sets Data Source
     * @param dataSource 
     */
    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }

    /**
     * @return the dataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}
