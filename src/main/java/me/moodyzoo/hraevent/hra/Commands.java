package me.moodyzoo.hraevent.hra;

import me.moodyzoo.hraevent.hra.tools.EventManager;
import me.moodyzoo.hraevent.hra.tools.PlayerManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             @NotNull String[] args) {
        if (args.length == 0) {
            return true;
        }

        Player player = (Player) sender;

        //check if player had permission hra.admin
        if (!sender.hasPermission("hra.admin")) {
            sender.sendMessage(ChatColor.RED + "Hond je hebt geen perms!");
            return true;
        }


            if (args[0].equalsIgnoreCase("toggle")) {
                if (Hra.inGame) {
                            sender.sendMessage(Component.text(ChatColor.RED + "Game gestopt"));
                    Hra.inGame = false;
                } else {
                    Hra.inGame = true;
                    sender.sendMessage(Component.text(ChatColor.GREEN + "Game gestart"));
                }

                }

            if(args[0].equalsIgnoreCase("event")) {
                switch(args[1].toLowerCase()) {
                    case "settp":

                        EventManager.setEventLocation(player.getLocation());
                        sender.sendMessage(Component.text(ChatColor.GREEN + "Teleport locatie gezet"));
                        break;
                    case "teleportplayers":
                        EventManager.teleportToEventLocation();
                        sender.sendMessage(Component.text(ChatColor.GREEN + "Spelers geteleporteerd"));
                        break;
                    case "start":
                        PlayerManager.addPlayers();
                        sender.sendMessage(Component.text(ChatColor.GREEN + "Players added"));
                        break;
                }
            }

            return true;
            };



}
