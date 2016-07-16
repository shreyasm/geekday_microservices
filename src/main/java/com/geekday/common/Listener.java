package com.geekday.common;

import com.geekday.account.CustomerCreatedReceiver;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Created by shreyas on 16/7/16.
 */
public class Listener implements Runnable {

    private static final Listener INSTANCE = new Listener();
    private String topic;
    private List<Receiver> subscribers;

    private Listener() {
        new Thread(new Listener("CustomerCreated", singletonList(new CustomerCreatedReceiver()))).start();
    }

    private Listener(String topic, List<Receiver> subscribers) {
        this.topic = topic;
        this.subscribers = subscribers;
    }

    public static Listener getInstance() {
        return INSTANCE;
    }

    @Override
    public void run() {
        DomainEventSubscriber subscriber = new DomainEventSubscriber(topic);
        DomainEvent receivedEvent = subscriber.receive();
        subscribers.forEach(consumer -> consumer.on(receivedEvent));
    }
}
