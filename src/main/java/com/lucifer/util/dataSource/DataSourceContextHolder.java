package com.lucifer.util.dataSource;


/**
 */
public class DataSourceContextHolder {
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = DataSourceConfig.MASTER;

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        String ds = contextHolder.get();
        if( ds == null ){
            return DEFAULT_DS;
        }
        return ds;
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }


}
