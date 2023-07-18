package top.nipuru.msgdemo.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerMsgMessage implements Serializable {
    private static final long serialVersionUID = 8849649245827190181L;

    /** 发送者名称 */
    String sender;

    /** 接受者名称 */
    String receiver;

    /** 消息 */
    String msg;
}
