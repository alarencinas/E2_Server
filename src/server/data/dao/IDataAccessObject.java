package server.data.dao;
import javax.jdo.annotations.Transactional;



import java.util.List;
public interface IDataAccessObject<DomainObject> {
	public List<DomainObject> getAll();
	public DomainObject find(String param);
	public void save(DomainObject object);
	public void delete(DomainObject object);
}
