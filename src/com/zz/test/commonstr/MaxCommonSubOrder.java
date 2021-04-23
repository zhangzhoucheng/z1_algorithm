package com.zz.test.commonstr;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

/**
 * 最长公共子序列
 * @author jldzz
 *@ref: https://www.cnblogs.com/kaituorensheng/archive/2013/03/31/2992319.html
 */
public class MaxCommonSubOrder {
	private String stra;
	private String strb;
	
	List<String> strab_common = new ArrayList<>();//公共字符串

	BitSet stra_sub_local = null;//存储字符a 的下标是否为公共，默认0表示非公共，1表示公共
	BitSet strb_sub_local = null;
	
	//动态规划的二维数组
	int [][] str_two = null;
	
	
	//动态规划的 拐法记录数组
	int[][] str_two_tag;

	//公共字符
	List<Character> str_ab = new ArrayList<>();
	
	
	public MaxCommonSubOrder() {
		
	}
	
	public MaxCommonSubOrder(String stra, String strb) {
		this.stra = stra;
		this.strb = strb;
		this.stra_sub_local = new BitSet(stra.length());
		this.strb_sub_local = new BitSet(strb.length());
		this.str_two = new int[this.stra.length() + 1][this.strb.length() + 1];
		
		str_two_tag = new int[this.stra.length() + 1][this.strb.length() + 1];
	}

	public static void main(String[] args) {
		//String a = "cnblogs";
		//String b = "belong";
		String a = "cnblogsu7k9j00l";
		String b = "belong231u144k555j4444l";
		MaxCommonSubOrder mcso = new MaxCommonSubOrder(a, b);
		
		
		//System.out.println(mcso.getMCSO(a.length() - 1, b.length() - 1));
		
		//System.out.println(mcso.getMCSO_d());
		
		mcso.getMCSO_str_d();
	}
	
	
	
	//递归方式求解
	public  int getMCSO(int lena, int lenb) {
		if(lena == -1 || lenb == -1) {
			return 0;
		}
		
		if(stra.charAt(lena) == strb.charAt(lenb)) {

			return 1 + getMCSO(lena - 1, lenb - 1);
		}else {
			if(getMCSO(lena - 1, lenb) >= getMCSO(lena, lenb -1)) {
				
				
				return getMCSO(lena - 1, lenb);
			}else {
				
				return getMCSO(lena, lenb -1);
			}
			
		}
	}
	
	//动态规划方式求解
		public  int getMCSO_d() {
			int i = 0;
			int j = 0;
			for(; i<stra.length(); i++) {
				
				for(j=0; j<strb.length(); j++) {
					if(stra.charAt(i) == strb.charAt(j)) {
						str_two[i+1][j+1] = str_two[i][j] + 1;
					}else {
						if(str_two[i+1][j] >= str_two[i][j+1]) {
							str_two[i+1][j+1] = str_two[i+1][j];
						
						}else {
							str_two[i+1][j+1] = str_two[i][j+1];
						}
					}
					
				}
			}
			
			
			return str_two[i][j];
		}
		
		//动态规划求解最长公共子序列结果串，下标等
		public  void getMCSO_str_d() {
			int i = 0;
			int j = 0;
			
			//记录左拐，上拐路线，
			for(; i<stra.length(); i++) {
				
				for(j=0; j<strb.length(); j++) {
					if(stra.charAt(i) == strb.charAt(j)) {
						str_two[i+1][j+1] = str_two[i][j] + 1;//记录 动态规划的容器
						str_two_tag[i+1][j+1]=2;//后续遍历需要左上拐
					}else {
						if(str_two[i+1][j] >= str_two[i][j+1]) {
							str_two[i+1][j+1] = str_two[i+1][j];
							str_two_tag[i+1][j+1] = 1;//后续遍历需要左拐
						
						}else {
							str_two[i+1][j+1] = str_two[i][j+1];
							str_two_tag[i+1][j+1] = -1;//后续遍历需要上拐
						}
					}
					
				}
			}
			
			
			System.out.println("动态规划标记值为：");
			/*for(int k=1; k<str_two_tag.length; k++) {
				
				for(int m=1; m<str_two_tag[k].length; m++) {
					System.out.print(str_two_tag[k][m]);
				}
				System.out.println();
			}*/
			
			
			//取出路线值
			int i1 = stra.length();
			int j1 = strb.length();
			while(i1!=0 && j1!=0) {
				if(str_two_tag[i1][j1] == 2) {
					stra_sub_local.set(i1-1);
					strb_sub_local.set(j1-1);
					
					str_ab.add(stra.charAt(i1-1));
					
					i1--;
					j1--;
				}else {
					if(str_two_tag[i1][j1] == -1) {
						i1 --;
						
					}else if(str_two_tag[i1][j1] == 1){
						j1--;
					}
				}
			}
			
			//公共字符长度
			System.out.println("公共长度："+str_ab.size());
			
			//输出公共字符
			System.out.print("公共字符：");
			for(int i2=str_ab.size(); i2>0; i2--) {
				System.out.print(str_ab.get(i2-1));
			}
			System.out.println();
			
			
			//输出a，b字符 下标 ，1是公共部分，0是不公共部分
			
			System.out.print("a字符:");
			for(int i2=0; i2<stra.length(); i2++) {
				System.out.print(stra_sub_local.get(i2)+",");
			}
			System.out.println();
			
			
			System.out.print("b字符:");
			for(int i3=0; i3<strb.length(); i3++) {
				System.out.print(strb_sub_local.get(i3)+",");
			}
			
		}
}
