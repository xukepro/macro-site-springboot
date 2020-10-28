package com.xuke.macrosite.chat.server;

import org.junit.jupiter.api.Test;

import java.net.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xuke on 2020/10/25
 */
public class NetTest {

    @Test
    public void test() throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
        System.out.println(getIp());

        System.out.println("Host addr: " + InetAddress.getLocalHost().getHostAddress());  // often returns "127.0.0.1"

//        Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
//        for (; n.hasMoreElements(); ) {
//            NetworkInterface e = n.nextElement();
//            System.out.println("Interface: " + e.getName());
//            Enumeration<InetAddress> a = e.getInetAddresses();
//            for (; a.hasMoreElements(); ) {
//                InetAddress addr = a.nextElement();
//                System.out.println("  " + addr.getHostAddress());
//            }
//        }
        System.out.println(getLinuxAddress());
        System.out.println(getWindowsAddress());
    }

    public static Set<String> getIp() {
        Set<String> set = new HashSet<String>();
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        set.add(inetAddress.getHostAddress().toString());
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return set;
    }

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    private static String getLinuxLocalIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        return ip;
    }

    public static String getLocalIP() {
        String ip = "";
        boolean isLinux = true;
        try {
            if (isLinux) {
                Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
                        .getNetworkInterfaces();
                while (e1.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) e1.nextElement();
                    if (!ni.getName().equals("eth0")) {
                        continue;
                    } else {
                        Enumeration<?> e2 = ni.getInetAddresses();
                        while (e2.hasMoreElements()) {
                            InetAddress ia = (InetAddress) e2.nextElement();
                            if (ia instanceof Inet6Address)
                                continue;
                            ip = ia.getHostAddress();
                        }
                        break;
                    }
                }
            } else {
                ip = InetAddress.getLocalHost().getHostAddress().toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ip;
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
    public String getWindowsAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

}
