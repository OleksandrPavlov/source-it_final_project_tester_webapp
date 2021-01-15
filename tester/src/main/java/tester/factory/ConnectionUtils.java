package tester.factory;

import java.sql.Connection;

public class ConnectionUtils {
private static final ThreadLocal<Connection> connections=new ThreadLocal<Connection>();
public static final Connection getCurrentConnection(){
	return connections.get();
}
public static final void setConnection(Connection c){
	connections.set(c);
}
public static final void clearCurrentConnection(){
	connections.remove();
}

}
