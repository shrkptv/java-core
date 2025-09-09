package tasks.skynet;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Simulation {

    public void simulate()
    {
        Factory factory = new Factory();
        Faction factionWorld = new Faction("World", factory);
        Faction factionWednesday = new Faction("Wednesday", factory);

        try (var pool = Executors.newFixedThreadPool(3)) {
            for(int i = 0; i < 100; i++)
            {
                try {
                    pool.submit(factory).get();
                    var future1 = pool.submit(factionWednesday);
                    var future2 = pool.submit(factionWorld);
                    future1.get();
                    future2.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        System.out.println();
        displayResults(factionWorld, factionWednesday);
    }

    private void displayResults(Faction faction1, Faction faction2)
    {
        int firstArmy = faction1.countRobots();
        int secondArmy = faction2.countRobots();
        System.out.println("Faction " + faction1.getName() + " army: " + firstArmy);
        System.out.println("Faction " + faction2.getName() + " army: " + secondArmy);

        if(firstArmy != secondArmy)
        {
            String strongest = firstArmy > secondArmy ? faction1.getName() : faction2.getName();
            System.out.println("Faction " + strongest + " has the strongest army after 100 days");
        }
        else
        {
            System.out.println("Factions have same armies");
        }
    }
}
