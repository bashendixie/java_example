package com.home.skydance.controller;

import com.home.skydance.service.CacheForPojoService;
import com.home.skydance.service.CacheForXmlService;
import com.home.skydance.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final CacheForXmlService cacheForXmlService;
    private final UserService userService;

    public UserController(CacheForXmlService cacheForXmlService, UserService userService) {
        this.cacheForXmlService = cacheForXmlService;
        this.userService = userService;
    }

    @GetMapping("/createTcpClient")
    public void createTcpClient()
    {
        for(int i=0; i<15000; i++)
        {
            Socket s = new Socket();
            try {
                s.bind(new InetSocketAddress("127.0.0.1", 0));
                s.connect(new InetSocketAddress("127.0.0.1", 18080));
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                //BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                pw.write(String.valueOf("我是客户端".getBytes(StandardCharsets.UTF_8)));
                pw.flush();
                //String line = br.readLine();
                //System.out.println("收到服务端信息："+line);
                //pw.close();
                //br.close();
                //s.close();
            } catch (IOException e) {
                System.out.println("失败："+i);
                System.out.println("失败："+e.getMessage().toString());
                e.printStackTrace();
            }
        }
    }

    int kkk = 0;

    @GetMapping("/createTcpServer")
    public void createTcpServer()
    {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 18080));
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("已经创建");

            new Thread()
            {
                @Override
                public void run()
                {
                    //轮询服务
                    while (true) {
                        //选择准备好的事件
                        try {
                            selector.select();
                            //已选择的键集
                            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                            //处理已选择键集事件
                            while (it.hasNext()) {
                                SelectionKey key = it.next();
                                //处理掉后将键移除，避免重复消费(因为下次选择后，还在已选择键集中)
                                it.remove();
                                //处理连接请求
                                if (key.isAcceptable()) {
                                    //处理请求
                                    SocketChannel socket = serverSocketChannel.accept();
                                    socket.configureBlocking(false);
                                    //注册read，监听客户端发送的消息
                                    socket.register(selector, SelectionKey.OP_READ);
                                    //keys为所有键，除掉serverSocket注册的键就是已连接socketChannel的数量
                                    String message = "连接成功 你是第" + (selector.keys().size() - 1) + "个用户";
                                    //向客户端发送消息
                                    socket.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                                    InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                                    //输出客户端地址
                                    System.out.println(address.getHostString() + ":" + address.getPort() + "\t");
                                    System.out.println("客戶端已连接:" + (kkk++));
                                    System.out.println("=========================================================");
                                }

                                if (key.isReadable()) {
                                    SocketChannel socket = (SocketChannel) key.channel();
                                    InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                                    System.out.println(address.getHostString() + ":" + address.getPort() + "\t");
                                    ByteBuffer bf = ByteBuffer.allocate(1024 * 4);
                                    int len = 0;
                                    byte[] res = new byte[1024 * 4];
                                    //捕获异常，因为在客户端关闭后会发送FIN报文，会触发read事件，但连接已关闭,此时read()会产生异常
                                    try {
                                        while ((len = socket.read(bf)) != 0) {
                                            bf.flip();
                                            bf.get(res, 0, len);
                                            System.out.println(new String(res, 0, len, "utf-8"));
                                            bf.clear();
                                        }
                                        System.out.println("=========================================================");
                                    } catch (Exception e) {
                                        //客户端关闭了
                                        key.cancel();
                                        socket.close();
                                        System.out.println("客戶端已断开");
                                        System.out.println("=========================================================");
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加缓存
     */
    @PostMapping("/addCache")
    public void addCache()
    {
        this.cacheForXmlService.addMenuListToCache("小猫");
    }


    @PostMapping("/getCache")
    public void getCache()
    {
        String str = this.cacheForXmlService.getMenuListToCache();
        System.out.println(str);
    }
}
