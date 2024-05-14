package org.aman.epiclifesteal.utils;


import java.util.concurrent.CompletableFuture;
import org.aman.epiclifesteal.EpicLifesteal;
import org.bukkit.Bukkit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Threads {
    private static final EpicLifesteal plugin = EpicLifesteal.getInstance();
    private static ExecutorService executor;
    public static void load(){
        executor = Executors.newFixedThreadPool(
                Math.max(2, Bukkit.getMaxPlayers()/10));
    }
    private static boolean shutdown = false;
    public static void executeDataInternal(Runnable runnable) {
        if (shutdown) {
            return;
        }
        executor.execute(runnable);
    }
    public static <T> T getDataInternal(RunnableReturn<T> runnable){
        if (shutdown) {
            return null;
        }
        return CompletableFuture.supplyAsync(runnable::run, executor).join();
    }
    public static void close() {
        try {
            shutdown = true;
            if(executor!=null)
                executor.shutdown();
            plugin.getLogger().info("Shutting down database executor now...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public interface RunnableReturn<T>{
        T run();
    }
}

