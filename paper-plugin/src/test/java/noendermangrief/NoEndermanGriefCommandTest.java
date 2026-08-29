package noendermangrief;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

class NoEndermanGriefCommandTest {

    private ServerMock server;
    private NoEndermanGriefPlugin plugin;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(NoEndermanGriefPlugin.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void reload_withPermission_sendsConfirmation() {
        PlayerMock player = server.addPlayer();
        player.addAttachment(plugin, "noendermangrief.reload", true);

        server.dispatchCommand(player, "negreload");

        player.assertSaid("NoEndermanGrief configuration reloaded.");
    }

    @Test
    void reload_withoutPermission_sendsDenialMessage() {
        PlayerMock player = server.addPlayer();

        server.dispatchCommand(player, "negreload");

        player.assertSaid("You do not have permission to use this command.");
    }
}
