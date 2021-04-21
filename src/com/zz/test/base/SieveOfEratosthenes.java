package com.zz.test.base;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.function.IntPredicate;


/**
 * 
 * <note>
 * Desc： 埃拉托色尼筛选法,选出指定范围内素数，很多 编程语言可以通过它测试位操作性能；
@opr:
（1）先把1删除（现今数学界1既不是质数也不是合数）
（2）读取队列中当前最小的数2，然后把2的倍数删去
（3）读取队列中当前最小的数3，然后把3的倍数删去
（4）读取队列中当前最小的数5，然后把5的倍数删去
（5）读取队列中当前最小的数7，然后把7的倍数删去
（6）如上所述直到需求的范围内所有的数均删除或读取
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-11-13 16:32:48
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-11-13 16:32:48    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class SieveOfEratosthenes {
	public static void main(String[] args) {
			/**
			 * 1、loop:2-n/2 item
			 * 2、每个item while 
			 */
		Long start = System.currentTimeMillis();
		SieveOfEratosthenes.getSieveOfErat(0, 200_0000);//.forEach(System.out::println);;
		Long end = System.currentTimeMillis();
		
		System.out.println("time:" + (end-start));
	}
	

	/**
	 * @desc:得到start，end间素数（质数）
	 * @param start
	 * @param end
	 * @return
	 */
	public static List getSieveOfErat(int start, int end) {

		int testNum = end + 1;	
		BitSet slot = new BitSet(testNum);
		
		//初始值1
		for(int i = 0; i< testNum + 1; i++) {
			slot.set(i);
		}
		
		//循环1/2即可（为啥是小于平方根A：因为当i*j如果被clear，当i>A,而且二者乘积i*j<testSum,发现之前i=j的时候，也穷举了该情况，所以i*i < testNum即可
		for(int i = (start<2 ? 2 : start); i*i < testNum; i ++){
			if(!slot.get(i)) continue;
			int muti = i;
			int j = 2;
			while(muti * j <= testNum) {
				slot.clear(muti * j);
				j ++;
			}
		}
		
		List<Integer> list = new ArrayList<>();
		//输出
		for(int i = 2; i< testNum + 1; i++) {
			if(slot.get(i)) {
				list.add(i);
			}
			
		}
		
		return list;
	}
}
