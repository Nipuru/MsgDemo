package top.nipuru.msgdemo.bukkit;

import net.afyer.afybroker.client.BrokerClientBuilder;
import top.nipuru.msgdemo.bukkit.command.MsgCommand;
import net.afyer.afybroker.client.Broker;
import top.nipuru.msgdemo.bukkit.processor.PlayerMsgBukkitProcessor;
import org.bukkit.plugin.java.JavaPlugin;

public class MsgDemo extends JavaPlugin {

    @Override
    public void onLoad(){
        Broker.buildAction(this::buildBroker);
    }

    private void buildBroker(BrokerClientBuilder builder){
        builder.registerUserProcessor(new PlayerMsgBukkitProcessor());
    }

    @Override
    public void onEnable(){
        getCommand("msg").setExecutor(new MsgCommand(this));
    }

}
