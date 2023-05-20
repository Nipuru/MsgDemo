package top.nipuru.msgdemo.bukkit.processor;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AsyncUserProcessor;
import top.nipuru.msgdemo.message.PlayerMsgMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Nipuru
 * @since 2023/1/23 9:43
 */
public class PlayerMsgBukkitProcessor extends AsyncUserProcessor<PlayerMsgMessage> {

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, PlayerMsgMessage request) {
        String sender = request.getSender();
        String receiver = request.getReceiver();
        String msg = request.getMsg();

        Player senderPlayer = Bukkit.getPlayer(sender);
        if (senderPlayer != null) {
            senderPlayer.sendMessage("你->" + receiver + ": " + msg);
        }

        Player receiverPlayer = Bukkit.getPlayer(receiver);
        if (receiverPlayer != null) {
            receiverPlayer.sendMessage(sender + "->你: " + msg);
        }
    }

    @Override
    public String interest() {
        return PlayerMsgMessage.class.getName();
    }
}
