package ${groupId}.ui.membership;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ${groupId}.membernet.MembernetManager;
import ${groupId}.ui.base.GenericPage;

import com.yoso.dev.membernet.member.domain.Member;
import com.yoso.dev.membernet.membership.domain.Membership;
import com.yoso.dev.membernet.society.domain.Society;


public class MembershipPage extends GenericPage {

	@SpringBean
	private MembernetManager membernetManager;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Override
	public void inic() {
		// TODO Auto-generated method stub
		super.inic();
		setTitle("Wicket - membership");
		setHeader("Membership");
	}
	
	@Override
	public void addComponents() {
		// TODO Auto-generated method stub
		super.addComponents();
		showMembershipTable("membershipTable");
	}
	
	private void showMembershipTable(String tableID) {
		List<Membership> memberships = membernetManager.listAll();
		
		final DataView dataView = new DataView(tableID,new ListDataProvider(memberships)){
			
			
			@Override
			protected void populateItem(Item item) {
				// TODO Auto-generated method stub
				final Membership membership = (Membership) item.getModelObject();
				final Member m = (Member)membership.getLower();
				final Society s = (Society)membership.getUpper();
				final boolean isAdmin = membership==null?false:membership.isSocietyAdmin();
				item.add(new Label("id",membership.getId()));
				item.add(new Label("mId", m==null?"null":m.getId()));
				item.add(new Label("sId", s==null?"null":s.getSocietyId()));
				item.add(new Label("mName",m==null?"null":m.getFirstName() + " " + m.getLastName()));
				item.add(new Label("sName",s==null?"null":s.getName()));
				item.add(new Label("sAdmin",isAdmin));
				
				//links
//				item.add(new Link("interfaceLink") {
//
//					@Override
//					protected void onComponentTag(ComponentTag tag) {
//						// TODO Auto-generated method stub
//						super.onComponentTag(tag);
//						tag.put("target", "_blank");
//					}
//					
//					@Override
//					public void onClick() {
//						// TODO Auto-generated method stub
//						PageParameters params = new PageParameters();
//						params.add("personId", membership.getId());
//						
//						Class respPage = isAdmin?AdminInterfacePage.class:MemberInterfacePage.class;
//						
//						setResponsePage(respPage,params);
//					}
//					
//				});
				
			
			}
		};
		
		add(dataView);
		
	}
	
}
