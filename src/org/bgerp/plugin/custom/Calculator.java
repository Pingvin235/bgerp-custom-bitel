package org.bgerp.plugin.custom;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.bgcrm.cache.Cache;
import ru.bgcrm.cache.CacheHolder;
import ru.bgcrm.dao.ParamValueDAO;
import ru.bgcrm.dao.process.Tables;
import ru.bgcrm.model.param.ParameterListCountValue;
import ru.bgcrm.util.Setup;
import ru.bgcrm.util.Utils;
import ru.bgerp.util.Log;

/**
 * Calculator for license prices.
 * @author Shamil Vakhitov
 */
public class Calculator extends Cache<Calculator> {
    private static final Log log = Log.getLog();

    /** Contact E-Mail, type 'email' */
    public static final int PARAM_EMAIL_ID = 53;
    
    /** Subscription mode, type 'list' */
    public static final int PARAM_MODE_ID = 52;
    private static final int PARAM_MODE_VAL_RUB_TO_CARD_ID = 1;
    private static final int PARAM_MODE_VAL_RUB_INVOICE_ID = 2;
    private static final int PARAM_MODE_VAL_EUR_PAYPAL_ID = 3;
    
    /** Sessions limit, type 'list'. */
    public static final int PARAM_SESSIONS_ID = 54;
    /* private static final int PARAM_SESSIONS_VAL_0_10 = 1;
    private static final int PARAM_SESSIONS_VAL_11_UNL = 2; */

    /** Plugin prices, type 'listcount' */
    public static final int PARAM_PRICE_RUB_ID = 50;
    public static final int PARAM_PRICE_EUR_ID = 51;

    /** Process type ID for Plugin. */
    public static final int PROCESS_TYPE_PLUGIN_ID = 14;

    public static final int PROCESS_STATUS_OPEN_ID = 1;
    public static final int PROCESS_STATUS_SUPPORT_ID = 10;

    private static CacheHolder<Calculator> holder = new CacheHolder<>(new Calculator());

    /**
     * 
     * @param modeId subscription mode ID
     * @param sessionsId sessions limit ID
     * @param processIds
     * @return
     */
    public static BigDecimal getCost(int modeId, int sessionsId, Collection<Integer> processIds) {
        var result = BigDecimal.ZERO;

        var paramId = modeIdToParamId(modeId);
        var cacheInstance = holder.getInstance();

        log.debug("getCost modeId: %s, sessionsId: %s", modeId, sessionsId);

        for (int processId : processIds) {
            var key = new SubscriptionKey(processId, paramId);
            
            var prices = cacheInstance.prices.get(key);
            if (prices == null) continue;

            var price = prices.get(sessionsId);
            if (price == null) continue;

            result = result.add(price.getCount());

            log.debug("add processId: %s, add: %s", processId, price.getCount());
        }

        // additional for manual work
        if (modeId == PARAM_MODE_VAL_RUB_INVOICE_ID) {
            result = result.add(BigDecimal.valueOf(200));
        }

        return result;
    }

    private static int modeIdToParamId(int modeId) {
        switch (modeId) {
            case PARAM_MODE_VAL_RUB_INVOICE_ID:
            case PARAM_MODE_VAL_RUB_TO_CARD_ID:
                return PARAM_PRICE_RUB_ID;
            case PARAM_MODE_VAL_EUR_PAYPAL_ID:
                return PARAM_PRICE_EUR_ID;
            default:
                throw new IllegalArgumentException("Incorrect mode ID: " + modeId);
        }
    }

    public static void flush() {
        holder.flush(null);
    }

    // END OF STATIC

    private Map<SubscriptionKey, Map<Integer, ParameterListCountValue>> prices;

    private Calculator() {}

    protected Calculator newInstance() {
        var instance = new Calculator();

        instance.prices = new HashMap<>(100);

        try (var con = Setup.getSetup().getDBSlaveConnectionFromPool()) {
            var paramDao = new ParamValueDAO(con);

            var query = 
                "SELECT id FROM " + Tables.TABLE_PROCESS + " AS p " +
                "WHERE type_id=? AND status_id IN (" + Utils.toString(List.of(PROCESS_STATUS_OPEN_ID, PROCESS_STATUS_SUPPORT_ID)) + ")";
            var ps = con.prepareStatement(query);
            ps.setInt(1, PROCESS_TYPE_PLUGIN_ID);
            var rs = ps.executeQuery();
            while (rs.next()) {
                int processId = rs.getInt(1);
                loadPrices(instance, paramDao, processId, PARAM_PRICE_RUB_ID);
                loadPrices(instance, paramDao, processId, PARAM_PRICE_EUR_ID);
            }
            ps.close();
        } catch (Exception e) {
            log.error(e);
        }

        return instance;
    }

    private void loadPrices(Calculator instance, ParamValueDAO paramDao, int processId, int paramId) throws Exception {
        var key = new SubscriptionKey(processId, paramId);
        var prices = paramDao.getParamListCount(processId, paramId);
        instance.prices.put(key, prices);
    }
}
