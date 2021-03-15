package org.bgerp.plugin.custom.action.open;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bgerp.plugin.custom.Calculator;
import org.bgerp.plugin.custom.Plugin;

import ru.bgcrm.servlet.ActionServlet.Action;
import ru.bgcrm.struts.action.BaseAction;
import ru.bgcrm.struts.form.DynActionForm;
import ru.bgcrm.util.Utils;
import ru.bgcrm.util.sql.ConnectionSet;

@Action(path = "/open/plugin/custom/calculate")
public class CalculateAction extends BaseAction {
    private static final String JSP_PATH = PATH_JSP_OPEN_PLUGIN + "/" + Plugin.ID;

    public ActionForward calc(ActionMapping mapping, DynActionForm form, ConnectionSet conSet) throws Exception {
        int modeId = form.getParamInt("modeId");
        int sessionsId = form.getParamInt("sessionsId");
        var processIds = Utils.toIntegerSet(form.getParam("processIds"));

        BigDecimal cost = Calculator.getCost(modeId, sessionsId, processIds);
        form.setResponseData("cost", cost);

        return data(conSet, null, JSP_PATH + "/calculate_result.jsp");
    }

    public ActionForward order(ActionMapping mapping, DynActionForm form, ConnectionSet conSet) throws Exception {
        int modeId = form.getParamInt("modeId");
        int sessionsId = form.getParamInt("sessionsId");
        var processIds = Utils.toIntegerSet(form.getParam("processIds"));

        

        /* BigDecimal cost = Calculator.getCost(modeId, sessionsId, processIds);
        form.setResponseData("cost", cost); */

        return status(conSet, form);
    }
}
