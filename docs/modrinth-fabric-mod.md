# Modrinth listing draft — Fabric mod (singleplayer)

Draft copy for creating a **new** Modrinth project (this mod isn't listed there yet). Paste/adapt into Modrinth's project creation flow, don't upload this file itself.

---

**Every mob can be spawn-proofed and optimized around — except endermen.**

They teleport straight through spawn-proofing into hidden pockets (deep underground, inside your base), and the moment one picks up a block, it sticks around far longer than it should, quietly eating into the mob cap and tanking spawn rates on any mob farm nearby. Building a base with several farms? Endermen are the one mob you can't design around — no matter how well everything else is optimized. (And yes, they also just grief your builds overnight.)

No Enderman Grief fixes that at the source: endermen simply can't pick up or place blocks anymore, full stop. Unlike turning off the `mobGriefing` gamerule, this doesn't touch anything else — creepers still explode, villagers still farm, silverfish still infest. Only endermen are affected.

### Is this the right download for you?

This is the **singleplayer / Fabric mod version** — install it if you play singleplayer, or run a small Fabric server. Running a full Paper server instead? Grab the [Paper plugin version](https://modrinth.com/plugin/no-enderman-grief-2025) instead — same idea, built for servers.

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

1. Download the jar below.
2. Drop it into your `.minecraft/mods/` folder (or your Fabric server's `mods/` folder).
3. Launch the game.
4. Done — endermen are already blocked from griefing. No further setup needed.

### Configuring it (optional)

Everything works out of the box with sensible defaults. If you want to turn on logging, a config file is created automatically at `config/no-enderman-grief.json` the first time the mod runs. Full details are in the [GitHub README](https://github.com/Jack-Underhill/No-Enderman-Grief/blob/master/fabric-mod/README.md).

### Source & issues

Source code, build instructions, and issue tracker: https://github.com/Jack-Underhill/No-Enderman-Grief
