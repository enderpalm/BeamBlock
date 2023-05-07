package me.emafire003.dev.beampass.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class BeamCommands {


    //Based on Factions' code https://github.com/ickerio/factions
    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        LiteralCommandNode<ServerCommandSource> lightCommands = CommandManager
                .literal("beam")
                .build();

        LiteralCommandNode<ServerCommandSource> alias = CommandManager
                .literal("beamblock")
                .build();

        dispatcher.getRoot().addChild(lightCommands);
        dispatcher.getRoot().addChild(alias);

        BeamCommand[] commands = new BeamCommand[] {
                new AddBlocksCommand(registryAccess),
                new GetBlocksCommand(registryAccess),
                new RemoveBlocksCommand(registryAccess)
        };

        for (BeamCommand command : commands) {
            lightCommands.addChild(command.getNode());
            alias.addChild(command.getNode());
        }
    }
}
