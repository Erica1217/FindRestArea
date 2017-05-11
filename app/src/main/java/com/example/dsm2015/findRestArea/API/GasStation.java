package com.example.dsm2015.findRestArea.API;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dsm2015 on 2017-04-30.
 */


/* 주유소별 가격,업체 현황 */
/* http://data.ex.co.kr/openapi/basicinfo/openApiInfoM?apiId=0312&serviceType=&keyWord=&searchDayFrom=2014.12.01&searchDayTo=2017.04.30&CATEGORY=BU&GROUP_TR= */
public class GasStation {
    static final String API_KEY = null;
    static final String TYPE = "json";
    private String mainLink = "http://data.ex.co.kr/openapi/business/";


    // 요청변수--------------------------------------------------------------------------
    String routeName;
    String direction;
    String oilCompany;
    String serviceAreaName;
    String routeCode;
    String serviceAreaCode;

    // 출력변수--------------------------------------------------------------------------
    String lpgYn; //LPG여부
    String telNo; //전화번호
    String gasolinePrice; //휘발유가격
    String diselPrice; //경유가격
    String lpgPrice; //lpg가격

    /* 생성자 */
    public GasStation() {

    }

    /* 요청변수 생성자 */
    public GasStation(String routeName, String direction, String oilCompany, String serviceAreaName, String routeCode, String serviceAreaCode) {
        this.routeName = routeName;
        this.direction = direction;
        this.oilCompany = oilCompany;
        this.serviceAreaName = serviceAreaName;
        this.routeCode = routeCode;
        this.serviceAreaCode = serviceAreaCode;
    }

    public String getGasStationLink() {
        String parameters[] = {"routeName", "direction", "oilCompany", "serviceAreaName", "routeCode", "serviceAreaCode"};
        String value[] = {routeName, direction, oilCompany, serviceAreaName, routeCode, serviceAreaCode};
        String link = mainLink + "curStateStation";
        for (int i = 0; i < 6; i++) {
            if (!value[i].isEmpty()) {
                link += parameters[i] + value[i];
            }
        }
        return link;
    }
}
