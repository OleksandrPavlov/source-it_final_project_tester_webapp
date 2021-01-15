package tester.service;

import org.apache.commons.dbcp2.BasicDataSource;
import tester.dao.AccountDao;
import tester.dao.StudentDao;
import tester.dao.TutorDao;
import tester.dao.impl.AccountDaoImpl;
import tester.dao.impl.StudentDaoImpl;
import tester.dao.impl.TutorDaoImpl;
import tester.service.impl.AdminServiceImpl;
import tester.service.impl.CommonServiceImpl;
import tester.service.impl.StudentServiceImpl;
import tester.service.impl.TutorServiceImpl;

import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.Objects;

public final class ServiceManager {
    private final static String SERVICE_MANAGER = "SERVICE_MANAGER";

    private ServiceManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
        accountDao = new AccountDaoImpl();
        tutorDao = new TutorDaoImpl();
        studentDao = new StudentDaoImpl();
        studentService = new StudentServiceImpl(studentDao, dataSource);
        commonService = new CommonServiceImpl(accountDao, dataSource);
        adminService = new AdminServiceImpl(accountDao, dataSource);
        tutorService = new TutorServiceImpl(tutorDao, dataSource);
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public TutorServiceImpl getTutorService() {
        return (TutorServiceImpl) tutorService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public static ServiceManager getInstance(ServletContext servletContext, BasicDataSource dataSource) {
        if (!Objects.nonNull(servletContext.getAttribute(SERVICE_MANAGER))) {
            servletContext.setAttribute(SERVICE_MANAGER, new ServiceManager(dataSource));
        }
        return (ServiceManager) servletContext.getAttribute(SERVICE_MANAGER);

    }

    private final BasicDataSource dataSource;
    private final AccountDao accountDao;
    private final CommonService commonService;
    private final AdminService adminService;
    private final TutorService tutorService;
    private final TutorDao tutorDao;
    private final StudentService studentService;
    private final StudentDao studentDao;

    private BasicDataSource setUpDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/webtester");
        ds.setUsername("postgres");
        ds.setPassword("12345");
        ds.setInitialSize(5);
        ds.setMaxTotal(20);
        return ds;
    }

    public void shutdown() {
        try {
            dataSource.close();
        } catch (SQLException e) {

        }
        // LOGGER
    }

}
