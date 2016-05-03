package ${groupId}.ui.base.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ${groupId}.membernet.MembernetManager;

/**
 * Generic session containing id of logged user.
 * @author Zdenda
 *
 */
public class GenericSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1L;

	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * Id of logged member
	 */
	private Long memberId;
	
	@SpringBean(name = "membernetManager")
	private MembernetManager membernetManager;
	
	public GenericSession(Request request) {
		super(request);
		//so the autowiring works
		org.apache.wicket.injection.Injector.get().inject(this);
	}

	
	/**
	 * For now, the username will be the memberId.
	 * Method will try to parse the memberId and check if it exists.
	 * If the parsing is ok and member with this id exists, then true is returned.
	 */
	@Override
	public boolean authenticate(String username, String password) {
		try {
			//try to parse it
			memberId = Long.parseLong(username);
			
			if (membernetManager == null) {
				logger.error("MembernetManager is null.");
				return false;
			}
			
			//check if exists
			if(membernetManager.exists(memberId)) {
				logger.debug("Successfully logged as member id="+memberId);
				return true;
			} else {
				logger.debug("Failed to log as member id="+memberId+". Member doesn't exist.");
				return false;
			}
		} catch (NumberFormatException e) {
			logger.warn("Error when parsing memberId: "+username);
			return false;
		}
	}

	@Override
	public void invalidate() {
		super.invalidate();
		memberId = null;
	}
	
	@Override
	public Roles getRoles() {
		return null;
	}
	
	public long getLoggedMemberId() {
		return memberId;
	}

}
