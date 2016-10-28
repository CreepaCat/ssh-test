/**
 *此游戏是简易扑克牌游戏
 * 锻炼数据结构和排序的运用能力
 *
 */
package CardGame;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
import java.util.ArrayList;
import java.lang.Enum;

/**
 * Created by ghost on 2016/10/26.
 */
public class CardGame {
    public static int PLAYER_NUM=3;
    public static int CARD_NUM=6;//玩家手牌数
    public ArrayList<Card> cards=new ArrayList<Card>();
    public HashMap<String,Card> hm=new HashMap<String, Card>();
    HashMap<Enum,Card> ec;
    public ArrayList<Player>pl=new ArrayList<Player>();

    public static void main(String[] args){
        CardGame that=new CardGame();

        //创建扑克牌(52张，不要大小王)
        that.CreateCards();
        System.out.println("========扑克牌创建成功！=========");
        Card c0;
        for(int k=0;k<that.cards.size();k++){
            c0=that.cards.get(k);
            System.out.print(c0.getFlo().name()+c0.getNum().name());
            System.out.print("["+c0.getFlower()+c0.getNumber()+"]");
            System.out.print("\n");

        }
        //比较
        that.CompareCards(that.cards.get(0),that.cards.get(1));
        System.out.println("比较结果：" + that.CompareCards(that.cards.get(0), that.cards.get(1)));
        System.out.println("\n============开始洗牌===========");
        //洗牌
          that.WashCards(that.cards);
        System.out.println("============洗牌结束===========");
        for(int k=0;k<that.cards.size();k++){
            c0=that.cards.get(k);

            System.out.print("["+c0.getFlower()+c0.getNumber()+"]");
        }
        System.out.print("\n");

        //创建玩家
        System.out.println("=========创建玩家...===========\n");

            that.CreatePlayers();



       // CreatePlayers();
        //发牌(规则：一人一张，顺序发牌)
          that.SendCards(that.pl);
        //比较得出获胜玩家
           that.GameResult(that.pl);
    }

    public void CreateCards() {
        //HashMap card = new HashMap();
        //card.

        //EnumSet<NumberEnum> cardSet=EnumSet.allOf(NumberEnum.class);

        //NumberEnum number=null;
        //EnumMap<String,Enum>
        //Enumeration<>


        //EnumSet<NumberEnum>.
        for (int i = 0; i < 4; i++) {
            FlowerEnum flower = null;
            //Enumeration<NumberEnum> cardSet;

            NumberEnum num=null;

            String fc=null;
            if(i==0){
               fc="方块";
                flower=FlowerEnum.FQ;



            }
            if(i==1){
                fc="梅花";
               flower=FlowerEnum.MH;
            }
            if(i==2){
                fc="红桃";
                flower=FlowerEnum.RE;
            }
            if(i==3){
                fc="黑桃";
                 flower=FlowerEnum.BL;

            }
            for (int j = 0; j < 13; j++) {
                String number=null;
                if(j<=8){
                    //整数解析为字符串
                    number=String.valueOf(j+2);
                    num=NumberEnum.N;



                   // num=Nu
                   // num=
                }else if(j==9){
                    number="J";
                    num=NumberEnum.J;

                }
                if(j==10){
                    number="Q";
                    num=NumberEnum.Q;
                }
                if(j==11){
                    number="K";
                    num=NumberEnum.K;

                }
                if(j==12){
                    number="A";
                    num=NumberEnum.A;

                }
                Card c=new Card();
                c.setFlower(fc);
                c.setNumber(number);

                c.setFlo(flower);
                c.setNum(num);

                cards.add(c);

            }
        }
    }
    public void WashCards(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            String key = "";
            key = String.valueOf(i);
            //添加键值对
            hm.put(key, cards.get(i));
        }
        //Random ran = new Random();
        for (int r = 0; r < 100; r++) {


            Collections.shuffle(cards);
        }
    }
    public void CreatePlayers(){
        Scanner input=new Scanner(System.in);
        back1:
        for(int n=0;n<PLAYER_NUM;n++) {
            int pId=n+1;
            Player p = new Player();
            System.out.println("请输入玩家"+pId+"ID:");
            String ID = input.next();

            try {
                int id = Integer.parseInt(ID);
                p.setID(id);
            } catch (Exception e) {
                //  throw Exception("请输入");
                //e.printStackTrace();
                System.out.println("请输入整数型ID");
                n--;
                continue back1;
            }

            System.out.println("请输入玩家"+pId+"名字:");
            String name = input.next();
            p.setName(name);
            pl.add(p);
        }

    }
    public void SendCards(ArrayList<Player> pl){
        //pl.size();

        System.out.println("=========开始拿牌==========");



        for(int n=0;n<PLAYER_NUM;n++){
               Player p=pl.get(n);
            ArrayList<Card> pc = new ArrayList<Card>();
            System.out.print("玩家"+p.getName()+"手牌为:");
            for(int k=0;k<CARD_NUM;k++) {
                Card hc=cards.get(n + k*PLAYER_NUM);
                  pc.add(hc);
               // p.setCards(pc);

                System.out.print(hc.getFlower()+hc.getNumber()+"|");

                //pl.get()
            }

            pl.get(n).setCards(pc);
            System.out.print("\n");


        }
        System.out.println("=========拿牌结束==========");

    }
    public void GameResult( ArrayList<Player> pl) {
        ArrayList<Card> Max = new ArrayList<Card>();
        for (int i = 0; i < pl.size(); i++) {
            Card max;
            Player p = pl.get(i);
            //降序排序
            Collections.sort(p.getCards(), new SortCards());
            max = p.getCards().get(0);
            Max.add(max);
            System.out.println("玩家" + p.getName() + "最大牌为:[" + Max.get(i).getFlower() + Max.get(i).getNumber() + "]");

        }
        //Card winCar;
        //winCard=CompareCards(Max.get(0),Max.get(1))?Max.get(0):Max.get(1);
        ArrayList<Card> Result = new ArrayList<Card>();
        for (int i = 0; i <Max.size() ; i++) {
            Result.add(Max.get(i));
        }
        //Result.indexOf()
        Collections.sort(Max, new SortCards());
        Card BigMax = Max.get(0);
        int index = Result.indexOf(BigMax);

        Player winner = pl.get(index);

        System.out.println("玩家" + winner.getName() + "获胜");


        }
       // System.out.println("玩家2最大牌为"+Max.get(1));
       /* if(winCard.equals(Max.get(0))){
            System.out.println("玩家1获胜");
        }else {
            System.out.println("玩家2获胜");
        }*/



    public boolean CompareCards(Card a,Card b){
        boolean flag=false;//a>b?
        //三目比较
        int numKey;
        int floKey;
        numKey=a.getNum().compareTo(b.getNum());
        floKey=a.getFlo().compareTo(b.getFlo());
        if(numKey>0){
            numKey=1;
        }else if(numKey<0){
            numKey=-1;
        }else numKey=0;
        if(floKey>0){
            floKey=1;
        }else if(floKey<0){
            floKey=-1;
        }else floKey=0;
        switch (numKey) {
            case -1://a的数字枚举在b之前
                flag=false;break;
            case 1:
                flag=true;break;
            case 0: //a的数字枚举为N或其它与b相等
                switch (a.getNum()){
                    case N://为N
                        int na=Integer.parseInt(a.getNumber());
                        int nb=Integer.parseInt(b.getNumber());
                        if(na>nb){
                            flag=true;break;
                        }
                        if(na<nb){
                            flag=false;break;

                        }
                        if(na==nb){
                            switch (floKey){
                                case -1://a的花色枚举在b之前
                                    flag=false;break;
                                case 1:
                                    flag=true;break;

                            }
                        }
                    default:
                        switch (floKey){
                            case -1://a的花色枚举在b之前
                                flag=false;break;
                            case 1:
                                flag=true;break;
                            // default:
                        }

                }

        }

        return flag;

    }



}
    enum FlowerEnum{

             FQ,
             MH,
             RE,
             BL



}
enum NumberEnum{
            N,J,Q,K,A

}
class Card implements Comparable<Card>{
    public String getFlower() {
        return flower;

    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    private String flower;


    public FlowerEnum getFlo() {
        return flo;
    }

    public void setFlo(FlowerEnum flo) {
        this.flo = flo;
    }

    private FlowerEnum flo;

    public NumberEnum getNum() {
        return num;
    }

    public void setNum(NumberEnum num) {
        this.num = num;
    }

    private NumberEnum num;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String number;

    @Override
    public int compareTo(Card o) {
        CardGame cg=new CardGame();

        if(cg.CompareCards(this, o)){
            return -1;
        }else
        return 1;
    }
}
class Player{
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    private ArrayList<Card> cards;

}
     class SortCards implements Comparator{

         @Override
         public int compare(Object o1, Object o2) {
             //CardGame cg=new CardGame();
             Card c1=(Card)o1;
             Card c2=(Card)o2;
            return c1.compareTo(c2);
         }
     }
