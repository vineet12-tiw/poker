package poker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.*;
import java.util.Collections; 

public class game {
	/*static boolean royalFlush()
	{
		//boolean flush = true;
		String rank = "A,K,Q,J,10,9,8" ;
		boolean royalFlush=false;
		if(Flush() == true)
		{
			Pattern pattern = Pattern.compile("A,K,Q,J,10");
			Matcher matcher= pattern.matcher(rank);
		    boolean matchFound = matcher.find();
		    if(matchFound) {
		    	
		      //royalFlush=true;
		    }
		}
		return royalFlush;
	}*/
	/*static boolean StraightFlush()
	{
		boolean StraightFlush=false;
		if(Straight()==true && Flush() == true)
		{
			StraightFlush=true;
		}
		return StraightFlush;
	}
	static boolean fourOfaKind();
	static boolean fullHouse();
	*/
	public static boolean Flush(ArrayList<String> suit)
	{
		boolean Flush=false;
		Collections.sort(suit);
			for(int i=0;i<suit.size()-4;i++)
			{
				if(suit.get(i).equals(suit.get(i+1))==true && suit.get(i+1).equals(suit.get(i+2))==true && suit.get(i+2).equals(suit.get(i+3))==true && suit.get(i+3).equals(suit.get(i+4))==true)
				{
					Flush=true;
				}
			}
		return Flush;
	}
	static boolean Straight(ArrayList<String> rank)
	{
		boolean Straight=false;
		Collections.sort(rank);
		for(int i=0;i<rank.size()-4;i++)
		{
			if(rank.get(i).compareTo(rank.get(i+1))==1 && rank.get(i+1).compareTo(rank.get(i+2))==1 && rank.get(i+2).compareTo(rank.get(i+3))==1 && rank.get(i+3).compareTo(rank.get(i+4))==1  )
			{
				Straight=true;
			}
		}
		return Straight;
	}
	static boolean threeOfaKind(ArrayList<String> rank)
	{
		boolean threeOfaKind=false;
		Collections.sort(rank);
		for(int i=0;i<rank.size()-2;i++)
		{
			if(rank.get(i).equals(rank.get(i+1))==true && rank.get(i+1).equals(rank.get(i+2))==true)
			{
				threeOfaKind=true;
			}
		}
		return threeOfaKind;
	}
	static boolean twoPair(ArrayList<String> rank)
	{
		int flag=0;
		boolean twoPair=false;
		Collections.sort(rank);
		for(int i =0; i<rank.size()-1;i++)
		{
			if(rank.get(i).compareTo(rank.get(i+1))==0) {
				flag++;
			}
		}
		if(flag==2)
		{
			twoPair=true;
		}
		return twoPair;
	}
	static boolean pair(ArrayList<String> rank)
	{
		boolean pair=false;
		Collections.sort(rank);
		for(int i =0; i<rank.size()-1;i++)
		{
			if(rank.get(i).compareTo(rank.get(i+1))==0) {
				pair=true;
			}
		}
		return pair;
	}
	static String high(ArrayList<String> rank)
	{
		Collections.sort(rank);
		String high=rank.get(0);
		return high;
	}
	public static String drawCard(ArrayList<String> Deck){
	double drandPos=Math.floor(Math.random()*Deck.size());
	int irandPos=(int) drandPos;
	String card=Deck.get(irandPos);
	Deck.remove(irandPos);
	return card;
	}

	public static void main(String[] args) 
	{
		ArrayList<String> Deck=new ArrayList<String>();
		String[] suit={"Club","Diamond","Spade","Heart"};
		String[] rank={"A","K","Q","J","10","9","8","7","6","5","4","3","2"};
		for(int i=0;i<suit.length;i++)
		{
			for(int j=2;j<rank.length;j++)
			{
				Deck.add(suit[i] +"-" +rank[j]);
			}
		}
		System.out.print("Enter number of players : ");
		try (Scanner sc = new Scanner(System.in)) 
		{
			int players=sc.nextInt();
			ArrayList<String> player[]=new ArrayList[players];
			ArrayList<String> table=new ArrayList<String>();
			ArrayList<String> suits[]=new ArrayList[players];
			ArrayList<String> ranks[]=new ArrayList[players];
			for (int i=0;i<players;i++)
			{
				player[i]=new ArrayList<>();
				
				for(int j=0;j<2;j++)
				{
					player[i].add(drawCard(Deck));
				}
				System.out.println("player -"+(i+1)+" cards :"+player[i]);
			}
			
			for(int i=0;i<3;i++)
			{
				table.add(drawCard(Deck));
			}
			
			table.add(drawCard(Deck));
			table.add(drawCard(Deck));
			for(String i:table)
			{
				System.out.print(i+" ");
			}
			System.out.println();
			for(int i=0;i<players;i++)
			{
				player[i].addAll(table);
			}
			for(int i=0;i<players;i++)
			{
				suits[i]=new ArrayList<>();
				for(int j=0;j<player[0].size();j++)
				{
					String[] result = player[i].get(j).split("-");
					suits[i].add(result[0]);
				}
			}
			for(int i=0;i<players;i++)
			{
				ranks[i]=new ArrayList<>();
				for(int j=0;j<player[0].size();j++)
				{
					String[] result = player[i].get(j).split("-");
					ranks[i].add(result[1]);
				}
			}

			ArrayList<String> check=new ArrayList<String>();
			
			for(int i=0;i<players;i++)
			{
				if(Flush(suits[i])== true)
				{
					System.out.println((i+1)+" - Flush!");
					check.add((i+1)+"-1");
				}
				else if(Straight(suits[i])==true) 
				{
					check.add((i+1)+"-2");
					System.out.println((i+1)+"Straight!");
				}
				else if(threeOfaKind(ranks[i])==true)
				{
					check.add((i+1)+"-3");
					System.out.println((i+1)+" - three of a kind!");
				}
				else if(twoPair(ranks[i])==true)
				{
					check.add((i+1)+"-4");
					System.out.println((i+1)+" - 2Pair!");
				}
				else if(pair(ranks[i])==true)
				{
					check.add((i+1)+"-5");
					System.out.println((i+1)+" - Pair!");
				}
				else
				{
					high(ranks[i]);
					check.add((i+1)+"-6");
					System.out.println((i+1)+" - "+"high card!");
				}
			}
			boolean winnerfound= false;
			for(int j=1;j<6;j++)
			{
				if(winnerfound==false)
				{
				for(int i=0;i<players;i++)
				{
					if(check.get(i).equals((i+1)+"-"+j)==true )
					{
						System.out.println((i+1)+" is the winner");
						winnerfound=true;
					}
				}
				}
			}
		}
	}
}
