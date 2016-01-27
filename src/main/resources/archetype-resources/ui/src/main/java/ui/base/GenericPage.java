package ${groupId}.ui.base;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import ${groupId}.ui.HomePage;


/**
 * Generic page. To change stuff, override init() method, use setters and do your own inicialization.
 * To add components, override addComponents() methods (with super call).
 * 
 * Override constructor just in case you need to use page Parameters. If you use constructor with page parameters
 * you need to override init() and addComponents() methods with page parameters too.
 * 
 * @author Zdenda
 *
 */
public class GenericPage extends WebPage {

	private final String NO_ERROR = "No errors.";
	private final String DEF_TITLE = "Wicket - Title";
	private final String DEF_HEADER = "Header";
	private final Class<?> DEF_BACK_LINK = HomePage.class;
	
	private final String TITLE_ID = "title";
	private final String HEADER_ID = "header";
	private final String BACK_LINK_ID = "btnBack";
	private final String ERROR_MSG_ID = "errorMsg";
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	protected StringBuilder errorMsgB;
	
	protected String title;
	
	protected String header;
	
	protected Class<?> backLink;
	
	public GenericPage() {
		errorMsgB = new StringBuilder();
		
		//assign values to fields
		inic();
		
		//actually add components
		addComponents();
		
		//add error message label
		showErrorMsg();
	}
	
	public GenericPage(PageParameters parameters) {
		// TODO Auto-generated constructor stub
		errorMsgB = new StringBuilder();
		
		//assign values to fields
		inic(parameters);
		
		//actually add components
		addComponents(parameters);
		
		//add error message label
		showErrorMsg(parameters);
	}
	
	// so you can override it without page parameters
	public void inic() {
		this.inic(null);
	}
	
	public void inic(PageParameters parameters) {
		loadParameters(parameters);
		setTitle(DEF_TITLE);
		setHeader(DEF_HEADER);
		setBackLink(DEF_BACK_LINK);
	}
	
	public void addComponents() {
		this.addComponents(null);
	}
	
	public void addComponents(PageParameters parameters) {
		add(new Label(TITLE_ID,getTitle()));
		add(new Label(HEADER_ID,getHeader()));
		add(new Link(BACK_LINK_ID) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				setResponsePage(getBackLink());
			}
			
		});
	}

	/**
	 * Override this method to load your parameters.
	 * @param parameters
	 */
	public void loadParameters(PageParameters parameters) {
		
	}
	
	public void showErrorMsg() {
		this.showErrorMsg(null);
	}
	
	public void showErrorMsg(PageParameters parameters) {
		add(new Label(ERROR_MSG_ID,getErrorMsg()));
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Class getBackLink() {
		return backLink;
	}

	public void setBackLink(Class<?> backLink) {
		this.backLink = backLink;
	}
	
	public String getErrorMsg() {
		if (errorMsgB.length() == 0) {
			return NO_ERROR;
		} else {
			return errorMsgB.toString();
		}
	}
	
	public void addError(String error) {
		errorMsgB.append(error);
	}
	
	
}
