package be.vdab.Retrovideo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class DefaultMandje implements Serializable, Mandje {
	
	private static final long serialVersionUID = 1L;
	private final List<Long> filmIDs = new ArrayList<>();
	
	/*
	 * (non-Javadoc)
	 *
	 * @see be.vdab.Retrovideo.web.Mandje#addFilmId(long)
	 */
	@Override
	public void addFilmId(long id) {
		
		filmIDs.add(id);
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see be.vdab.Retrovideo.web.Mandje#removeFilmId(long)
	 */
	@Override
	public void removeFilmId(long removeid) {
		
		final Iterator<Long> it = filmIDs.iterator();
		
		while (it.hasNext()) {
			long id = it.next();
			
			if (id == removeid) {
				it.remove();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see be.vdab.Retrovideo.web.Mandje#getFilmIds()
	 */
	@Override
	public List<Long> getFilmIds() {
		
		return filmIDs;
	}
}
