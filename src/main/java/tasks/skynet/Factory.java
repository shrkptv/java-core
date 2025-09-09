package tasks.skynet;


import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Factory implements Runnable {

    private final Random random = new Random();
    private final Map<RobotPart, Integer> producedPart;
    private final Lock lock = new ReentrantLock();


    {
        producedPart = new EnumMap<>(RobotPart.class);
        for(var robotPart: RobotPart.values())
        {
            producedPart.put(robotPart, 0);
        }
    }

    private RobotPart getRandomPart()
    {
        RobotPart[] parts = RobotPart.values();
        return parts[random.nextInt(parts.length)];
    }

    @Override
    public void run()
    {
        lock.lock();
        try{
            int partQuantity = random.nextInt(11);
            for(int i = 0; i < partQuantity; i++)
            {
                RobotPart randomPart = getRandomPart();
                producedPart.put(randomPart, producedPart.get(randomPart) + 1);
            }
            System.out.println("Factory produced " + partQuantity + " parts. After this day: " + producedPart);
        }
        finally{
            lock.unlock();
        }
    }

    public Map<RobotPart, Integer> getProducedPart() {
        return producedPart;
    }

    public RobotPart giveRobotPart()
    {
        lock.lock();
        try{
            return producedPart.entrySet().stream()
                    .filter(entry -> entry.getValue() > 0)
                    .findAny()
                    .map(entry -> {
                        producedPart.put(entry.getKey(), entry.getValue() - 1);
                        return entry.getKey();
                    })
                    .orElse(null);
        }
        finally{
            lock.unlock();
        }
    }
}
