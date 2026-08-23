package me.jack.noendermangrief.listener;

import me.jack.noendermangrief.NoEndermanGriefPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.block.BlockMock;
import org.mockbukkit.mockbukkit.world.WorldMock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EndermanBlockListenerTest {

    private ServerMock server;
    private NoEndermanGriefPlugin plugin;
    private WorldMock world;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(NoEndermanGriefPlugin.class);
        world = server.addSimpleWorld("world");
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    private EntityChangeBlockEvent fireBlockChangeEvent(EntityType entityType) {
        Entity entity = mock(Entity.class);
        when(entity.getType()).thenReturn(entityType);

        BlockMock block = world.getBlockAt(10, 64, -30);
        EntityChangeBlockEvent event = new EntityChangeBlockEvent(entity, block, Material.AIR.createBlockData());

        server.getPluginManager().callEvent(event);
        return event;
    }

    @Test
    void endermanBlockChange_isCancelled_whenWorldEnabled() {
        EntityChangeBlockEvent event = fireBlockChangeEvent(EntityType.ENDERMAN);

        assertTrue(event.isCancelled());
    }

    @Test
    void endermanBlockChange_isNotCancelled_whenWorldDisabled() {
        plugin.getConfig().set("default-enabled", false);

        EntityChangeBlockEvent event = fireBlockChangeEvent(EntityType.ENDERMAN);

        assertFalse(event.isCancelled());
    }

    @Test
    void nonEndermanBlockChange_isNotTouched() {
        EntityChangeBlockEvent event = fireBlockChangeEvent(EntityType.SILVERFISH);

        assertFalse(event.isCancelled());
    }

    @Test
    void loggingEnabled_logsCancelMessage() {
        plugin.getConfig().set("logging.enabled", true);
        List<LogRecord> records = captureLogRecords();

        fireBlockChangeEvent(EntityType.ENDERMAN);

        assertTrue(records.stream().anyMatch(r -> r.getMessage().contains("[EndermanBlocked]")));
    }

    @Test
    void loggingDisabled_doesNotLog() {
        List<LogRecord> records = captureLogRecords();

        fireBlockChangeEvent(EntityType.ENDERMAN);

        assertTrue(records.stream().noneMatch(r -> r.getMessage().contains("[EndermanBlocked]")));
    }

    private List<LogRecord> captureLogRecords() {
        List<LogRecord> records = new ArrayList<>();
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                records.add(record);
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() {
            }
        };
        plugin.getLogger().addHandler(handler);
        return records;
    }
}
