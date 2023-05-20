package top.nipuru.msgdemo.broker;

import top.nipuru.msgdemo.broker.processor.PlayerMsgBrokerProcessor;
import net.afyer.afybroker.server.plugin.Plugin;

public class MsgDemo extends Plugin {

    @Override
    public void onEnable(){
        getServer().registerUserProcessor(new PlayerMsgBrokerProcessor());
    }

}
