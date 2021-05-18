package entity;

/**
 * grid has three status
 * Covered - un-clicked
 * Flag - flag
 * Clicked - clicked
 *
 * 该三种状态与格子本身内容是否为雷组合到一起，其实可以组合出五种状态：
 * 未点击、插旗子正确、插旗子错误、点到雷、点到普通格子。
 * 想直接在这里列出五种状态的话，其实也行，就看你们啦~
 */
public enum GridStatus {
    Covered,Flag,Clicked,Mine,WrongFlag,Cheat;


public static GridStatus getGridStatus(String s){
    if (s.equals("Covered")){return Covered;}
    if (s.equals("Flag")){return Flag;}
    if (s.equals("Clicked")){return Clicked;}
    if (s.equals("Mine")){return Mine;}
    if (s.equals("WrongFlag")){return WrongFlag;}
    if (s.equals("Cheat")){return Cheat;}
    return null;
}


}
