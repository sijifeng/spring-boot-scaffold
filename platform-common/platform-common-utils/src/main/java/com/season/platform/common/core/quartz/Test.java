package com.season.platform.common.core.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by yingchun on 2017/7/19.
 */
public class Test {

    public static void main(String[] args) throws SchedulerException {
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        System.out.println(scheduler);


        // Grab the Scheduler instance from the Factory
        Scheduler scheduler1 = StdSchedulerFactory.getDefaultScheduler();
        System.out.println(scheduler1);


        System.out.println(scheduler == scheduler1);
    }
}
