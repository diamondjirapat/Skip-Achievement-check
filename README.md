# Skip Advancement Check

A server-only NeoForge mod for Minecraft 1.21.1 that disables per-player
advancement processing. It is intended for large modded servers where loading
and synchronizing thousands of advancements stalls the server when a player
joins.

## What it skips

- Reading and decoding each player's advancement JSON at login
- Checking automatic advancement criteria
- Registering advancement criterion listeners
- Calculating advancement-tree visibility and sending the initial tree
- Writing advancement progress

Because both reading and writing are skipped, existing files under
`world/advancements/` are preserved. They become active again if the mod is
removed.

## Consequences

Advancements are fully disabled while this mod is installed. Players cannot
earn advancements or receive advancement rewards, recipes, announcements, or
the advancement UI tree. Mods that use vanilla advancements as progression or
grant logic may therefore stop working as intended.

Clients do not need to install this mod.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.x
- Java 21

## Build

On Windows:

```powershell
.\gradlew.bat build
```

The mod JAR is written to `build/libs/`.

## Install

1. Stop the server.
2. Back up the world.
3. Put the built JAR in the server's `mods` directory only.
4. Start the server.

Remove the JAR to restore normal advancement behavior and resume using the
preserved advancement files.
