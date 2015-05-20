/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.CodeItem;
import com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;
import com.github.nest.quelea.codes.PartyRelationType.PartyRelationTypeContentProvider;

/**
 * relation type
 * 
 * @author brad.wu
 * @see PartyType
 */
@CodeTableRegistration(name = PartyRelationType.CODE_TABLE_NAME)
@CodeTableProvider(contentProviderClass = PartyRelationTypeContentProvider.class)
public class PartyRelationType extends AbstractCodeTable {
	private static final long serialVersionUID = 5496201821865226174L;

	public final static String CODE_TABLE_NAME = "RelationType";

	/**
	 * party relation type code item
	 * 
	 * @author brad.wu
	 *
	 */
	public static class PartyRelationTypeCodeItem extends CodeItem {
		private static final long serialVersionUID = -4032694812618217319L;
		private String sourcePartyTypeCode = null;
		private String targetPartyTypeCode = null;

		/**
		 * construct by codes<br>
		 * 1: code of item;<br>
		 * 2: code of from party type<br>
		 * 3: code of to party type
		 * 
		 * @param codes
		 */
		public PartyRelationTypeCodeItem(String... codes) {
			super(codes[0]);
			if (codes.length == 3) {
				this.setSourcePartyTypeCode(codes[1]);
				this.setTargetPartyTypeCode(codes[2]);
			} else if (codes.length == 2) {
				this.setSourcePartyTypeCode(codes[1]);
			}
		}

		/**
		 * @return the sourcePartyTypeCode
		 */
		public String getSourcePartyTypeCode() {
			return sourcePartyTypeCode;
		}

		/**
		 * @param sourcePartyTypeCode
		 *            the sourcePartyTypeCode to set
		 */
		public void setSourcePartyTypeCode(String sourcePartyTypeCode) {
			this.sourcePartyTypeCode = sourcePartyTypeCode;
		}

		/**
		 * @return the targetPartyTypeCode
		 */
		public String getTargetPartyTypeCode() {
			return targetPartyTypeCode;
		}

		/**
		 * @param targetPartyTypeCode
		 *            the targetPartyTypeCode to set
		 */
		public void setTargetPartyTypeCode(String targetPartyTypeCode) {
			this.targetPartyTypeCode = targetPartyTypeCode;
		}
	}

	/**
	 * party relation type content provider.<br>
	 * only read the first, second and third parts of line, separated by "|".
	 * 
	 * @author brad.wu
	 */
	public static class PartyRelationTypeContentProvider extends DefaultCodeTableContentProvider {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#createCodeItem(java.lang.String)
		 */
		@Override
		protected ICodeItem createCodeItem(String line) {
			String[] ss = line.split("[|]");
			return new PartyRelationTypeCodeItem(ss);
		}
	}
}
