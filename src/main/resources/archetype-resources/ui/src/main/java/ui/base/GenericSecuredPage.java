package ${groupId}.ui.base;

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import ${groupId}.ui.WicketApplication;

/**
 * Base page for pages accessible only to logged members. If no member is logged, redirects to the sign in page.
 * @author Zdenda
 *
 */
public class GenericSecuredPage extends GenericPage {

	public GenericSecuredPage() {
		super();
	}
	
	public GenericSecuredPage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	public void authenticate() {
		super.authenticate();
		AuthenticatedWebApplication app = (AuthenticatedWebApplication)WicketApplication.get();
		if (!isSignedIn()) {
			logger.debug("No user logged. Redirecting to membership page.");
			app.restartResponseAtSignInPage();
		}
	}
	
}
