package org.bgerp.plugin.custom;

class SubscriptionKey {
    private final int processId;
    private final int paramId;

    SubscriptionKey(int modeId, int paramId) {
        this.processId = modeId;
        this.paramId = paramId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + processId;
        result = prime * result + paramId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubscriptionKey other = (SubscriptionKey) obj;
        if (processId != other.processId)
            return false;
        if (paramId != other.paramId)
            return false;
        return true;
    }
}