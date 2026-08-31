# Modrinth listing draft — combined project (Paper + Fabric)

Draft copy for the consolidated listing at https://modrinth.com/plugin/no-enderman-grief-2025 (now hosts both the Paper plugin and the Fabric mod as separate versions in one project) — paste/adapt into Modrinth's editor, don't upload this file itself.

**Title:** No Enderman Grief

**Summary** (short tagline field):
> Stops endermen from picking up or placing blocks — on Paper servers or in Fabric singleplayer/servers — so they stop tanking your mob farm spawn rates. Every other mob behaves exactly as vanilla.

**Description** (long-form field, below):

---

**Every mob can be spawn-proofed and optimized around — except endermen.**

They teleport straight through spawn-proofing into hidden pockets (deep underground, inside player bases), and the moment one picks up a block, it sticks around far longer than it should, quietly eating into the mob cap and tanking spawn rates on any mob farm nearby. Run a base with several farms, and endermen are the one mob nobody can design around — no matter how well everything else is optimized. (And yes, they also just grief builds overnight.)

No Enderman Grief fixes that at the source: endermen simply can't pick up or place blocks anymore, full stop. Unlike turning off the `mobGriefing` gamerule, this doesn't touch anything else — creepers still explode, villagers still farm, silverfish still infest. Only endermen are affected.

## Which download do you need?

This project covers both platforms — pick the version tagged for yours below:

- **Running a Paper server?** Grab a `Paper-*` version.
- **Playing singleplayer, or running a Fabric server?** Grab a `Fabric-*` version.

Both do exactly the same thing — same behavior, same config philosophy — just built for different platforms, since a Bukkit/Paper plugin can't run in vanilla singleplayer (no plugin API exists there) and a Fabric mod can't run as a server plugin.

## Paper plugin

### Features

- Endermen simply can't pick up or place blocks anymore. That's it.
- No more block-holding endermen surviving indefinitely in hidden pockets, dragging down your mob farm spawn rates.
- Everything else about mob griefing stays vanilla.
- Turn it on or off per world, if you want endermen to behave differently in the Nether or your Overworld.
- Optional logging, if you want a record of what got blocked and when.
- One admin command to reload settings without restarting your server.

### Requirements

- Paper server (Spigot/Bukkit/Purpur are not supported — this plugin needs Paper specifically)
- Minecraft 1.21.x
- Java 21

### Installation

1. Download a `Paper-*` version below.
2. Drop it into your server's `plugins/` folder.
3. Restart your server.
4. Done — endermen are already blocked from griefing. No further setup needed.

### Configuring it (optional)

Everything works out of the box with sensible defaults. If you want to fine-tune it — turning it off in specific worlds, or turning on logging — a `config.yml` is created automatically in `plugins/NoEndermanGrief/` the first time the plugin runs. Full details on every option are in the [GitHub README](https://github.com/Jack-Underhill/No-Enderman-Grief/blob/master/paper-plugin/README.md).

## Fabric mod

### Features

- Endermen simply can't pick up or place blocks anymore. That's it.
- No more block-holding endermen surviving indefinitely in hidden pockets, dragging down your mob farm spawn rates.
- Everything else about mob griefing stays vanilla.
- Optional logging in chat and the log file, if you want to know what got blocked and when.
- No other mods required — this works entirely on its own.

### Requirements

- Fabric Loader (0.19.3 or newer)
- Minecraft 1.21
- Java 21
- No Fabric API needed — this mod doesn't depend on it

### Installation

1. Download a `Fabric-*` version below.
2. Drop it into your `.minecraft/mods/` folder (or your Fabric server's `mods/` folder).
3. Launch the game.
4. Done — endermen are already blocked from griefing. No further setup needed.

### Configuring it (optional)

Everything works out of the box with sensible defaults. If you want to turn on logging, a config file is created automatically at `config/no-enderman-grief.json` the first time the mod runs. Full details are in the [GitHub README](https://github.com/Jack-Underhill/No-Enderman-Grief/blob/master/fabric-mod/README.md).

## Source & issues

Source code, build instructions, and issue tracker: https://github.com/Jack-Underhill/No-Enderman-Grief
