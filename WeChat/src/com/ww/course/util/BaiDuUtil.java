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
	 * �������ֲ���
	 * 
	 * @param inputStream �ٶ���������API���ص�������
	 * @return Music
	 */
	@SuppressWarnings("unchecked")
	public static Music parseMusic(InputStream inputStream) {
		Music music = null;
		try {
			// ʹ��dom4j����xml�ַ���
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// �õ�xml��Ԫ��
			Element root = document.getRootElement();
			// count��ʾ�������ĸ�����
			String count = root.element("count").getText();
			// ���������ĸ���������0ʱ
			if (!"0".equals(count)) {
				// ��ͨƷ��
				List<Element> urlList = root.elements("url");
				// ��Ʒ��
				List<Element> durlList = root.elements("durl");

				// ��ͨƷ�ʵ�encode��decode
				String urlEncode = urlList.get(0).element("encode").getText();
				String urlDecode = urlList.get(0).element("decode").getText();
				// ��ͨƷ�����ֵ�URL
				String url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode;
				if (-1 != urlDecode.lastIndexOf("&"))
					url = urlEncode.substring(0, urlEncode.lastIndexOf("/") + 1) + urlDecode.substring(0, urlDecode.lastIndexOf("&"));

				// Ĭ������£����������ֵ�URL ���� ��ͨƷ�����ֵ�URL
				String durl = url;

				// �жϸ�Ʒ�ʽڵ��Ƿ����
				Element durlElement = durlList.get(0).element("encode");
				if (null != durlElement) {
					// ��Ʒ�ʵ�encode��decode
					String durlEncode = durlList.get(0).element("encode").getText();
					String durlDecode = durlList.get(0).element("decode").getText();
					// ��Ʒ�����ֵ�URL
					durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode;
					if (-1 != durlDecode.lastIndexOf("&"))
						durl = durlEncode.substring(0, durlEncode.lastIndexOf("/") + 1) + durlDecode.substring(0, durlDecode.lastIndexOf("&"));
				}
				music = new Music();
				// ������ͨƷ����������
				music.setMusicUrl(url);
				// ���ø�Ʒ����������
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
//			//���xml�ĸ�Ԫ��
//			Element root=document.getRootElement();
////			String count=root.element("results").getTextTrim();
//		    List<Element> rootChildren = null;
//	        rootChildren = root.elements();
//			System.out.println(rootChildren);
//			// �õ���Ԫ�ص������ӽڵ�
//			List<Element> elementList = root.elements();
//
//			// ���������ӽڵ�
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
		 //2. �� Dom4j ��, xml �ĵ���Ӧ Document ����
		  
		  //a. �õ� xml �ĵ������ Document ����
		  		Document document = null;
		  
		        SAXReader reader = new SAXReader();
		        try {
					document = reader.read(inputStream);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
		       
		        //System.out.println(document);
		       
		        //b. �õ� xml �ĵ��ĸ�Ԫ��
		        Element root =document.getRootElement ();
		        String rootName = root.getName();
		        //System.out.println("rootName: " + rootName);
		        System.out.println("<" + rootName + ">");
		       
		        //c. �õ������������ӽڵ�
		        List<Element> rootChildren = null;
		        rootChildren = root.elements();
		        System.out.println("rootChildren.size: " + rootChildren.size());
		       
		        //d. �����е��ӽڵ���б���
		        for(int i = 3; i < rootChildren.size(); i++){
		         Element customerEle = rootChildren.get(i);
		         //System.out.println(customerEle.getName());
		         System.out.println("\t<" + customerEle.getName() + ">");
		         
		         Iterator<Element> it = customerEle.elementIterator();
		         while(it.hasNext()){
		          Element ele = it.next();
		          //System.out.println(ele.getName());
		          //�õ�Ԫ�ص�����
		          String eleName = ele.getName();
		          //�õ�Ԫ�ص��ı�ֵ
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
