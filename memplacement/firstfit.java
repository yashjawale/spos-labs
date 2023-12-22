// package memory_placement;
import java.util.*;

public class firstfit {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the Number of Memmory Blocks: ");
        int block_no=sc.nextInt();
        int i=0,j;
        int block[]=new int[10];
        int flag[]=new int[10];
        int display[]= new int [10];
        int total=0,sum=0;
        Arrays.fill(flag,0);
        Arrays.fill(display,0);

        for(i=0;i<block_no;i++)
        {
            System.out.println("Enter the memory capacity of B"+(i+1));
            block[i]=sc.nextInt();
            total=total+block[i];
        }
        System.out.println("Enter the Number of Proesses: ");
        int process_no=sc.nextInt();
       
        int process[]=new int[10];
        for(i=0;i<process_no;i++)
        {
            System.out.println("Enter the memory required for Process"+(i+1));
            process[i]=sc.nextInt();
        }
        System.out.println("Blockm Distribution: ");
         for(i=0;i<block_no;i++)
        {
            System.out.println("B"+(i+1)+"\t"+block[i]);
            
        }
         System.out.println("\n\nProcess Distribution: ");
         for(i=0;i<process_no;i++)
        {
            System.out.println("P"+(i+1)+"\t"+process[i]);
            
        }
         for(i=0;i<process_no;i++)
         {
             for(j=0;j<block_no;j++)
             {
                if(process[i]<block[j])
                {
                  if(flag[j]==0) 
                   {
                    sum=sum+process[i];
                    display[j]=i+1;
                    flag[j]=1;

                    break;
                  }
                }
             }
         }
        //  System.out.println(sum+" "+total);
        System.out.println("\n\nProccess Alocation In blocks");
        for(i=0;i<block_no;i++){
            if(display[i]==0){
                System.out.println("B"+(i+1)+"\t"+"0");
            }
            else{
            System.out.println("B"+(i+1)+"\t"+"P"+display[i]);
            }
        }
        double efficiency = (sum*100)/total;
        System.out.println("\n \nEfficiency :"+efficiency);






        sc.close();

    }
}

//  5 100 500 200 300 600 4 212 416 112 426
// 4 212 416 112 426