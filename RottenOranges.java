import java.io.*;
import java.util.*;
class Main {
	public static void main (String[] args) {
	    Scanner sc=new Scanner(System.in);
	    int m=0,n=0;
	    m=sc.nextInt();
	    n=sc.nextInt();
	    int grid[][]=new int[m][n];
	    
	    for(int i=0;i<m;i++){
	        for(int j=0;j<n;j++){
	            grid[i][j]=sc.nextInt();
	        }
	    }
	    Solution solution =new Solution();
	    Result res=solution.orangesRotting(grid);
	    System.out.println("Time Frames :"+res.time_frames);
	    System.out.println("Fresh Oranges :"+res.fresh_oranges);
	    System.out.println("Rotten Oranges :"+res.rotten_oranges);
	}
}

class Solution {
    public Result orangesRotting(int[][] grid) {
        int time_frames=0,fresh_oranges=0,rotten_oranges=0;
        Queue<Data>q=new LinkedList<>();
            int m=grid.length;
            int n=grid[0].length;
            for(int i=0;i<m;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(grid[i][j]==2)
                    q.add(new Data(0,i,j));
                }
            }
        int res=Integer.MIN_VALUE;
        while(!q.isEmpty())
        {
            Data d=q.poll();
            int time=d.time;
            res=Math.max(time,res);
            int i=d.i;
            int j=d.j;
            if(i-1>=0 && grid[i-1][j]==1)
            {
                grid[i-1][j]=2;
                q.add(new Data(time+1,i-1,j));
            }
            if(i+1<m && grid[i+1][j]==1)
            {
                 grid[i+1][j]=2;
                q.add(new Data(time+1,i+1,j));
            }
            if(j-1>=0 && grid[i][j-1]==1)
            {
                 grid[i][j-1]=2;
                q.add(new Data(time+1,i,j-1));
            }
            if(j+1<n && grid[i][j+1]==1 )
            {
                 grid[i][j+1]=2;
                q.add(new Data(time+1,i,j+1));
            }
        }
       
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++){
                
                if(grid[i][j]==1)
                    fresh_oranges++;
                else if(grid[i][j]==2)
                  rotten_oranges++;
            }
        }
         res=res==Integer.MIN_VALUE?0:res;
         time_frames=res;
         
         return new Result(time_frames,fresh_oranges,rotten_oranges);
    }
}
class Result{
    int time_frames;
    int fresh_oranges;
    int rotten_oranges;
    
    Result(int time_frames,int fresh_oranges,int rotten_oranges){
        this.time_frames=time_frames;
        this.fresh_oranges=fresh_oranges;
        this.rotten_oranges=rotten_oranges;
    }
}
class Data
{
    int time;
    int i;
    int j;
    Data(int time,int i,int j)
    {
        this.time=time;
        this.i=i;
        this.j=j;
    }
}