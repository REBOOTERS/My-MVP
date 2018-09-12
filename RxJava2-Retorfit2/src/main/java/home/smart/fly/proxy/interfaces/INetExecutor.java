package home.smart.fly.proxy.interfaces;

/**
 * @author: zhuyongging
 * @date: 2018-09-10
 * @desc
 */
public interface INetExecutor {
    <T> T executor(IRequest request);
}
