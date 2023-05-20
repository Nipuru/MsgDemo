package top.nipuru.msgdemo.broker.processor;

import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import top.nipuru.msgdemo.message.PlayerMsgMessage;
import lombok.Setter;
import net.afyer.afybroker.server.BrokerServer;
import net.afyer.afybroker.server.aware.BrokerServerAware;
import net.afyer.afybroker.server.proxy.BrokerClientProxy;
import net.afyer.afybroker.server.proxy.BrokerPlayer;

/**
 * @author Nipuru
 * @since 2023/1/23 9:44
 */
public class PlayerMsgBrokerProcessor extends SyncUserProcessor<PlayerMsgMessage> implements BrokerServerAware {

    @Setter
    BrokerServer brokerServer;

    @Override
    public Object handleRequest(BizContext bizCtx, PlayerMsgMessage request) throws Exception {
        String sender = request.getSender();
        String receiver = request.getReceiver();

        BrokerPlayer senderPlayer = brokerServer.getPlayer(sender);
        BrokerPlayer receiverPlayer = brokerServer.getPlayer(receiver);

        //发送者或接受者不存在则返回false
        if (senderPlayer == null || receiverPlayer == null) {
            return false;
        }

        BrokerClientProxy senderBukkit = senderPlayer.getBukkitClientProxy();
        BrokerClientProxy receiverBukkit = senderPlayer.getBukkitClientProxy();

        //发送者或接受所在的bukkit代理不存在则返回false
        if (senderBukkit == null || receiverBukkit == null) {
            return false;
        }

        //如果发送者和接受者在同一个服务器则往这个服务器发消息
        if (senderBukkit == receiverBukkit) {
            senderBukkit.oneway(request);
            return true;
        }

        //分别往发送者和接受者服务器发消息
        senderBukkit.oneway(request);
        receiverBukkit.oneway(request);
        return true;
    }

    @Override
    public String interest() {
        return PlayerMsgMessage.class.getName();
    }
}
