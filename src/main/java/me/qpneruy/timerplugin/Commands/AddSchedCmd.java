package me.qpneruy.timerplugin.Commands;

import me.qpneruy.timerplugin.Task.archiver;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.qpneruy.timerplugin.Task.TimeCalibarate.getDayOfWeek;

public class AddSchedCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("[TimerPro]: Lệnh này chỉ có thể sử dụng trong game!");
            return false;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("TimerPro.Them_Lenh")) {
            player.sendMessage("§6[TimerPro]: §cBạn không có quyền sử dụng lệnh này!");
            return false;
        }
        if (strings.length < 4) {
            player.sendMessage("§6[TimerPro]: §cThiếu tham số!");
            return false;
        }
        String Name = strings[0];
        String DayorDate = strings[1];
        if (DayorDate.equals("Hom_Nay")) {
            DayorDate = getDayOfWeek();
        }
        String Time = strings[2];

        archiver JsonWritter = new archiver();
        StringBuilder FullCommand = new StringBuilder(strings[3]);
        for (int i = 4; i < strings.length; ++i) {
            FullCommand.append(" ").append(strings[i]);
        }
        if (JsonWritter.addCommand(Name, FullCommand.toString(), DayorDate, Time)   == -1) {
            player.sendMessage("§6[TimerPro]: §cLỗi Định Dạng Thời Gian!");
            return false;
        }
        player.sendMessage("§6[TimerPro]:§f Đã thêm lệnh §a\"" + Name + "\"§f Thành Công!");
    return true;
    }
}
