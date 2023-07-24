package com.alibaba.csp.sentinel.tag;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.traffic.Instance;

import java.util.List;

public class TagRule extends AbstractRule {

    /**
     * 标记
     */
    private String tag;

    /**
     * 实例组
     */
    private List<Instance> instances;


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
}
