
package tester.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import javax.servlet.annotation.WebListener;

@WebListener
public class AppSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		System.out.println(se.getSession().getId());
      
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Destroyed "+se.getSession().getId());
	}
}
