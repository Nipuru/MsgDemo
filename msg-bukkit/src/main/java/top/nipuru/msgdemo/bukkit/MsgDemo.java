package top.nipuru.msgdemo.bukkit;

import top.nipuru.msgdemo.bukkit.command.MsgCommand;
import net.afyer.afybroker.client.Broker;
import top.nipuru.msgdemo.bukkit.processor.PlayerMsgBukkitProcessor;
import org.bukkit.plugin.java.JavaPlugin;

public class MsgDemo extends JavaPlugin {

    @Override
    public void onLoad(){
        Broker.registerUserProcessor(new PlayerMsgBukkitProcessor());
    }

    @Override
    public void onEnable(){
        getCommand("msg").setExecutor(new MsgCommand(this));
    }

}
