package tester.listener;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import tester.constants.ApplicationInitConstants;
import tester.service.ServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static tester.Constants.APPLICATION_PROPERTIES_FILE;
import static tester.constants.ApplicationInitConstants.*;


@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(AppContextListener.class);
    private BasicDataSource basicDataSource;
    private Properties applicationProperties;

    public void contextDestroyed(ServletContextEvent sce) {
        ServiceManager.getInstance(sce.getServletContext(),basicDataSource).shutdown();
    }

    public void contextInitialized(ServletContextEvent sce) {
        initApplicationProperties();
        initDataSource();
        ServiceManager.getInstance(sce.getServletContext(),basicDataSource);
        LOG.info("AppContextListener-contextInitialized");
    }

    public void initDataSource(){
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(applicationProperties.getProperty(DB_DRIVER));
        basicDataSource.setUrl(applicationProperties.getProperty(DB_URL));
        basicDataSource.setUsername(applicationProperties.getProperty(DB_USERNAME));
        basicDataSource.setPassword(applicationProperties.getProperty(DB_PASSWORD));
        basicDataSource.setInitialSize(NumberUtils.createInteger(applicationProperties.getProperty(DB_INIT_POOL_SIZE)));
        basicDataSource.setMaxTotal(NumberUtils.createInteger(applicationProperties.getProperty(DB_MAX_POOL_SIZE)));
        LOG.debug("Connection pool has been initialized!");
    }

    private void initApplicationProperties() {
        applicationProperties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(APPLICATION_PROPERTIES_FILE)) {
            applicationProperties.load(inputStream);
        } catch (IOException e) {
            LOG.info("IO exception was occurred during loading application properties");
        }
        LOG.debug("Application properties object has been initialized!");
    }

}
