package AmazingJava.HighConcurrency.HookThreadTry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.sql.Time;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.HookThreadTry
 * @description 7-4 本代码段必须在linu下运行
 * 通过一个.lock文件判断线程是否已启动，已启动将会报错 ，已启动将会创建.lock，当线程结束使用HookThrea删除文件.lock，达到只有一个程序运行的目的
 * 但是有一个问题 程序A运行在running，现在运行同样的程序B，程序B抛异常，（程序B会删除.lock），然后在运行程序C，没有lock的程序c达不到想要的目的
 * @date 2018/10/16 15:18
 */
public class PreventDuplicated {
    private final static String LOCK_PATH ="/home/wangwenjun/locks/";
    private final static String LOCK_FILE =".lock";
    private final static String PERMISSIONS ="rw-------";

    public static void main(String[] args) throws IOException {
        checkRunning();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("thread been kill");
            getlocalPath().toFile().delete();
        }));

        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running .........");
        }
    }

    private static void checkRunning() throws IOException {
        Path path =getlocalPath();
        if(path.toFile().exists()){
            throw new RuntimeException("program already running.");
        }
        Set<PosixFilePermission> perms= PosixFilePermissions.fromString(PERMISSIONS);

        Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));
    }

    private static Path getlocalPath(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
