package com.huawei.it.groupserivce.service;

import com.huawei.it.groupserivce.config.KafkaProperties;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZooKeeperService {
    private ZooKeeper zooKeeper;

    public ZooKeeperService() throws IOException {
        zooKeeper = new ZooKeeper(
                KafkaProperties.ZOO_SERVER_URL + ":" + KafkaProperties.ZOO_SERVER_PORT,
                10000,
                new EventWatcher());
    }

    public List<String> getBrokerList() throws IOException, KeeperException, InterruptedException {
        List<String> ids = zooKeeper.getChildren("/brokers/ids", false);
        List<String> brokerList = new ArrayList<>();

        for (String id : ids) {
            String brokerInfo = new String(zooKeeper.getData("/brokers/ids/" + id, false, null));
            brokerList.add(brokerInfo);
        }

        return brokerList;
    }
}

class EventWatcher implements Watcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventWatcher.class);

    @Override
    public void process(WatchedEvent event) {
        LOGGER.info("start up zookeeper service");
    }
}
