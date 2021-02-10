package org.bgerp.plugin.custom;

import java.util.Map;

import ru.bgcrm.plugin.Endpoint;
import ru.bgcrm.util.ParameterMap;

/**
 * BiTel's custom plugin.
 * @author Shamil Vakhitov
 */
public class Plugin extends ru.bgcrm.plugin.Plugin {
    public static final String ID = "custom";

    public Plugin() {
        super(ID);
    }

    @Override
    public boolean isEnabled(ParameterMap config, String defaultValue) {
        return true;
    }

    @Override
    public Map<String, String> getEndpoints() {
        return Map.of(
            Endpoint.JS, Endpoint.getPathPluginJS(ID)
        );
    }
}
