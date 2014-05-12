package com.ww.course.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ww.entity.Place;
import com.ww.message.resp.Music;

public class BaiDuUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	
	/**
	 * 解析音乐参数
	 * 
	 * @param inputStream 百度音乐搜索API返回的输入流
	 * @return Music
	 */
	@SuppressWarnings("unchecked")
	public static Music parseMusic(InputStream inputStream) {
		Music music = null;
		try {
			// 使用dom4j解析xml字符串
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// count表示搜索到的歌曲数
			String count = root.element("count").getText();
			// 当搜索到的歌曲数大于0时
			if (!"0".equals(count)) {
				// 普通品质
				List<Element> urlList = root.elements("url");
				// 高品质
				List<Element> durlList = root.elements("durl");

				// 普通品质的encode、decode
				String urlEncode = urlList.get(0).element("encode").getText();
				String urlDecode = urlList.get(0).element("decode").getText();
				// 普通品质音乐的URL
				String url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode;
				if (-1 != urlDecode.lastIndexOf("&"))
					url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode.substring(0, urlDecode.lastIndexOf("&"));

				// 默认情况下，高音质音乐的URL 等于 普通品质音乐的URL
				String durl = url;

				// 判断高品质节点是否存在
				Element durlElement = durlList.get(0).element("encode");
				if (null != durlElement) {
					// 高品质的encode、decode
					String durlEncode = durlList.get(0).element("encode").getText();
					String durlDecode = durlList.get(0).element("decode").getText();
					// 高品质音乐的URL
					durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode;
					if (-1 != durlDecode.lastIndexOf("&"))
						durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode.substring(0, durlDecode.lastIndexOf("&"));
				}
				music = new Music();
				// 设置普通品质音乐链接
				music.setMusicUrl(url);
				// 设置高品质音乐链接
				music.setHQMusicUrl(durl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return music;
	}

	public static List<Place> pasePlaces(InputStream inputStream){
		
		List<Place> lPlaces=null;
		
//		try {
//			SAXReader sax=new SAXReader();
//			Document document= sax.read(inputStream);
//			//获得xml的根元素
//			Element root=document.getRootElement();
////			String count=root.element("results").getTextTrim();
//		    List<Element> rootChildren = null;
//	        rootChildren = root.elements();
//			System.out.println(rootChildren);
//			// 得到根元素的所有子节点
//			List<Element> elementList = root.elements();
//
//			// 遍历所有子节点
//			for (Element e : elementList){
//				//map.put(e.getName(), e.getText());
//				//e.getName()
//			System.out.println(e.getName()+":"+e.getText());
//				
//			}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		
//		
		 //2. 在 Dom4j 中, xml 文档对应 Document 对象
		  
		  //a. 得到 xml 文档代表的 Document 对象
		  		Document document = null;
		  
		        SAXReader reader = new SAXReader();
		        try {
					document = reader.read(inputStream);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
		       
		        //System.out.println(document);
		       
		        //b. 得到 xml 文档的根元素
		        Element root =document.getRootElement ();
		        String rootName = root.getName();
		        //System.out.println("rootName: " + rootName);
		        System.out.println("<" + rootName + ">");
		       
		        //c. 得到根结点的所有子节点
		        List<Element> rootChildren = null;
		        rootChildren = root.elements();
		        System.out.println("rootChildren.size: " + rootChildren.size());
		       
		        //d. 对所有的子节点进行遍历
		        for(int i = 3; i < rootChildren.size(); i++){
		         Element customerEle = rootChildren.get(i);
		         //System.out.println(customerEle.getName());
		         System.out.println("\t<" + customerEle.getName() + ">");
		         
		         Iterator<Element> it = customerEle.elementIterator();
		         while(it.hasNext()){
		          Element ele = it.next();
		          //System.out.println(ele.getName());
		          //得到元素的名字
		          String eleName = ele.getName();
		          //得到元素的文本值
		          String eleText = ele.getText();
		          
		          System.out.println("\t\t<" + eleName + ">" +
		            eleText +
		            "</" + eleName + ">");
		         }
		         
		         System.out.println("\t</" + customerEle.getName() + ">");
		        }
		       
		        System.out.println("</" + rootName + ">");
		return null;
		
		
	}
	
	
}
