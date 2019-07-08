package com.company.test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DataSourceSwitcher extends AbstractRoutingDataSource{

	private static final Logger logger = LogManager.getLogger(DataSourceSwitcher.class);

    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();


    public static void clearDataSourceType() {
        logger.debug("thread:{},remove,dataSource:{}",Thread.currentThread().getName());
        dataSourceKey.remove();
    }

    protected Object determineCurrentLookupKey() {
        String s = dataSourceKey.get();
        logger.debug("thread:{},determine,dataSource:{}",Thread.currentThread().getName(),s);
        return s;
    }

    public static void setDataSourceKey(String dataSource) {
        logger.debug("thread:{},set,dataSource:{}",Thread.currentThread().getName(),dataSource);
        dataSourceKey.set(dataSource);
    }
}
