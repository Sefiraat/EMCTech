package io.github.sefiraat.emctech.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import io.github.sefiraat.emctech.EmcTech;
import io.github.sefiraat.emctech.emc.EmcStorage;
import io.github.sefiraat.emctech.utils.EmcUtils;
import io.github.sefiraat.emctech.utils.Theme;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("emctech|emc")
public class EmcTechMain extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(Theme.ERROR + "Please provide a valid subcommand.");
        }
    }

    @Subcommand("emc")
    @Description("Displays the player's current EMC")
    public void viewEmc(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage(Theme.MAIN + "Current EMC: " + EmcUtils.EMC_FORMAT.format(EmcStorage.getEmc(player)));
        }
    }

    @Subcommand("viewemc")
    @CommandPermission("EMCTech.Admin")
    @CommandCompletion("@players")
    @Description("Displays the player's current EMC")
    public void viewEmc(CommandSender sender, Player player) {
        if (sender instanceof Player commandIssuer) {
            commandIssuer.sendMessage(Theme.MAIN + player.getName() + "'s current EMC: " + EmcStorage.getEmc(player));
        } else {
            EmcTech.getInstance().getLogger().info(Theme.MAIN + player.getName() + "'s current EMC: " + EmcUtils.EMC_FORMAT.format(EmcStorage.getEmc(player)));
        }
    }

    @Subcommand("addemc")
    @CommandPermission("EMCTech.Admin")
    @CommandCompletion("@players <amount>")
    @Description("Adds EMC to the player's pool")
    public void addEmc(CommandSender sender, OnlinePlayer player, double amount) {
        EmcStorage.addEmc(player.getPlayer(), amount);
    }

    @Subcommand("setemc")
    @CommandPermission("EMCTech.Admin")
    @CommandCompletion("@players <amount>")
    @Description("Sets the player's EMC pool")
    public void setEmc(CommandSender sender, OnlinePlayer player, double amount) {
        EmcStorage.setEmc(player.getPlayer(), amount);
    }

    public void sendPlayerOnlyMessage(CommandSender sender) {
        sender.sendMessage(Theme.ERROR + "This can only be done as a player.");
    }
}

