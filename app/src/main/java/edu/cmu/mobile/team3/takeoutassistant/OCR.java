package edu.cmu.mobile.team3.takeoutassistant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wgtmac on 7/29/15.
 */
public class OCR {
    /*
    private String restaurant_name;
    private String address;
    private String phone_number;
    private HashMap<String, String> menu_list;
    */
    private List<String> lines;
    private PaperMenu menu;
    private String _path;
    public OCR() {
        lines = new ArrayList<String>();
        menu = new PaperMenu();
    }

    public OCR(String path) {
        lines = Arrays.asList(path.split("\\n+"));
        _path = path;
        for(String l : lines)
            l = l.trim();
    }

    public PaperMenu getRestaurant() {
        menu.setRestaurantName(lines.get(0));
        menu.setPhoneNumber(lines.get(1));
        menu.setAddress(lines.get(2));
        menu.setMenuList(getMenuList());
        return menu;
    }

    public String getPhoneNum() {
        if(_path.length() == 0)
            return _path;
        Pattern p1 = Pattern.compile("[0-9]{3}[-][0-9]{3}[-][0-9]{2}[-][0-9]{2} ");

        Matcher m1 = p1.matcher(_path);

        if(m1.find()) {
            return m1.group(0);
        }

        return "";
    }


    public HashMap<String, String> getMenuList() {
        if(lines.size() < 4)
            return new HashMap<String, String>();
        HashMap<String, String>result = new HashMap<String, String>();
        for(int i = 2; i < lines.size()/2; i++) {
            result.put(lines.get(i * 2), lines.get(i * 2 + 1));
        }
        return result;
    }
}
