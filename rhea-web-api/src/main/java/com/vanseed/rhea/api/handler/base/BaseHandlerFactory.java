package com.vanseed.rhea.api.handler.base;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.vanseed.rhea.common.enums.ClientType;
import com.vanseed.rhea.common.mvc.MRequestHeader;
import com.vanseed.saturn.core.mvc.IHandler;
import com.vanseed.saturn.core.mvc.IHandlerFactory;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.vanseed.saturn.core.mvc.IRequest;

/**
 * 
 * @author leon
 * @date 2019/04/29
 */
@Component("baseHandlerFactory")
public class BaseHandlerFactory implements IHandlerFactory<IHandler> {

	private static Map<String, TreeMap<String, IHandler>> handlerMap = new ConcurrentHashMap<>();

	protected Comparator<String> versionComparator= new Comparator<String>(){
		public int compare(String s1, String s2) {          
	        if(s1.equalsIgnoreCase(s2)){
	        	return 0;
	        }else{
	        	return s2.compareToIgnoreCase(s1);  
	        }	        
		} 
	};

	public Boolean isLatestHandler(MRequestHeader mHeader){
		if(mHeader!=null && mHeader.getClientType()!=null && ClientType.APP.getIndex() == mHeader.getClientType() ){
			return false;
		}else{
			return true;
		}
	}

	public Boolean isOldestHandler(MRequestHeader mHeader){
		return false;
	}

	@Override
	public IHandler getHandler(IRequest mRequest){
		if(mRequest==null){
			return null;
		}

		MRequestHeader mHeader = (MRequestHeader) mRequest.getRequestHeader();
		SortedMap<String, IHandler> versionMap = handlerMap.get(mHeader.getProtocolCode());

		if(!StringUtils.isEmpty(mHeader.getProtocolVersion())){ //按照指定接口版本查找
			return versionMap.get( mHeader.getProtocolVersion() );
		}
		else if(isLatestHandler(mHeader)){//取最新的
			return versionMap.get( versionMap.firstKey() );
		}
		else if(isOldestHandler(mHeader)){ //取最旧的
			return versionMap.get( versionMap.lastKey() );
		}
		else if(!StringUtils.isEmpty(mHeader.getClientVersion())){//通过客户端版本查找
			for(Map.Entry<String, IHandler> entry : versionMap.entrySet()){
				if( versionComparator.compare(entry.getKey(), mHeader.getClientVersion()) <=0 ){
					return entry.getValue();
				}
			}
			return versionMap.get( versionMap.lastKey() );
		}
		else{
			return null;
		}
	}

	public synchronized TreeMap<String, IHandler> putHandler(List<? extends IHandler> list){
		if(list != null && list.size()>0){
			TreeMap<String, IHandler> tMap = new TreeMap<>();
			String protocol = "";
			for (IHandler handler:list) {
				tMap.put(handler.getVersion(), handler);
				protocol = handler.getProtocol();
			}
			handlerMap.put(protocol, tMap);
			return tMap;
		}else {
			return null;
		}
	}

	public Map<String, TreeMap<String, IHandler>> getHandlerMap(){
		return handlerMap;
	}
}
