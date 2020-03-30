package com.jayphone.practice.java.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JayPhone on 2020/3/30
 */
public class PieceFactory {
    private static Map<String, Piece> sPieceMap = new HashMap<>();

    public static Piece getPiece(String type) {
        if (sPieceMap.containsKey(type)) {
            return sPieceMap.get(type);
        } else {
            Piece piece = new WeiQiPiece(type);
            sPieceMap.put(type, piece);
            return piece;
        }
    }

    public static int getSize() {
        return sPieceMap.size();
    }
}
