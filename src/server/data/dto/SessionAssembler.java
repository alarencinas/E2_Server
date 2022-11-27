package server.data.dto;
import java.util.ArrayList;
import java.util.List;
import server.data.domain.Session;
public class SessionAssembler {
	private static SessionAssembler instance;

	public SessionAssembler() { }
	public static SessionAssembler getInstance() {
		if(instance==null) {
			instance=new SessionAssembler();
		}
		return instance;
	}
	public SessionDTO sessionToDTO(Session session) {
		SessionDTO dto = new SessionDTO();
		
		dto.setDistance(session.getDistance());
		dto.setDuration(session.getDuration());
		dto.setStart(session.getStart());
		
		dto.setSport(dto.getSport());
		dto.setTitle(session.getTitle());
		return dto;
	}

}
