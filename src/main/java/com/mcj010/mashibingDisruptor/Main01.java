package com.mcj010.mashibingDisruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

public class Main01 {
    public static void main(String[] args) {

        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, Executors.defaultThreadFactory());

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //官方例程
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);// Get the entry in the Disruptor
            // for the sequence
            event.set(8888L);
        } finally {

            ringBuffer.publish(sequence);
        }
    }
}
