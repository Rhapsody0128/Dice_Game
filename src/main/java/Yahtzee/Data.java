package Yahtzee;

public class Data {
  public static enum DiceType{
    None,
    Aces,
    Twos,
    Threes,
    Fours,
    Fives,
    Sixes,
    ThreeOfAKind,
    FourOfAKind,
    SmallStraight,
    LargeStraight,
    Chance,
    FullHouse,
    Yahtzee
  }
  public static String DiceTypeToChinese(DiceType Type){
    switch (Type) {
      case None:
        return "放棄";
      case Aces:
        return "點數一";
      case Twos:
        return "點數二";
      case Threes:
        return "點數三";
      case Fours:
        return "點數四";
      case Fives:
        return "點數五";
      case Sixes:
        return "點數六";
      case ThreeOfAKind: 
        return "三條" ;
      case FourOfAKind:
        return "四條" ;
      case SmallStraight:
        return "小順" ;
      case LargeStraight:
        return "大順" ;
      case Chance:
        return "機會" ;
      case FullHouse:
        return "葫蘆" ;
      case Yahtzee:
        return "壓死" ;
      default:
        return "";
      }
  }
  public static String orginalText(int index){
    switch (index) {
      case 0:
        return "跳過本次回合";
      case 1:
        return "至少含有點數"+index;
      case 2:
        return "至少含有點數"+index;
      case 3:
        return "至少含有點數"+index;
      case 4:
        return "至少含有點數"+index;
      case 5:
        return "至少含有點數"+index;
      case 6:
        return "至少含有點數"+index;
      case 7:
        return "至少有三個同樣點數";
      case 8:
        return "至少有四個同樣點數";
      case 9:
        return "至少有四個點數連號";
      case 10:
        return "需五個點數連號";
      case 11:
        return "跳過本次回合";
      case 12:
        return "需二加三相同點數";
      case 13:
        return "需五個點數相同";
      default:
        return "無";
    }
  }
}
