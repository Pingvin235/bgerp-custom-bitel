package org.bgerp.plugin.custom;

import ru.bgcrm.cache.Cache;
import ru.bgcrm.cache.CacheHolder;

/**
 * Calculator for license prices.
 * @author Shamil Vakhitov
 */
public class Calculator extends Cache<Calculator> {
    /** Subscription mode, type 'list' */
    public static final int PARAM_MODE_ID = 52;
    /** Contact E-Mail, type 'email' */
    public static final int PARAM_EMAIL_ID = 53;
    /** Sessions limit, type 'list'. */
    public static final int PARAM_SESSIONS_ID = 54;
    
    private static final int PARAM_SESSIONS_VAL_0_10 = 1;
    private static final int PARAM_SESSIONS_VAL_11_UNL = 2;

    /** Plugin prices, type 'listcount' */
    public static final int PARAM_PRICE_RUB_ID = 50;
    public static final int PARAM_PRICE_EUR_ID = 51;

    
    private static CacheHolder<Calculator> holder = new CacheHolder<>(new Calculator());

    /* private Map<Integer, Map */

    protected Calculator newInstance() {
        //var instance 
        return null;
    } 

    //todo cache
}
