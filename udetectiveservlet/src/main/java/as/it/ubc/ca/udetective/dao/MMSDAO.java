package as.it.ubc.ca.udetective.dao;

import javax.sql.DataSource;

/**
 * @author UBC IT
 */
public abstract class MMSDAO {
    
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
