package com.demo.util;

class RandomIntGenerator
{
    /**
     * 最小值
     */
    private int min = Integer.MIN_VALUE;
    /**
     * 最大值
     */
    private int max = Integer.MAX_VALUE;


    /**
     * 大于min 小于max
     * @param min
     * @param max
     */
    public RandomIntGenerator(int min, int max)
    {
        this.min = min;
        this.max = max;
    }
    /**
     * 大于min 小于max
     * @param min
     * @param max
     */
    public static RandomIntGenerator between(int min, int max)
    {
        return new RandomIntGenerator(min, max);
    }
    /**
     * 大于min 小于Integer.MAX_VALUE
     */
    public static RandomIntGenerator biggerThan(int min)
    {
        return new RandomIntGenerator(min, Integer.MAX_VALUE);
    }

    /**
     * 大于Integer.MIN_VALUE 小于max
     */
    public static RandomIntGenerator smallerThan(int max) {
        return new RandomIntGenerator(Integer.MIN_VALUE, max);


    }
}
