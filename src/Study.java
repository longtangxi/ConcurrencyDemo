import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 87627 on 2017/7/11.
 */
public class Study {

    public static void main(String[] args) {
        // 除了newFixedThreadPool可以生成固定大小的线程池，
        // newCachedThreadPool可以生成一个无界、可以自动回收的线程池，
        // newSingleThreadScheduledExecutor可以生成一个单个线程的线程池。
        // newScheduledThreadPool还可以生成支持周期任务的线程池。
        // 一般用户场景下各种不同设置要求的线程池都可以这样生成，不用自己new一个线程池出来。
        ExecutorService executorService = Executors.newFixedThreadPool(7);//固定大小线程池
        try {
            // AbstractExecutorService任务提交的submit方法有三个实现。
            // 第一个接收一个Runnable的Task，没有执行结果；
            // 第二个是两个参数：一个任务，一个执行结果；
            // 第三个一个Callable，本身就包含执任务内容和执行结果。
            // submit方法的返回结果是Future类型，调用该接口定义的get方法即可获得执行结果
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("休眠1s");
                        try {

                        Thread.sleep(1000);
                        }catch (Exception e){

                        }

                    }
                    return "iiiiiiiii";
                }
            });
            //在ExecutorService接口中还定义了服务的关闭方法shutdown和shutdownNow方法，
            // 可以平缓或者立即关闭执行服务，实现该方法的子类根据自身特征支持该定义。
            System.out.println("get前开始休眠");
            executorService.shutdownNow();
            if (!future.isDone()) {
                String outputs = future.get();
            System.out.println("get后开始休眠");
            executorService.shutdown();

            System.out.println(outputs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
