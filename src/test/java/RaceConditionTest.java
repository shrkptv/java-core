import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tasks.skynet.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RaceConditionTest {

    private Factory factory;

    @BeforeEach
    public void createFactory()
    {
        factory = new Factory();
    }

    @Test
    public void testTakeParts() throws InterruptedException {
        Faction factionWorld = new Faction("World", factory);
        Faction factionWednesday = new Faction("Wednesday", factory);

        factory.run();

        int remainingParts = factory.getProducedPart().values()
                .stream()
                .mapToInt(Integer::intValue).sum();

        Thread thread1 = new Thread(() -> factionWorld.takeParts(3));
        Thread thread2 = new Thread(() -> factionWednesday.takeParts(2));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        int takenPartsWorld = factionWorld.getArmy().values()
                .stream().mapToInt(Integer::intValue).sum();
        int takenPartsWednesday = factionWednesday.getArmy().values()
                .stream().mapToInt(Integer::intValue).sum();

        assertTrue(takenPartsWorld + takenPartsWednesday <= remainingParts);
    }

    @Test
    public void testAddPart() throws InterruptedException {
        Faction faction = new Faction("Faction", factory);

        Thread thread1 = new Thread(() -> faction.addPart(RobotPart.HEAD));
        Thread thread2 = new Thread(() -> faction.addPart(RobotPart.HEAD));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertEquals(2, faction.getArmy().get(RobotPart.HEAD));
    }
}
