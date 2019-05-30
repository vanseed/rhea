package com.vanseed.rhea.api.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonTest {

	public static void main(String[] args) 
	{
		try{
//			String aa = "0100";
//			int d = Integer.parseInt(aa, 2); // 2进制
//			int o = Integer.parseInt(aa, 8); // 8进制
//			System.out.println(d);
//			System.out.println(o);
//			
//			long coinNumber = 69l;
//			int milestone = Math.floor(coinNumber%10l)>0? (2*(int)Math.floor(coinNumber/10l))+1:2*((int)Math.floor(coinNumber/10l));
//			System.out.println(milestone);
//			
//			Date firstDate = DateUtils.getFirstDayOfMonth(new Date()); 
//			String dataStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(firstDate);
//			System.out.println( dataStr );
//			
//			System.out.println( DateUtils.format( DateUtils.getFirstDayOfMonth(new Date()), "yyyy-MM-dd HH:mm:ss" ));
//			String plainText = "110100,lottery"; //MTEwMTAwLHNpZ251cA
//			String cipherText = "";
//			byte[] a = Base64.encodeBase64URLSafe(plainText.getBytes());
//			//cipherText = EncryptUtils.encryptBASE64(plainText.getBytes());
//			cipherText =  new String(a, "UTF-8");
//			System.out.println("===="+cipherText);
//			
//			byte[] b = Base64.decodeBase64(cipherText.getBytes("UTF-8")); 
//			//byte[] b = EncryptUtils.decryptBASE64(cipherText);
//			String decodeData =  new String(b, "UTF-8");
//			System.out.println("===="+decodeData);
//			
//			int[] arr = new int[]{8,5,1,3,0,2,9};
//			int[] index =  new int[]{2,3,0,2,4,6,5,4,2,4,1};
//			String myTel = "";
//			for(int i:index ){
//				myTel += arr[i];
//			}
//			System.out.println(myTel);
//			
//			
//			String radomCode ="";
//			Random random = new Random();
//			for(int i=0; i<6; i++){
//				radomCode += String.valueOf(random.nextInt(10));
//			}
//			System.out.println(radomCode);
//			System.out.println("[城市]兼职_[城市]招聘兼职_[城市]兼职信息_窝客网".replaceAll("[\\[][城][市][\\]]", "北京"));
//			int k=13;
//			int i=10;
//			System.out.println((Double)Math.ceil(Double.valueOf(k)/Double.valueOf(i)));
			
			ObjectMapper jsonMapper = new ObjectMapper();
			String jsonStr = "[{\"id\":\"1\",\"num\":\"222222\"},{\"id\":\"2\",\"num\":\"1380000\"}]";
			
			List<Map<String,String>> listTest = jsonMapper.readValue(jsonStr, new TypeReference<List<Map<String,String>>>(){} );
			//Map<String,Object> values=jsonMapper.readValue(jsonStr,new TypeReference<Map<String,Object>>(){
		    
			//new ObjectMapper().readValue(content, valueType)
			System.out.println(listTest.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
