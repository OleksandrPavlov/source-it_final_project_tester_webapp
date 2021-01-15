package tester.dao.service.models;

import java.util.ArrayList;
import java.util.List;

public class ServiceObjectToInsertRole {
private String query;
private int quantity;
private List<Long> roleKod=new ArrayList<>();
public List<Long> getRoleKod() {
	return roleKod;
}
public void setRoleKod(List<Long> roleKod) {
	this.roleKod = roleKod;
}
public String getQuery() {
	return query;
}
public void setQuery(String query) {
	this.query = query;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}
