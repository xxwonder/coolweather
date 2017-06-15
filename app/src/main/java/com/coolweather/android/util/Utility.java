package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yjj on 2017/6/14.
 */

    public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++)
                {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                    Log.d("tag","save");
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allCitys = new JSONArray(response);
                for (int i = 0; i < allCitys.length(); i++)
                {
                    JSONObject cityObject = allCitys.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     *  解析和处理县级数据
     */
    public static boolean handleContyResponse(String response,int cityId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allConties = new JSONArray(response);
                for(int i=0;i<allConties.length();i++)
                {
                    JSONObject contyObject = allConties.getJSONObject(i);
                    County conty = new County();
                    conty.setContyName(contyObject.getString("name"));
                    conty.setWeatherId(contyObject.getString("weather_id"));
                    conty.setCityId(cityId);
                    conty.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
