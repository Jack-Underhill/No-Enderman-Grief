# Modrinth listing draft — Paper plugin (server)

Draft copy for the "Description" field of the existing listing at
https://modrinth.com/plugin/no-enderman-grief-2025 — paste/adapt into Modrinth's editor, don't upload this file itself.

Also update while there: the listing currently lists supported platforms as Bukkit/Paper/Purpur/Spigot — narrow this to **Paper only** (the jar is Mojang-mapped and won't load on vanilla Spigot or Bukkit).

---

**Every mob can be spawn-proofed and optimized around — except endermen.**

They teleport straight through spawn-proofing into hidden pockets (deep underground, inside player bases), and the moment one picks up a block, it sticks around far longer than it should, quietly eating into the mob cap and tanking spawn rates on any mob farm nearby. Run a base with several farms, and endermen are the one mob nobody can design around — no matter how well everything else is optimized. (And yes, they also just grief builds overnight.)

No Enderman Grief fixes that at the source: endermen simply can't pick up or place blocks anymore, full stop. Unlike turning off the `mobGriefing` gamerule, this doesn't touch anything else — creepers still explode, villagers still farm, silverfish still infest. Only endermen are affected.

### Is this the right download for you?

This is the **server plugin** — install it if you run a Paper server. Playing singleplayer instead? Grab the Fabric mod version instead — same idea, built for singleplayer worlds and Fabric servers. **[TODO: link once the Fabric mod's Modrinth listing exists — see docs/modrinth-fabric-mod.md]**

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

1. Download the jar below.
2. Drop it into your server's `plugins/` folder.
3. Restart your server.
4. Done — endermen are already blocked from griefing. No further setup needed.

### Configuring it (optional)

Everything works out of the box with sensible defaults. If you want to fine-tune it — turning it off in specific worlds, or turning on logging — a `config.yml` is created automatically in `plugins/NoEndermanGrief/` the first time the plugin runs. Full details on every option are in the [GitHub README](https://github.com/Jack-Underhill/No-Enderman-Grief/blob/master/paper-plugin/README.md).

### Source & issues

Source code, build instructions, and issue tracker: https://github.com/Jack-Underhill/No-Enderman-Grief
