package Test_volatile;

/**
 * @Classname Volatile_01
 * @Description
 * @Date 2022/10/23 13:42
 * @Created by brain
 */
public class Volatile_01 {
    private static boolean running = true;
    private static /*volatile*/ int count = 0;

    //请问，下面这个程序为什么无法停止
    static void test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (running) {
                count++;
//                System.out.println("哈哈");
            }
            System.out.println("count: " + count);
        });
        t1.start();
        /*
        主线程阻塞1秒 t1线程在执行while 1秒后 JIT编译器会将其检测为热点代码，进行常量替换
        running替换为true
        解决办法：
        1.java -Djava.compiler=NONE Volatile 让JIT放弃优化
        2.注释掉Thread.sleep减少while循环次数，让JIT判断其为热点代码之前感知到running的变化
        3.添加volatile
        volatile的作用就是保证内存可见性，体现在两个方面：
                1).让编译器放弃优化
                2）.添加内存屏障
         站在程序的角度来看，不算底层如何实现，volatile都没有影响，因为volatile是给编译器看的
         至于怎么用硬件提供的接口去实现这个要求，是编译器决定的。可能会使用读写屏障，但也有可能根本没用！
         编译器和虚拟机都有可能为了提高执行效率对指令进行重排，因此即使硬件保证了一致性，volatile也要保证
         编译器不会对指令重排！！
         此外，并发编程三大特性：有序性 原子性 可见性 volatile无法保证原子性(具体来说，多写情况下的原子性，一写多度
         的原子性volatile可以保证)，因此volatile经常配合CAS实现无锁的同步
         此外volatile强制内存可见(缓存失效)，因此执行效率一定会下降
         4.在while循环中加入一种能够刷新缓存的语句 比如println(这个函数底层是有synchronized的)
         */
        Thread.sleep(1000); // ①

        Thread t2 = new Thread(() -> running = false);
        t2.start();
        t1.join();
        t2.join();
    }


    /**
     * 如果不存在指令重排，那么可能的情况是(1,0) (0,1) (1,1)
     * 存在指令重排的话就可能出现(0,0)
     */
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void test02() throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(new Runnable() {
                public void run() {
                    //由于线程one先启动，下面这句话让它等一等线程two. 读着可根据自己电脑的实际性能适当调整等待时间.
                    shortWait(50000);
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }


    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }


    public static void main(String[] args) throws InterruptedException {
//        test01();
        test02();
    }
}
