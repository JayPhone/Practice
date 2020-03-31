package com.jayphone.practice.java.pattern.flyweight;

/**
 * 黑色棋子
 * Created by JayPhone on 2020/3/30
 */
public class WeiQiPiece implements Piece {
    private String type;

    public WeiQiPiece(String type) {
        this.type = type;
    }

    @Override
    public void coordinate(int x, int y) {
        String color;
        if (type.equalsIgnoreCase("white")) {
            color = "白";
        } else {
            color = "黑";
        }
        System.out.println("设置" + color + "棋子坐标为 x:" + x + " y:" + y);
    }
}
