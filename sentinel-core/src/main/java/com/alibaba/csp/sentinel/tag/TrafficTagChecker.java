package com.alibaba.csp.sentinel.tag;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.traffic.ClusterManager;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.csp.sentinel.util.function.Function;
import io.opentelemetry.api.baggage.Baggage;

import java.util.Collection;

public class TrafficTagChecker {

    private RequestProxy requestProxy;

    private ClusterManager clusterManager;


    public void checkTag(Function<String, Collection<TagRule>> ruleProvider, ResourceWrapper resource, Context context) throws BlockException {
        if (ruleProvider == null || resource == null) {
            return;
        }
        System.out.println("++++++++++++++running +++++++++++++++++");
        // 获取资源的标记，并检查标记与本机是否匹配。
        Collection<TagRule> rules = ruleProvider.apply(resource.getName());
        System.out.println("do apply");
        // 获取标签
        String marked = getMarked();
        System.out.println("marked: "+ marked);
        if (StringUtil.isNotBlank(marked)) {

            // todo 路由到对应的机器上

//            clusterManager.route()
        } else {
            // 染色 将当前机器对应的tag染到请求上
            setMarked("fakeTag");
            System.out.println("make Tag");
        }
        if (rules != null) {
            for (TagRule rule : rules) {
                // todo 如何完善流量筛选规则检查
                if (!canPassTag(rule, resource, context)) {
                    throw new TagException(rule.getLimitApp(), rule);
                }
            }
        }
    }

    private String getMarked() {
        return Baggage.current().getEntryValue("label");
    }

    private void setMarked(String tag) {
        Baggage.current().toBuilder().put("label", tag).build().makeCurrent();
    }

    // todo
    private boolean canPassTag(TagRule rule, ResourceWrapper resource, Context context) {
        // todo 获取clusterInstance
        if (rule.getInstances().contains(context.getName())) {
            return true;
        }
        return false;
    }


}
