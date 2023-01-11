package server.data.dao;

import server.data.domain.Challenge;
import server.data.domain.Session;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import java.util.ArrayList;  
import java.util.List;

public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge> {
	private static ChallengeDAO instance;
	private ChallengeDAO() {}
		public static ChallengeDAO getInstance() {
			if (instance == null) {
				instance = new ChallengeDAO();
			}		
			return instance;
		}
		public void save(Challenge object) {
			super.saveObject(object);
		}
		
		public void delete(Challenge object) {
			super.deleteObject(object);
		}
		
		public List<Challenge> getAll(){
			
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			
			List<Challenge> challenges = new ArrayList<>();
			
			try {
				tx.begin();
				Extent<Challenge> extent = pm.getExtent(Challenge.class,true);
				for(Challenge c :extent) {
					challenges.add(c);
					
				}
				tx.commit();
			}catch (Exception ex) {
				System.out.println("  $ Error retrieving all Challenges " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				pm.close();
				
			}
			return challenges;
			
		}
		
		public Challenge find(String param) {
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			Challenge challengeF=null;
			
			try {
				tx.begin();
				Query<?> query =pm.newQuery("SELECT FROM"+Challenge.class.getName()+"WHERE name == '"+ param+ "'");
				query.setUnique(true);
				challengeF=(Challenge) query.execute();
				tx.commit();}
			catch (Exception ex) {
				System.out.println("  $ Error querying Challenge " + ex.getMessage());
				} finally {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}
				return challengeF;
				
			}
		
}
