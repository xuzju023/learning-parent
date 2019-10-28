package pubsub;

import redis.clients.jedis.JedisPubSub;

/**
 * @date 2019/9/26 20:22
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class KeyExpiredListener extends JedisPubSub {
    
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe "
                + pattern + " " + subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        //System.out.println(pattern);
        //System.out.println(channel);
        System.out.println(message);
    }

}
