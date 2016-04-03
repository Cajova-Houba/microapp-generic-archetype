package ${groupId}.ui.membership;

import java.io.Serializable;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ${groupId}.membernet.MembernetManager;
import ${groupId}.ui.base.GenericPage;
import ${groupId}.ui.base.genericTable.GenericTable;

import com.yoso.dev.membernet.membership.domain.Membership;


public class MembershipPage extends GenericPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void addComponents() {
		super.addComponents();
		addMembershipTable("membershipTable");
		
	}
	
	private void addMembershipTable(String tableID) {
		
		List<String> fieldNames = Arrays.asList(new String[] {"id", "lower.id", 
								"upper.societyId", "lower.firstName", "lower.lastName",
								"upper.name","societyAdmin","id"});
		
		MembershipTable table = new MembershipTable(tableID, Membership.class, membernetManager.listAll(), fieldNames, null);
		
		add(table);
	}
	
	class MembershipTable extends GenericTable {
		
		
		private static final long serialVersionUID = 1L;
		
		public MembershipTable(String id, Class<? extends Serializable> clazz,
				List<? extends Serializable> values, List<String> fieldNames,
				Map<String, String> headers) {
			super(id, clazz, values, fieldNames, headers);
		}
	}
}
