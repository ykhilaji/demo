package com.shipping.demo.realTime.core;

import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.cluster.ClusterEvent.CurrentClusterState;
import org.apache.pekko.cluster.typed.Cluster;

import com.shipping.demo.realTime.core.common.Nullable;

public class PekkoCluster {

    @Nullable
    private final Cluster cluster;

    public PekkoCluster(@Nullable ActorSystem<?> actorSystem) {
        if (actorSystem != null) {
            this.cluster = Cluster.get(actorSystem);
        } else {
            cluster = null;
        }
    }

    public boolean isClusterConfigured() {
        return cluster != null;
    }

    public CurrentClusterState state() {
        return cluster.state();
    }
}