package com.alibaba.csp.sentinel.traffic.tag;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class TagException extends BlockException {
    public TagException(String ruleLimitApp) {
        super(ruleLimitApp);
    }

    public TagException(String ruleLimitApp, AbstractRule rule) {
        super(ruleLimitApp, rule);
    }

    public TagException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagException(String ruleLimitApp, String message) {
        super(ruleLimitApp, message);
    }

    public TagException(String ruleLimitApp, String message, AbstractRule rule) {
        super(ruleLimitApp, message, rule);
    }
}
