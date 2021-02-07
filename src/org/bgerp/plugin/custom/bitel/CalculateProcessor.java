package org.bgerp.plugin.custom.bitel;

import ru.bgcrm.event.Event;
import ru.bgcrm.event.ProcessMarkedActionEvent;
import ru.bgcrm.event.listener.DynamicEventListener;
import ru.bgcrm.util.sql.ConnectionSet;
import ru.bgerp.util.Log;

public class CalculateProcessor extends DynamicEventListener {
    private static final Log log = Log.getLog();

    @Override
    public void notify(Event e, ConnectionSet conSet) throws Exception {
        if (!(e instanceof ProcessMarkedActionEvent)) {
            throw new IllegalArgumentException();
        }

        var event = (ProcessMarkedActionEvent) e;
        
    }
}
