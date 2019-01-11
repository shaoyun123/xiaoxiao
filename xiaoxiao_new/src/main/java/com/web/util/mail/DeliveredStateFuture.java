package com.web.util.mail;


public class DeliveredStateFuture {
    private DeliveredState state = DeliveredState.INITIAL;
    synchronized void waitForReady() throws InterruptedException {
        if (state == DeliveredState.INITIAL) {
            wait();
        }
    }
    synchronized DeliveredState getState() {
        return state;
    }
    synchronized void setState(DeliveredState newState) {
        state = newState;
        notifyAll();
    }
}
