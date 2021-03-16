package org.bgerp.plugin.custom.action.open;

import java.math.BigDecimal;
import java.sql.Connection;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bgerp.plugin.custom.Subscription;
import org.bgerp.plugin.custom.Plugin;

import ru.bgcrm.model.process.Process;
import ru.bgcrm.servlet.ActionServlet.Action;
import ru.bgcrm.struts.action.BaseAction;
import ru.bgcrm.struts.action.ProcessAction;
import ru.bgcrm.struts.form.DynActionForm;
import ru.bgcrm.util.Utils;
import ru.bgcrm.util.sql.ConnectionSet;

@Action(path = "/open/plugin/custom/subscription")
public class SubscriptionAction extends BaseAction {
    private static final String JSP_PATH = PATH_JSP_OPEN_PLUGIN + "/" + Plugin.ID;

    public ActionForward calc(ActionMapping mapping, DynActionForm form, ConnectionSet conSet) throws Exception {
        int modeId = form.getParamInt("modeId");
        int sessionsId = form.getParamInt("sessionsId");
        var processIds = Utils.toIntegerSet(form.getParam("processIds"));

        BigDecimal cost = Subscription.getCost(modeId, sessionsId, processIds);
        form.setResponseData("cost", cost);

        return data(conSet, null, JSP_PATH + "/calculate_result.jsp");
    }

    public ActionForward order(ActionMapping mapping, DynActionForm form, Connection con) throws Exception {
        int modeId = form.getParamInt("modeId");
        int sessionsId = form.getParamInt("sessionsId");
        var processIds = Utils.toIntegerSet(form.getParam("processIds"));

        var email = form.getParam("email", Utils::isValidEmail);

        var process = new Process();
        process.setTypeId(Subscription.PROCESS_TYPE_SUBSCRIPTION_ID);
        ProcessAction.processCreate(form, con, process);

        

        /* BigDecimal cost = Calculator.getCost(modeId, sessionsId, processIds);
        form.setResponseData("cost", cost); */

        return status(con, form);
    }
}
