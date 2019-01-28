package com.wsinger.appclima.utils;

import java.util.HashMap;
import java.util.Map;

public class TranslationsCode {

    Map<Integer,String> condCodes = createMap();
    private static TranslationsCode mTranslationCodes;

    public  static TranslationsCode getInstance(){
        if(mTranslationCodes ==null){
            mTranslationCodes = new TranslationsCode();
        }
        return mTranslationCodes;
    }

    public String getSpanish(int condCode){
        Integer c = condCode;
        String s = condCodes.get(c);
        return s;
    }

    private HashMap<Integer,String> createMap(){
        HashMap<Integer,String>  map = new HashMap<>();
        map.put(0,"Tornado");
        map.put(1,"Tormenta tropical");
        map.put(2,"Huracan");
        map.put(3,"Tormentas severa");
        map.put(4,"Tormentas eléctrica");
        map.put(5,"Lluvia y nieve");
        map.put(6,"Lluvia y aguanieve");
        map.put(7,"Nieve y aguanieve");
        map.put(8,"Llovizna helada");
        map.put(9,"Llovizna");
        map.put(10,"Lluvia helada");
        map.put(11,"Chubascos");
        map.put(12,"Chaparrones");
        map.put(13,"Copos de nieve");
        map.put(14,"Chaparron de nieve ligera");
        map.put(15,"Ventizca con nieve");
        map.put(16,"Nieve");
        map.put(17,"Granizo");
        map.put(18,"Aguanieve");
        map.put(19,"Polvillo");
        map.put(20,"Bruma");
        map.put(21,"Neblina");
        map.put(22,"Humoso");
        map.put(23,"Tempestuoso");
        map.put(24,"Ventoso");
        map.put(25,"Frío");
        map.put(26,"Nublado");
        map.put(27,"Mayormente nublado");
        map.put(28,"Mayormente nublado");
        map.put(29,"Parcialmente nublado");
        map.put(30,"Parcialmente nublado");
        map.put(31,"Despejado");
        map.put(32,"Soleado");
        map.put(33,"Despejado (noche)");
        map.put(34,"Despejado (día)");
        map.put(35,"Lluvia y granizo");
        map.put(36,"Caluroso");
        map.put(37,"Tormentas aisladas");
        map.put(38,"Tormentas dispersa");
        map.put(39,"Tormentas dispersa");
        map.put(40,"Lluvias dispersa");
        map.put(41,"Fuertes nevadas");
        map.put(42,"Chubascos de nieve dispersos");
        map.put(43,"Fuertes nevadas");
        map.put(44,"Parcialmente nublado");
        map.put(45,"Chaparrones con truenos");
        map.put(46,"Chaparrones de nieve");
        map.put(47,"Chaparrones con truenos aislados");
        map.put(3200,"No disponible");

        return map;
    }

}
