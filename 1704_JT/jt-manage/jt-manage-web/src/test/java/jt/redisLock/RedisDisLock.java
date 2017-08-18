package jt.redisLock;

import redis.clients.jedis.Jedis;

public class RedisDisLock {
	private static final long expired = 1000;//1秒超时  
    //上锁  
    public static boolean acquireLock(Jedis jedis,String lock) {  
        // 1. 通过SETNX试图获取一个lock  
        boolean success = false;  
        long value = System.currentTimeMillis() + expired + 1;        
        long acquired = jedis.setnx(lock, String.valueOf(value));  
        jedis.expire(lock, 1);//设置1秒超时
        //SETNX成功，则成功获取一个锁 
        if (acquired == 1)  success = true;
        //SETNX失败，说明锁被其他客户端保持，检查其是否已经超时
        /*else { 
            long oldValue = Long.valueOf(jedis.get(lock));          
            if (oldValue < System.currentTimeMillis()) {//超时 
                //获取上一个锁到期时间，并设置现在的锁到期时间， 
                //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的 
                String getValue = jedis.getSet(lock, String.valueOf(value)); 
                if (getValue !=null) { 
                    if (Long.valueOf(getValue) == oldValue)  
                        success = true;  
                    else success = false;// 已被其他进程捷足先登了 
                }             
            }else //未超时，则直接返回失败 
                success = false; 
        }        */  
        return success;        
    }  
       
    //释放锁  
    public static void releaseLock(Jedis jedis,String lock) {      
        //long current = System.currentTimeMillis();         
        // 避免删除非自己获取得到的锁  
        //if (current < Long.valueOf(jedis.get(lock)))  
            jedis.del(lock);   
    }  
}
