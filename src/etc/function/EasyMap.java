package etc.function;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EasyMap {
	private ArrayList<String> keys = new ArrayList<String>();
    private ArrayList<String> attributes = new ArrayList<String>();
    private ArrayList<String> types = new ArrayList<String>();
    private ArrayList<String> parentsFriend = new ArrayList<String>();
    private String parents="";

    
    
    public void clearbackslash(){
        StringBuffer tempKey = new StringBuffer("");
        StringBuffer tempAttribute = new StringBuffer("");

        for(int i=0; i<size(); i++){
            for(int t =0; t<keys.get(i).length(); t++){
                if(!(keys.get(i).charAt(t)=='\\')){
                    tempKey.append(keys.get(i).charAt(t));
                }
            }
            for(int t =0; t<attributes.get(i).length(); t++){
                if(!(attributes.get(i).charAt(t)=='\\')){
                    tempAttribute.append(attributes.get(i).charAt(t));
                }
            }
            keys.set(i,tempKey.toString());
            attributes.set(i,tempAttribute.toString());
            tempKey=new StringBuffer("");
            tempAttribute = new StringBuffer("");
        }
    }
    public String getKey (String attribute) {
        int position=0;
        for(int i=0; i<keys.size(); i++){
            if(keys.get(i).equals(attribute))
            {
                position=i;
                break;
            }
        }
        return getKey(position);
    }
    public String getKey(int position){
        return keys.get(position);
    }
    public void setKey(int position,String keyContext) { keys.set(position, keyContext);}
    public String getParentsFriend(int position) { return parentsFriend.get(position);}
    public void setParentsFriend(ArrayList<String> parentsFriend) { this.parentsFriend=parentsFriend; }
    public ArrayList<String> getParentsFriendList() {return parentsFriend;}
    
    public String getAttribute(int position) {return attributes.get(position);}
    public ArrayList<String> getAttributeList(){return attributes;}
    public void setParents(String parents) {
        this.parents =parents;
    }
    public String getParents() { return  this.parents;}
    public boolean empty() {
        int count=0;
        boolean result =false;

        if(keys.size()!=0)
            result=true;

        return result; //true 빈값false 안빈값
    }
    public int size() {
        int size=0;

        size=keys.size();
        return size;
    }
    public void put(String key,String attribute,String type) {
        keys.add(key);
        attributes.add(attribute);
        types.add(type);
    }
    public void put(Map<String,String> map) {
        Set<Map.Entry<String, String>> values = map.entrySet();
        // 반복자 생성
        Iterator<Map.Entry<String, String>> it = values.iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();

            //String.format("%s : &s", key,value);
            keys.add(key);
            attributes.add(value);
            types.add(ConfirmType(value));
        }
    }

    public String get(String key) {
        int position=0;
        String result="";
        for(int o =0; o<keys.size(); o++)
        {

            if(keys.get(o).equals(key)) {
                result=attributes.get(o);
                break;
            }
            else
                result="null";
        }

        return result;

    }
    public String getType(String key) {
        int position=0;
        String result="";
        for(int o =0; o<keys.size(); o++)
        {
            if(keys.get(o).equals(key))
                position=o;
            else
                position=-1;
        }

        if(position==-1)
            result="null";
        else
            result=types.get(position);

        return result;

    }
    public String getType(int position) {  //객체나 객체들이면 쪼갤려고 반환용
        if(position<types.size())
            return types.get(position);
        else
            return null;
    }
    public ArrayList<String> keyList(){ //검색할 키값들 보는용
        return keys;
    }
    public ArrayList<String> attrubuteList(){ //친구들 반환용
        return attributes;
    }
    public String toStringAddType() {
        StringBuffer result = new StringBuffer("");
        for(int index=0; index<keys.size(); index++) {

            result.append(String.format("%s : %s  type=%s \n", keys.get(index),attributes.get(index),types.get(index)));
        }

        return result.toString();
    }
    public String toString() {
        StringBuffer result = new StringBuffer("");
        result.append("부모:"+parents);
        result.append("한맵의 시작----------------------------------------------------------------->\n");
        for(int index=0; index<keys.size(); index++) {

            result.append(String.format("%s : %s  \n", keys.get(index),attributes.get(index)));
        }
        result.append("한맵의 끝----------------------------------------------------------------->\n");
        return result.toString();
    }

    public String ConfirmType(String type){
        String result="";
        String stringOn ="false";
        boolean string =false;
        boolean Double =false;
        int doubleCheck=0;
        String doubleOn ="false";
        ArrayList<String> check = new ArrayList<String>();
        for(int i=0; i<type.length(); i++){
            System.out.println("현재 char:"+type.charAt(i));
            if(type.charAt(i)=='0'|| type.charAt(i)=='1' || type.charAt(i)=='2' ||
                    type.charAt(i)=='3' || type.charAt(i)=='4' || type.charAt(i)=='5' ||
                    type.charAt(i)=='6' || type.charAt(i)=='7' || type.charAt(i)=='8' ||
                    type.charAt(i)=='9' )
            {

                stringOn ="false";
                check.add(stringOn);
            }
            else {

                stringOn ="true";
                check.add(stringOn);
            }

            if(type.charAt(i)=='.')
            {

                doubleOn="true";
            }

        }

        for(int k=0; k<check.size(); k++) {

            if(check.get(k).equals("true"))
            {

                doubleCheck++;
            }
        }

        if(doubleCheck >1) {
            string=true;

        }

        if(doubleCheck ==1) {
            if(doubleOn.equals("true")) {
                Double=true;

            } else
                string=true;
        }



        if(!string && Double) {

            result="Double";

        }

        if(string && !Double) {

            result="String";

        }

        if(!string && !Double) {

            result="Int";
            //System.out.println("현재문자:"+type+"  타입:"+result);
        }

        return result;
    }
    
    public String JSONObject() {  //부모가 json.response.body.items 급이면 이제 배열로 묶어 배출가능 극 객체들의 배열이면 가능
    	StringBuffer result=new StringBuffer("");
    	StringBuffer item=new StringBuffer("");
    	result.append("{");
    	
    	for(int index=0; index<size(); index++) {
    		
    		item.append("\"");
    		item.append(keys.get(index));
    		item.append("\"");
    		item.append(":");
    		
    		if(types.get(index).equals("String")) {
    			item.append("\"");
    			item.append(attributes.get(index));
    			item.append("\"");
    		} else {
    			item.append(attributes.get(index));
    		}
    		
    		
    		if(index!=size()-1) {
    			item.append(",");
    		}
    		
    	}
    	result.append(item.toString());
    	
    	result.append("}");
    	return result.toString();
    }
}