import java.io.*;
import java.util.Scanner;

public class BucketSort
{	
		int [] BucketAry;
		int bucketSize;
		int minData;
		int maxData;
		int offset;
		String inFile;
		String outFile1;
		String outFile2;
	
	
		public BucketSort(String input, String output1,String output2) throws FileNotFoundException
		{
			inFile = input;
			outFile1 = output1;
			outFile2 = output2;
			findMinMax();
			bucketSize = maxData - minData + 1;
			offset = minData;
			BucketAry = new int[bucketSize];
			for(int i = 0; i < bucketSize; i++)
			{
				BucketAry[i] = 0;	
			}	
		}	
		
		int getIndex(int data)
		{
			return (data - offset);
		}
		
		void printBucketAry() throws IOException
		{
			PrintWriter output2 = new PrintWriter(new FileWriter(outFile2));
			output2.println("Debugging output:\n");
			for(int i = 0; i < 19; i++)
			{
				output2.print(i + "\t");
			}
			output2.println();
			for(int i = 0; i < 19; i++)
			{
				output2.print(BucketAry[i] + "\t");
			}
			output2.close();
			return;
		}
		
		void printSortedData() throws IOException
		{
			PrintWriter output1 = new PrintWriter(new FileWriter(outFile1));
			output1.println("Sorted list:");
			for(int i = 0; i < bucketSize; i++)
			{
				while(BucketAry[i] > 0)
				{
					output1.println(i + offset);
					BucketAry[i]--;
				}
			}
			output1.close();
			return;	
		}
		
		void fillBucket() throws FileNotFoundException
		{
			Scanner input = new Scanner(new FileReader(inFile));
			int item;
			while(input.hasNext())
			{
				item = input.nextInt();
				BucketAry[getIndex(item)]++;
			}
			input.close();
			return;
		}
		
		void findMinMax() throws FileNotFoundException
		{
			int item, firstItem;
			Scanner input = new Scanner(new FileReader(inFile));
			firstItem = input.nextInt();
			minData = firstItem;
			maxData = firstItem;
			while(input.hasNext())
			{
				item = input.nextInt();
				if(item < minData)
				{
					minData = item;
				}
				if(item > maxData)
				{
					maxData = item;	
				}  
			}
			input.close();
			return;
		}
	
	public static void main(String[] argv) throws IOException
	{
		BucketSort myBucketAry = new BucketSort(argv[0], argv[1], argv[2]);
		myBucketAry.fillBucket();
		myBucketAry.printBucketAry();
		myBucketAry.printSortedData();
	}

}
