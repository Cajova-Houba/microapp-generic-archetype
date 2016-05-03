package ${groupId}.ui;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import ${groupId}.ui.base.security.GenericSession;
import ${groupId}.ui.membership.MembershipPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see ${groupId}.ui.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		
		mountPage("/home", HomePage.class);
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return MembershipPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return GenericSession.class;
	}
}
