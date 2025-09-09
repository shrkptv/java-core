package tasks.skynet;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Faction implements Runnable{

    private final Random random = new Random();
    private final Map<RobotPart, Integer> army;
    private final Lock lock = new ReentrantLock();
    private Factory factory;
    private String name;

    {
        army = new EnumMap<>(RobotPart.class);
        for(var robotPart: RobotPart.values())
        {
            army.put(robotPart, 0);
        }
    }
    public Faction(String name, Factory factory) {
        this.name = name;
        this.factory = factory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public Map<RobotPart, Integer> getArmy() {
        return army;
    }

    @Override
    public void run()
    {
        int randomPartsNum = random.nextInt(6);
        int takenParts = takeParts(randomPartsNum);

        if(takenParts > 0)
        {
            System.out.println(name + " took " + takenParts + " parts");
        }

        System.out.println(name + "'s inventory: " + army);
    }

    public void addPart(RobotPart robotPart)
    {
        lock.lock();
        try{
            army.put(robotPart, army.get(robotPart) + 1);
        }
        finally{
            lock.unlock();
        }
    }

    public int takeParts(int partsNum)
    {
        lock.lock();
        try
        {
            int takenParts= 0;
            for(int i = 0; i < partsNum; i++)
            {
                RobotPart robotPart = factory.giveRobotPart();
                if(robotPart == null)
                {
                    break;
                }

                addPart(robotPart);
                takenParts++;
            }
            return takenParts;
        }
        finally{
            lock.unlock();
        }
    }

    public int countRobots()
    {
        return Math.min(Math.min(army.get(RobotPart.HEAD), army.get(RobotPart.TORSO)),
                Math.min(army.get(RobotPart.HAND) / 2, army.get(RobotPart.FEET) / 2));
    }
}
