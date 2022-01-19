package com.fimon.scc.utils;

import org.springframework.http.converter.HttpMessageNotReadableException;

public class IdGenerator {

    private static final long epoch = 1596211200000L;

    private static final long workerIdBits = 5L;

    private static final long datacenterIdBits = 5L;

    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private static final long sequenceBits = 12L;

    private static final long workerIdShift = sequenceBits;

    private static final long datacenterShift = sequenceBits + workerIdBits;

    private static final long timestampShift = sequenceBits + workerIdBits + datacenterIdBits;

    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);


    private long workerId;

    private long dataCenterId;

    private long sequence;

    private long lastTimestamp;

    public IdGenerator(long dataCenterId, long workerId) {
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;

        this.sequence = 0L;
        this.lastTimestamp = -1L;
    }


    public synchronized long nextId() {

        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards, refuse to generate id.");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = untilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        long id = ((timestamp - epoch) << timestampShift) | (dataCenterId << datacenterShift) | (workerId << workerIdShift)
                | sequence;

        return id;
    }

    private static long untilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp == lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static long parseId(String str) {
        long id = -1;
        try {
            id = Long.parseLong(str);
        } catch (Exception e) {
            throw new HttpMessageNotReadableException("invalid id");
        }
        return id;
    }
}
