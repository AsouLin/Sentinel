package com.alibaba.csp.sentinel.tag;

import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.property.DynamicSentinelProperty;
import com.alibaba.csp.sentinel.property.PropertyListener;
import com.alibaba.csp.sentinel.property.SentinelProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagRuleManager {

    // todo 敲定规则形式
    private static volatile Map<String, List<TagRule>> tagRules = new HashMap<>();

    // 监听器
    private static final TagRuleManager.TagPropertyListener LISTENER = new TagRuleManager.TagPropertyListener();

    // 监听配置是否变化
    private static final SentinelProperty<List<TagRule>> currentProperty = new DynamicSentinelProperty<List<TagRule>>();

    static {
        // 设置监听
        currentProperty.addListener(LISTENER);
    }

    public static List<TagRule> getRules() {
        List<TagRule> rules = new ArrayList<TagRule>();
        for (Map.Entry<String, List<TagRule>> entry : tagRules.entrySet()) {
            rules.addAll(entry.getValue());
        }
        return rules;
    }

    static Map<String, List<TagRule>> getTagRuleMap() {
        return tagRules;
    }

    private static final class TagPropertyListener implements PropertyListener<List<TagRule>> {

        @Override
        public synchronized void configUpdate(List<TagRule> value) {
            // 在配置变更时加载规则
            Map<String, List<TagRule>> rules = TagRuleUtil.buildTagRuleMap(value);
            if (rules != null) {
                tagRules = rules;
            }
            RecordLog.info("[FlowRuleManager] Flow rules received: {}", rules);
        }

        @Override
        public synchronized void configLoad(List<TagRule> conf) {
            Map<String, List<TagRule>> rules = TagRuleUtil.buildTagRuleMap(conf);
            if (rules != null) {
                tagRules = rules;
            }
            RecordLog.info("[FlowRuleManager] Flow rules loaded: {}", rules);
        }
    }

}
