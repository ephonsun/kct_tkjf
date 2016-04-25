package com.framework.web.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.core.utils.GlobalConfig;
import com.framework.core.utils.SHA256;

public class test_main {
	
	private static List<String> mobileList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		
		String mobile = null;
		
		if(mobileList.contains(mobile)){
			System.out.println("123");
		}else{
			System.out.println("456");
		}
		
		
		
		
		
		
		
		
		
//		int i = 0;
//		int a = ++i;
//		i++;
//		System.out.println(i);
//		
//		String password = SHA256.getSHA256CheckSum("123456", SHA256.SHA256KEY);
//		System.out.println(password);
		
		
//		String cost = "30";
//		double xfa = 118.0;
//		
//		double xfje = Double.valueOf(cost);
//		double a = Math.ceil(xfje * 0.3);
//		int xfb = 533;
//		int a = (int) (xfa * 0.3);
//		int b = (int) (xfb * 0.3);
//		System.out.println((int) a);
//		System.out.println(b);
		
		
//		String password = SHA256.getSHA256CheckSum("asd123123", SHA256.SHA256KEY);// 用户密码
//		System.out.println(password);
		//GlobalConfig.sendTemplateSms("18651900841", "JSM40152-0002","@1@=先生/女士,@2@=您好,@3@=123321");// 发送短信验证码

		// HashMap<String, Object> map = new HashMap<>();
		// map.put("a", "a");
		// map.put("b", "");
		// map.put("c", "c");
		// map.put("d", "d");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// //String rq = "2015-08-06 00:00:00";
		// Date date = sdf.parse( " 2015-09-10 10:20:00 " );
		// String s123 = "2015-08-06";
		// System.out.println(OtherUtils.format_date(s123));
		//// SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss
		// " );
		//// Date date = null;
		//// try {
		//// date = sdf.parse(rq);
		//// } catch (ParseException e) {
		//// e.printStackTrace();
		//// }
		// System.out.println(date);
		// System.out.println(date.getTime());

		// //---------时间戳转换为日期---------
		 long time=1435565096000L;
		 Date date=new Date(time);
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String str=format.format(date);
		 System.out.println(str);
		// //---------日期转化为时间戳---------
		////
		// //六月一号 1433088000 六月三十号 1435593600
		// //七月一号 1435680000 七月三十一号 1438272000
		// //---------时间戳转化为日期---------
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time1="2016-04-06 09:11:22";
		 Date date1 = format1.parse(time1);
		 System.out.print("Format To times:"+date1.getTime());
		//// //---------时间戳转化为日期---------
		 
		//--------正则匹配时间--------
//		String rex = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})";
//		Pattern p = Pattern.compile(rex);
//		Matcher m = p.matcher("作者:&nbsp;&nbsp;&nbsp;发布日期：2015-06-03 10:34:15&nbsp;&nbsp;&nbsp;查看次数：123 次");
//		while (m.find()) {
//			System.out.println(m.group(1).substring(0, 2));
//		System.out.println(m.group(1));
//		}
//		StringBuffer str = new StringBuffer();
//		String time = "07月21日 15:29";
//		str.append("2015-");
//		str.append(time.substring(0, 2)+"-");
//		str.append(time.substring(3, 5)+" ");
//		str.append(time.substring(7, 12)+":00");
//		System.out.println(time.substring(0, 2));
//		System.out.println(time.substring(3, 5));
//		System.out.println(time.substring(7, 12));
//		System.out.println(str.toString());

//		String test = "作者:&nbsp;&nbsp;&nbsp;发布日期：2015-06-03 10:34:15&nbsp;&nbsp;&nbsp;查看次数：123 次";
//		System.out.println(test);
//		System.out.println(test.replaceAll("</?(?!img|/?em)[^>]*>", ""));
		
		

//		String test = "234<strong>324</strong>324<em>32<a href=\"#\">4te</a>st1</em>2<img src=\"test.jpg\" />3";
//		System.out.println(test);
//		System.out.println(test.replaceAll("</?(?!img|/?em)[^>]*>", ""));

		// int num = 15;
		// int totalCount = 16;
		// int page = totalCount/num+1;
		// System.out.println(page);
		//
		// int totalPageNum = (totalCount + num - 1) / num;
		// System.out.println(totalPageNum);

		// String ss = "{pubtime=null, content=null, id=1, title=null,
		// updatetime=null, count=5, userid=1, comment=null, user=黄超}";
		// ArrayList<Object> list = new ArrayList<>();
		//
		// HashMap<String, Object> map = new HashMap<>();
		// map.put("pubtime", "1");
		// map.put("content", "1");
		// map.put("id", "1");
		// map.put("tags", list);
		//
		// JSONArray jsonObject = JSONArray.fromObject(map);
		// System.out.println(jsonObject);
		// createRandom(true,6);

	}
	
	
	
	static{
		mobileList.add("17712890502");
		mobileList.add("13921442298");
		mobileList.add("15861803616");
		mobileList.add("13951013145");
		mobileList.add("18651853778");
		mobileList.add("15195765664");
		mobileList.add("18651900841");
		mobileList.add("13913811274");
		mobileList.add("13101890263");
		mobileList.add("18018030255");
		mobileList.add("18915949520");
		mobileList.add("13951678531");
		mobileList.add("13770907966");
		mobileList.add("13901594101");
		mobileList.add("15996348677");
		mobileList.add("13952023906");
		mobileList.add("15950482223");
		mobileList.add("18663729227");
		mobileList.add("15950528829");
		mobileList.add("15305156065");
		mobileList.add("18652979831");
		mobileList.add("15150679890");
		mobileList.add("13952024607");
		mobileList.add("18751850888");
		mobileList.add("13770630190");
		mobileList.add("18120139953");
		mobileList.add("15195896998");
		mobileList.add("18061776658");
		mobileList.add("18118805899");
		mobileList.add("13776568944");
		mobileList.add("15261491948");
		mobileList.add("18705170213");
		mobileList.add("13951961181");
		mobileList.add("13584010107");
		mobileList.add("15094310204");
		mobileList.add("15950451813");
		mobileList.add("15951851145");
		mobileList.add("13611591327");
		mobileList.add("13912912535");
		mobileList.add("15605155389");
		mobileList.add("13776549818");
		mobileList.add("13770957911");
		mobileList.add("15261891455");
		mobileList.add("13357719149");
		mobileList.add("15996367628");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// public static HashMap<String, Object> mapNull(Map<String,?> args) {
	// HashMap<String, Object> map = null;
	// for(int i=0;i<args.size();i++){
	// args.get
	// }
	// return null;
	// }

	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);
		System.out.println(retStr);
		return retStr;
	}

}
