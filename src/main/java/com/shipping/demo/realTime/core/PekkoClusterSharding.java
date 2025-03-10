package com.shipping.demo.realTime.core;

import org.apache.pekko.cluster.sharding.typed.javadsl.ClusterSharding;
import org.apache.pekko.cluster.sharding.typed.javadsl.Entity;

import com.shipping.demo.realTime.core.behaviors.ShardBehavior;

public class PekkoClusterSharding {

    private final ClusterSharding clusterSharding;

    public PekkoClusterSharding(ClusterSharding clusterSharding) {this.clusterSharding = clusterSharding;}

    public ClusterSharding getClusterSharding() {
        if (clusterSharding == null) {
            throw new IllegalStateException("ClusterSharding is not configured");
        }
        return clusterSharding;
    }

    public <T> void initEntity(ShardBehavior<T> shardBehavior) {
        assert clusterSharding != null;
        clusterSharding.init(
                Entity.of(shardBehavior.getEntityTypeKey(), shardBehavior::create)
                        .withMessageExtractor(shardBehavior.extractor())
        );
    }
}

