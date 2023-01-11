package server.data.dao;

import server.data.domain.User;
import server.data.domain.Session;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import java.util.ArrayList; 
import java.util.List;

public class SessionDAO extends DataAccessObjectBase implements IDataAccessObject<Session>{
	private static SessionDAO instance;
	private SessionDAO() {}
		public static SessionDAO getInstance() {
			if (instance == null) {
				instance = new SessionDAO();
			}		
			return instance;
		}
	
	public List<Session> getAll(){
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<Session> sessions = new ArrayList<>();
		
		try {
			tx.begin();
			Extent<Session> extent = pm.getExtent(Session.class,true);
			for(Session s :extent) {
				sessions.add(s);
				
			}
			tx.commit();
		}catch (Exception ex) {
			System.out.println("  $ Error retrieving all Sessions " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			
		}
		return sessions;
		
	}
	public Session find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Session sessionF=null;
		
		try {
			tx.begin();
			Query<?> query =pm.newQuery("SELECT FROM"+Session.class.getName()+"WHERE title == '"+ param+ "'");
			query.setUnique(true);
			sessionF=(Session) query.execute();
			tx.commit();}
		catch (Exception ex) {
			System.out.println("  $ Error querying Session: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
			return sessionF;
			
		}
	
	public void save(Session object) {
		super.saveObject(object);
	}
	
	public void delete(Session object) {
		super.deleteObject(object);
	}
	
		
	}
	

