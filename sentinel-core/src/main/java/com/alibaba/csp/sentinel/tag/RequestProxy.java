package com.alibaba.csp.sentinel.tag;

import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;

/**
 * 流量标记处理
 */
public interface RequestProxy {

    void setTag(ResourceWrapper resource, TagRule tag);

    TagRule getTag(ResourceWrapper resourceWrapper);
}
