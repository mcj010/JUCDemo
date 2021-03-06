package com.mcj010.mashibingDisruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.io.IOException;

public class Main03 {

    public static void main(String[] args) throws IOException {
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event));

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent((event, sequence) -> event.set(10000L));
        ringBuffer.publishEvent((event, sequence, l) -> event.set(l), 10000L);
        ringBuffer.publishEvent((event, sequence, l1, l2) -> event.set(l1 + l2),
                10000L, 10000L);
        System.in.read();
    }
}
