package com.alibaba.csp.sentinel.traffic.tag;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.traffic.Instance;

import java.util.List;

public class InstanceRule extends AbstractRule {

    /**
     * 标记
     */
    private String routeGroup;

    /**
     * 实例组 = host
     */
    private List<String> instances;

    /**
     * 服务版本
     */
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRouteGroup() {
        return routeGroup;
    }

    public void setRouteGroup(String routeGroup) {
        this.routeGroup = routeGroup;
    }

    public List<String> getInstances() {
        return instances;
    }

    public void setInstances(List<String> instances) {
        this.instances = instances;
    }
}
