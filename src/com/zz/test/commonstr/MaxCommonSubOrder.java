package com.zz.test.commonstr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
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
	
	List<Integer> stra_sub_local_extend = new ArrayList<>();//对stra_sub_local 和strb_sub_local结果进行互补扩充的结果集，方便渲染界面；（即 第n个ture之前之后的false数量需要填充成一样，少的一方则用2表示，1表示相同，0表示不同，2表示该槽位比对方少，
	List<Integer> strb_sub_local_extend = new ArrayList<>();//
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

	public static void main(String[] args) throws IOException {
		//String a = "cnblogs";
		//String b = "belong";
		//String a = "cnblogsu7k9j00l";
		//String b = "belong231u144k555j4444l";
		//MaxCommonSubOrder mcso = new MaxCommonSubOrder(a, b);
		
		
		//System.out.println(mcso.getMCSO(a.length() - 1, b.length() - 1));
		
		//System.out.println(mcso.getMCSO_d());
		
		//mcso.getMCSO_str_d();
		
		
		//test
		
		String a1="/*以下为演示内容，请添加您自己的内容 ~_~ */\r\n"
				+ "\r\n"
				+ "html,\r\n"
				+ "body {\r\n"
				+ "  width: 100%;\r\n"
				+ "  height: 100%;\r\n"
				+ "  margin: 0;\r\n"
				+ "  padding: 0;\r\n"
				+ "  overflow: hidden;\r\n"
				+ "  font-family: 'Fira Mono', helvetica, arial, sans-serif;\r\n"
				+ "  font-weight: 400;\r\n"
				+ "  font-size: 62.5%;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#webgl-container {\r\n"
				+ "  width: 100%;\r\n"
				+ "  height: 100%;\r\n"
				+ "  cursor: pointer;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".loading {\r\n"
				+ "  position: absolute;\r\n"
				+ "  width: 100%;\r\n"
				+ "  height: 100%;\r\n"
				+ "  background-color: #000000;\r\n"
				+ "  opacity: 1;\r\n"
				+ "  -webkit-transition: opacity 1.2s ease-out;\r\n"
				+ "  -o-transition: opacity 1.2s ease-out;\r\n"
				+ "  transition: opacity 1.2s ease-out;\r\n"
				+ "  pointer-events: none;\r\n"
				+ "  z-index: 5;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".loading__progress {\r\n"
				+ "  position: absolute;\r\n"
				+ "  top: 50%;\r\n"
				+ "  left: 50%;\r\n"
				+ "  transfor66yym: translate(-50%, -50%);\r\n"
				+ "  text-align: center;\r\n"
				+ "  -webkit-transition: opacity 0.25s ease-out;\r\n"
				+ "  -o-transition: opacity 0.25s ease-out;\r\n"
				+ "  transition: opacity 0.25s ease-out;\r\n"
				+ "  opacity: 0;\r\n"
				+ "}";
		
		String b1="/*以下为演示内容，请添加您自己的内容 ^_^ */\r\n"
				+ "\r\n"
				+ "ht\nml,\r\n"
				+ "body {\r\n"
				+ "  width: 100%;\r\n"
				+ "  height: 100%;\r\n"
				+ "  margin: 0;\r\n"
				+ "  padding: 1;\r\n"
				+ "  over"
				+ "flow: hiddean;\r\n"
				+ "  font-family: 'Fira Mono', helvetica, arial, sans-serif;\r\n"
				+ "  font-weight: 400;\r\n"
				+ "  font-size: 62.0%;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#webgl-container {\r\n"
				+ "  width: 100%;\r\n"
				+ "  height: 100%;\r\n"
				+ "  cursor: pointer;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".loading {\r\n"
				+ "  position: absolute;\r\n"
				+ "  width: 100%;\r\n"
				+ "  height: 100%;\r\n"
				+ "  background-color: #0000fff;\r\n"
				+ "  opacity: 1;\r\n"
				+ "  -webkit-transition: opacity 1.2s ease-out;\r\n"
				+ "  -o-transition: opacity 1.2s ease-out;\r\n"
				+ "  transition: opacity 1.2s ease-out;\r\n"
				+ "  pointer-events: none;\r\n"
				+ "  z-index: 5;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".loading__progress {\r\n"
				+ "  position: absolute;\r\n"
				+ "  top: 80%;\r\n"
				+ "  left: 50%;\r\n"
				+ "  transform: translate(-50%, -50%);\r\n"
				+ "  text-align: center;\r\n"
				+ "  -webkit-transition: opacity 0.25s ease-out;\r\n"
				+ "  -o-transition: opacity 0.25s ease-out;\r\n"
				+ "  transition: opacity 0.25s ease-out;\r\n"
				+ "  opacity: 0;\r\n"
				+ "}";
		
		String a="  opacity: 0;\r\n"
				+ "}";
		
		String b="  opacity: 0;\r\n"
				+ "}";
		
		//测试1
		Map<String,String> map =MaxCommonSubOrder.getCompareResultHtml(a1, b1);
		String html_page = String.format(html, map.get("txtCompareResultLeft"),map.get("txtCompareResulRight"));
		File file = new File("/compare.html");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(html_page.getBytes());
		
		
		//测试2，扩充版
		
		Map<String,String> map1 =MaxCommonSubOrder.getCompareResultHtml_ex(a1, b1);
		String html_page1 = String.format(html, map1.get("txtCompareResultLeft"),map1.get("txtCompareResulRight"));
		File file1 = new File("/compare_ext.html");
		FileOutputStream fos1 = new FileOutputStream(file1);
		fos1.write(html_page1.getBytes());
	}
	
	
	
	//递归方式求解，最大公共长
	private  int getMCSO(int lena, int lenb) {
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
	
	//动态规划方式求解,最大公共长
		private  int getMCSO_d() {
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
		private  void getMCSO_str_d() {
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
			/*for(int i2=str_ab.size(); i2>0; i2--) {
				System.out.print(str_ab.get(i2-1));
			}*/
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
			
			
			//扩充对齐
			int e1 = 0;
			int e2 = 0;
			while(e1 < stra.length() || e2 < strb.length()) {
				int eLen1 = 0;
				int eLen2 = 0; //true与true之间的false 长度，需要对齐
				while((!stra_sub_local.get(e1) || !strb_sub_local.get(e2)) && (e1 < stra.length() || e2 < strb.length())) {//只有两者都是true才停止。
					if(!stra_sub_local.get(e1)) {//当a非公共时，插入0，且记录false数量 eLen1, 且false时下标e1++
						stra_sub_local_extend.add(0);
						e1++;
						eLen1++;
					}else {
						//stra_sub_local_extend.add(1);
					}
					
					if(!strb_sub_local.get(e2)) {
						strb_sub_local_extend.add(0);
						e2++;
						eLen2++;
					}else {
						//strb_sub_local_extend.add(1);
					}
					
					if(eLen1>eLen2) {//当对齐的数量不等，则相互补充。
						strb_sub_local_extend.add(2);
						e2++;
						eLen2++;
					}else if(eLen1<eLen2) {
						stra_sub_local_extend.add(2);
						e1++;
						eLen1++;
					}
				}
				
				//接下来，a,b 的false都对齐，且都为true，则赋值1
				stra_sub_local_extend.add(1);
				strb_sub_local_extend.add(1);
				e1++;
				e2++;
				
			}
			
			
		}
		
		/**
		 * 获取比较字符串 a,b 的渲染结果。
		 * @param stra
		 * @param strb
		 * @return
		 */
		public static Map<String, String> getCompareResultHtml(String stra, String strb) {
			MaxCommonSubOrder mcso = new MaxCommonSubOrder(stra, strb);
			mcso.getMCSO_str_d();
			Map<String, String> map = new HashMap<>();
			String a =  mcso.getLinesPreByStr(stra, mcso.stra_sub_local);
			String b = mcso.getLinesPreByStr(strb, mcso.strb_sub_local);
			map.put("txtCompareResultLeft", a);
			map.put("txtCompareResulRight", b);
			System.out.println("@@@@@@@@@@@@:");
			System.out.println(a);
			System.out.println("@@@@@@@@@@@@1:");
			System.out.println(b);
			return map;
		}
		
		/**
		 * 获取比较字符串 a,b 的渲染结果。(较于getCompareResultHtml ，可以对缺少字符的行也标色）
		 * @param stra
		 * @param strb
		 * @return
		 */
		public static Map<String, String> getCompareResultHtml_ex(String stra, String strb) {
			MaxCommonSubOrder mcso = new MaxCommonSubOrder(stra, strb);
			mcso.getMCSO_str_d();
			Map<String, String> map = new HashMap<>();
			String a =  mcso.getLinesPreByStr_Extend(stra, mcso.stra_sub_local_extend);
			String b = mcso.getLinesPreByStr_Extend(strb, mcso.strb_sub_local_extend);
			map.put("txtCompareResultLeft", a);
			map.put("txtCompareResulRight", b);
			System.out.println("@@@@@@@@@@@@:");
			System.out.println(a);
			System.out.println("@@@@@@@@@@@@1:");
			System.out.println(b);
			return map;
		}
		
		/**
		 * 按照换行符，分组；以及0 1标记的位，处理得到 html文本
		 * @param str
		 * @param strBitSet
		 * @return
		 */
		private String getLinesPreByStr(String str, BitSet strBitSet) {
			final String contentExtend = "<div class=\"CodeMirror-code\">%s</div>";//外部div
			 String content ="";//内容
			final String line = "<pre class=\"zz_margin_0\" style=\"%s\">%s</pre>";//行模式
			final String charRed = "<span style=\"color:red\">%s</span>";
			
			//默认行颜色
			String lineBackgroundColor = "background-color:#D7D4F0;";//灰色
			
			String [] strLines = str.split("\\n");//分割
			int charCount = 0;//记录下列字符遍历的位置。注意处理当\n 时非的之后
			
			
			boolean linexColorRedFlagNext = false;//下一行標紅標記
			for(int i=0; i<strLines.length; i++) {
				String lineTemp = "";//临时行内容
				
				boolean lineColorRedFlag = false; //行标红标记
				
				String unCommon = "";
				String lineBackgroundColorTemp = "";
				for(int j=0; j<strLines[i].length(); j++,charCount ++) {
					
					if(!strBitSet.get(charCount)) {//非公共需要加起来
						unCommon += strLines[i].charAt(j);
					}else {//公共的正常累加，
						if(!"".equals(unCommon)) {//上次非公共的不为空，则需要处理上次非公共的。且把非公共处理成空
							lineTemp += (String.format(charRed, unCommon));
							unCommon = "";
							lineColorRedFlag = true;
						}
						lineTemp += strLines[i].charAt(j);
					}
					
					
				}
				
				if(!"".equals(unCommon)) {//当都是非公共，则需要外面处理下
					lineTemp += (String.format(charRed, unCommon));
					unCommon = "";
					lineColorRedFlag = true;
				}
				
				if(linexColorRedFlagNext) {
					lineColorRedFlag = true;
					linexColorRedFlagNext = false;
				}
				if(!strBitSet.get(charCount) && charCount < str.length()) {//当换行符位置有不同，则其前后行都应该标红(由于子循环，最后会charCount++,所以此时
					lineColorRedFlag = true;
					linexColorRedFlagNext = true;
				}
				
				if(lineColorRedFlag) {//如果行有非同，则进行标记
					lineBackgroundColorTemp = lineBackgroundColor;
				}
				content += String.format(line, lineBackgroundColorTemp,lineTemp);
				
				charCount += (charCount==(str.length())?0:1);//charCount ++中已经加上换行符。此处加是为了遍历到下一行开头
				
			}
			
			return String.format(contentExtend, content);
		}
		
		/**
		 * 针对getLinesPreByStr 扩充版本，根据扩充对齐的结果，进行渲染，结果更加符合比对逻辑
		 * @param str
		 * @param stra_sub_local_extend2 
		 * @param strBitSet
		 * @return
		 */
		private String getLinesPreByStr_Extend(String str, List<Integer> str_extend) {
			final String contentExtend = "<div class=\"CodeMirror-code\">%s</div>";//外部div
			 String content ="";//内容
			final String line = "<pre class=\"zz_margin_0\" style=\"%s\">%s</pre>";//行模式
			final String charRed = "<span style=\"color:red\">%s</span>";
			
			//默认行颜色
			String lineBackgroundColor = "background-color:#D7D4F0;";//灰色
			
			int i = 0;//记录下标，
			int iReal = 0;//记录真实的字符串下标，即排除str_extend中值=2的。
			
			boolean readlineRedFlag = false;//行标红标记
			String lineStrTemp = "";//行临时容器
			String unCommon = "";//非公共的字符串，应该排在一起，一起进行标红，避免过多使用<span>
			while(i < str_extend.size()) {

				//0,2则当前行需要标红
				if(str_extend.get(i) == 0) {
					readlineRedFlag = true;
				}else if(str_extend.get(i) == 2) {
					readlineRedFlag = true;
				}
				
				if(str_extend.get(i) != 2) {//当不为补充字符时
					if(str.charAt(iReal) != '\n') {//当不为换行时候
						
						if(str_extend.get(i) == 0) {//当是非公共的时候，即不同
							unCommon += str.charAt(iReal);
						}else {//当是公共的时候，先处理之前暂存的非公共的字串
							if(!"".equals(unCommon)) {
								lineStrTemp += (String.format(charRed, unCommon));
								unCommon = "";
							}
							lineStrTemp += str.charAt(iReal);
						}
						
						//lineStrTemp += str.charAt(iReal);
						
					}else {//换行则截断，组装出line
						
						//有可能在截断时候，之前都是非公共的，此时需要把unCommon赋值给lineStrTemp
						if(!"".equals(unCommon)) {
							lineStrTemp += (String.format(charRed, unCommon));
							unCommon = "";
						}
						
						content += String.format(line, readlineRedFlag ? lineBackgroundColor : "", lineStrTemp);
						lineStrTemp = "";
						readlineRedFlag = false;
						
					}
					
					iReal++;//不为2则真实的字符串下标才移动
				}
				
				i++;
			}
			return String.format(contentExtend, content);
		}
		
		
		private final static String html="<html>\r\n"
				+ "<body>\r\n"
				+ "<div class=\"container\">\r\n"
				+ "<div style=\"width:600px;float:left;\" class=\"div-inline\">\r\n"
				+ "%s\r\n"
				+ "</div>\r\n"
				+ "<div style=\"width:600px;float: right;\" class=\"div-inline\">\r\n"
				+ "%s\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "</div>\r\n"
				+ "<style>\r\n"
				+ ".div-inline{ display:inline} \r\n"
				+ ".container{whidth:1200px}\r\n"
				+ ".div-inline{border:1px solid blue}\r\n"
				+".zz_margin_0{margin:0;}\r\n"
				+ "\r\n"
				+ "</style>\r\n"
				+ "</body>\r\n"
				+ "</html>";
}
