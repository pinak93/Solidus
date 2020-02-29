import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class findpair {

	static List<Integer> values;
	static List<String> Products;
	public static void main(String[] args) throws IOException {
		/*if(args[0].length()==0 ||args[1].length()==0) {
			System.out.println("Input Error");
			System.exit(0);
		}
	
		String filename=args[0];
		int giftCost=Integer.parseInt(args[1]);*/
		FileReader FileReader;
		FileReader= new FileReader("prices.txt"); 
		values=new ArrayList();
		Products=new ArrayList();
		pre_process(FileReader);

		System.out.println(find_pair(2500));
		System.out.println(find_triplet(2000));
	}
	
	public static int search(int value) {

        if(value < values.get(0)) {
            return 0;
        }
        if(value > values.get(values.size()-1)) {
            return values.size()-1;
        }

        int lo = 0;
        int hi = values.size() - 1;

        while (lo <= hi) {
            int mid = (hi + lo) / 2;

            if (value < values.get(mid)) {
                hi = mid - 1;
            } else if (value > values.get(mid)) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        // lo == hi + 1
        return (values.get(lo) - value) < (value - values.get(hi)) ? lo : hi;
    }
	
	
	public static void pre_process(FileReader reader) throws IOException {
		int i;
		 while ((i=reader.read()) != -1) {
	      String product="";
	      while(((char)i)!=',' && i != -1) {
	    	  product=product+(char)i;
	    	  i=reader.read();
	      }
	      String price="";
	      i=reader.read();
	      while((i=reader.read())!=' '  && i != -1) {
	    	  price=price+(char)i;
	      }
	      int prices=Integer.parseInt(price);
	      values.add(prices);
	      Products.add(product);  
	  } 
	}
	
	public static String find_pair(int giftCost) {
		
		String answer="Not Found";
		int left=0;
		int right=search(giftCost);
		int diff=Integer.MAX_VALUE;
		while(left < right) {
			int difference=(values.get(left)+values.get(right));
			
			if((giftCost-difference)>=0 && (giftCost-difference)<=diff) {
				answer= Products.get(left) +""+ values.get(left)+","+ Products.get(right) +" "+ values.get(right);
				diff=Math.abs(giftCost-difference);
			}
			
			if(difference>giftCost)right--;
			else left++;
			
		}
		return answer; 
	}
	
	
	public static String find_triplet(int giftCost) {
	
		int diff=Integer.MAX_VALUE;
		String answer="Not Found";
	   
	
		for(int i=0;i<values.size()-2;i++) {
			int left=i+1,right=search(giftCost);
			
			while(left<right) {
			while(left<right && values.get(left)==values.get(left+1))left++;;
			while(left<right && values.get(right)==values.get(right-1))right--;
					
				int difference=(values.get(i)+values.get(left)+values.get(right));
			
			if((giftCost-difference)>=0 && (giftCost-difference)<=diff) {
				answer=Products.get(i) +""+ values.get(i)+","+ Products.get(left) +""+ values.get(left)+","+ Products.get(right) +" "+ values.get(right);
				diff=Math.abs(giftCost-difference);
			}
			
			if(difference>giftCost)right--;
			else left++;
			}
			
			
		}
		
		
		
		return answer; 
	}
	
	

}
