/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hospital.status.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/** 
 * MyEclipse Struts
 * Creation date: 05-17-2016
 * 
 * XDoclet definition:
 * @struts.action input="test1.jsp" validate="true"
 * @struts.action-forward name="method2" path="/method2"
 * @struts.action-forward name="method1" path="/method1"
 */
public class test1Action extends DispatchAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward doAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DynaActionForm test1Action = (DynaActionForm)form;
		ActionMessages errors = new ActionMessages();
		errors.add("error2", new ActionMessage("errors.validate.number"));
		super.saveErrors(request, errors);
		return mapping.findForward("method1");
	}
}