# NoEndermanGrief

**NoEndermanGrief** is a lightweight Paper/Spigot plugin that completely disables enderman block griefing while keeping all other mob behavior vanilla.

Endermen can no longer pick up or place blocks, but creepers still explode, villagers still farm, and the `mobGriefing` gamerule remains under your control.

---

## Features

- Prevents **only endermen** from changing blocks (both pickup and placement).
- Does **not** modify the global `mobGriefing` gamerule.
- Per-world enable/disable via `config.yml`.
- Optional console logging with timestamps and coordinates whenever an enderman block change is cancelled.
- Minimal surface area: one admin-only reload command, no changes for regular players.

---

## Requirements

- **Server:** Paper or Spigot (Paper recommended)
- **Minecraft:** 1.21+
- **Java:** 21

This plugin is developed against the Paper 1.21 API with Mojang mappings.

---

## Installation

1. Download the latest release JAR from the [Releases](./releases) page or build it with Maven (see below).
2. Place the JAR into your server’s `plugins/` folder.
3. Start (or restart) your Paper/Spigot server.
4. Run `/plugins` in game or in the console and verify that **NoEndermanGrief** is listed and enabled.

On first run, the plugin will create a `NoEndermanGrief` folder inside `plugins/` with a default `config.yml`.

---

## Configuration

`plugins/NoEndermanGrief/config.yml`:

```yaml
# If a world is not listed under "worlds", this value decides
# whether the plugin is enabled there by default.
default-enabled: true

# Per-world overrides.
# Add world names and set them to true/false as needed.
# Example:
# worlds:
#   world: true
#   world_nether: false
#   world_the_end: true
worlds: {}

logging:
  # If true, log whenever the plugin cancels an enderman block change.
  enabled: false

  # If true, include a timestamp inside the message itself
  # (on top of the normal server log timestamp).
  include-timestamp: true
```

## Per-world control

 - If a world is not listed under `worlds`, `default-enabled` determines whether the plugin is active there.
 - If a world is listed under `worlds`, that value overrides `default-enabled`:

```yaml
worlds:
  world: true
  world_nether: false
  world_the_end: true
```

In this example, the plugin is:

 - Enabled in `world` and `world_the_end`.
 - Disabled in `world_nether`.

## Logging

 - `logging.enabled: true` – log a line each time an enderman block change is cancelled.
 - `logging.include-timestamp: true` – include an ISO-8601 timestamp in the log message.

Example log entry:

```text
[NoEndermanGrief] [EndermanBlocked] 2025-01-01T12:34:56 - Cancelled enderman block change in world 'world' at (10, 64, -30)
```

## Commands

Reloads `config.yml` from disk (without restarting the world / server).

 - `/negreload`

## Permission
Allows use of `/negreload`.

 - `noendermangrief.reload`
   - Default: `op`

## Building from source

This project uses Maven.
```bash
git clone https://github.com/<your-username>/NoEndermanGrief.git
cd NoEndermanGrief
mvn package
```

The compiled plugin JAR will be located in the target/ directory:
```text
target/no-enderman-grief-1.0.0-SNAPSHOT.jar
```

Copy this JAR into your server’s plugins/ folder.

## How it works (technical overview)

 - The plugin registers a listener for EntityChangeBlockEvent.
 - If the event’s entity type is ENDERMAN and the plugin is enabled in that world:
    - The event is cancelled so the block change does not occur.
    - If logging is enabled, a timestamped message with world and coordinates is written to the console.
 - No other entity types are modified, so creepers, villagers, and other mobs behave as in vanilla.

This makes the plugin safe to drop into existing survival worlds where you want to preserve terrain from enderman griefing without affecting any other mechanics.
