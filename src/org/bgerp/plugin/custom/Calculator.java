package org.bgerp.plugin.custom;

/**
 * Calculator for license prices.
 * @author Shamil Vakhitov
 */
public class Calculator {
    /** Subscription mode, type 'list' */
    public static final int PARAM_MODE_ID = 52;
    /** Contact E-Mail, type 'email' */
    public static final int PARAM_EMAIL_ID = 53;
    /** Sessions limit, type 'list'. */
    public static final int PARAM_SESSIONS_ID = 54;
    /* private static final int PARAM_SESSIONS_VAL_LESS_10 = 1;
    private static final int PARAM_SESSIONS_VAL_MORE_10 = 2; */
    
    /** Plugin prices, type 'listcount' */
    private static final int PARAM_PRICE_RUB_ID = 50;
    private static final int PARAM_PRICE_EUR_ID = 51;
}
