package server.data.dao;
import server.data.domain.User;
import server.data.domain.Session;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import java.util.ArrayList; 
import java.util.List;

public class UserDAO extends DataAccessObjectBase implements IDataAccessObject<User>{
	private static UserDAO instance;
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}		
		return instance;
	}	
	
	
	public void save(User object) {
		super.saveObject(object);
	}

	
	public void delete(User object) {
		super.deleteObject(object);
	}

	
	public List<User> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<User> users = new ArrayList<>();

		try {
			tx.begin();
			
			Extent<User> userExtent = pm.getExtent(User.class, true);
			
			for (User user : userExtent) {
				users.add(user);
			}
						
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying all users: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return users;
	}

	
	public User find(String param) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		User result = null; 

		try {
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE nickname == '" + param + "'");
			query.setUnique(true);
			result = (User) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}

}
