package com.alibaba.csp.sentinel.traffic.tag;

import com.alibaba.csp.sentinel.Constants;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.node.DefaultNode;
import com.alibaba.csp.sentinel.slotchain.AbstractLinkedProcessorSlot;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.spi.Spi;
import com.alibaba.csp.sentinel.util.function.Function;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Spi(order = Constants.ORDER_TRAFFIC_SLOT)
public class TagTrafficSlot extends AbstractLinkedProcessorSlot<DefaultNode> {

    private final TrafficTagChecker checker;

    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode param, int count, boolean prioritized, Object... args) throws Throwable {
        // do check
        checker.checkTag(ruleProvider, resourceWrapper, context);
    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }


    public TagTrafficSlot() {
        // 实例化规则检查器
        this(new TrafficTagChecker());
    }

    public TagTrafficSlot(TrafficTagChecker checker) {
        this.checker = checker;
    }

    // 规则提供器
    private final Function<String, Collection<InstanceRule>> ruleProvider = new Function<String, Collection<InstanceRule>>() {
        @Override
        public Collection<InstanceRule> apply(String resource) {
            // tag - instances
            Map<String, List<InstanceRule>> tagRules = TagRuleManager.getTagRuleMap();
            return tagRules.get(resource);
        }
    };
}
