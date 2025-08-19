package com.horizon.backend.contract;

import java.time.Instant;

public record WriteOutPayLoad(
        String eventType,
        String identifierId,
        Instant timestamp
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private String eventType;
        private String identifierId;
        private Instant timestamp;
    }

    public WriteOutPayLoad build(){
        return new WriteOutPayLoad(
                eventType,
                identifierId,
                timestamp
        );
    }
}
