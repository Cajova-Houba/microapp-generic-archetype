package ${groupId}.ui.membership;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import ${groupId}.membernet.vo.MembershipVO;
import ${groupId}.ui.base.GenericPage;
import ${groupId}.ui.base.genericTable.GenericTable;


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
		
		List<String> fieldNames = Arrays.asList(new String[]{"id","name","society.id","society.name","isSocietyAdmin"});
		
		List<MembershipVO> values = new ArrayList<MembershipVO>();
		for(Integer societyId : Arrays.asList(-3,-2,-1,1,2,3)) {
			values.addAll(membernetManager.listAll(societyId));
		}
		
		MembershipTable table = new MembershipTable(tableID, MembershipVO.class, values, fieldNames, null);
		
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
