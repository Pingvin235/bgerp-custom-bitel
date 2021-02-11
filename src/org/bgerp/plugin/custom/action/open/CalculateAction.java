package org.bgerp.plugin.custom.action.open;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bgerp.plugin.custom.Plugin;

import ru.bgcrm.dao.ParamValueDAO;
import ru.bgcrm.servlet.ActionServlet.Action;
import ru.bgcrm.struts.action.BaseAction;
import ru.bgcrm.struts.form.DynActionForm;
import ru.bgcrm.util.sql.ConnectionSet;

@Action(path = "/open/plugin/custom/calculate")
public class CalculateAction extends BaseAction {
    private static final String JSP_PATH = PATH_JSP_OPEN + "/" + Plugin.ID;

    @Override
    protected ActionForward unspecified(ActionMapping mapping, DynActionForm form, ConnectionSet conSet) throws Exception {
        var dao = new ParamValueDAO(conSet.getSlaveConnection());

        

        return data(conSet, null, JSP_PATH + "/calc_result.jsp");
    }
}
