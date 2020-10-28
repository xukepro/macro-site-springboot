package com.xuke.macrosite.common.util;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by xuke on 2020/10/25
 */
public class NetUtil {

    /**
     * 检查url是否可以到达
     * @param address
     * @param port
     * @param timeout
     * @return
     */
    public static boolean checkAddressReachable(String address, int port, int timeout) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(address, port), timeout);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException ioException) {
                return false;
            }
        }
    }

    public static String getAddress() {
        return isLinux() ? getLinuxAddress() : getWindowsAddress();
    }

    /**
     * 判断系统是否是Linux
     * @return
     */
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    /**
     * 获得Linux IP地址
     * @return
     */
    public static String getLinuxAddress() {
        Enumeration<NetworkInterface> nis;
        String ip = null;
        try {
            nis = NetworkInterface.getNetworkInterfaces();
            for (; nis.hasMoreElements();) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                for (; ias.hasMoreElements();) {
                    InetAddress ia = ias.nextElement();
                    //ia instanceof Inet6Address && !ia.equals("")
                    if (ia instanceof Inet4Address && !"127.0.0.1".equals(ia.getHostAddress())) {
                        ip = ia.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获得windows IP地址
     * @return
     */
    public static String getWindowsAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
