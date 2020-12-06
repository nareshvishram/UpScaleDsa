import java.io.*;
import java.util.*;

class Node  
{ 
    int data; 
    Node left, right; 
   
    public Node(int d)  
    { 
        data = d; 
        left = right = null; 
    } 
}

class Main
{
    static Node buildTree(String str)
    {
        if(str.length() == 0 || str.equals('N'))
            return null;
        String[] s = str.split(" ");
        Node root = new Node(Integer.parseInt(s[0]));
        Queue <Node> q = new LinkedList<Node>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty() && i < s.length)
        {
           
              Node currNode = q.remove();
              String currVal = s[i];
        
              if(!currVal.equals("N")) 
              {
                  currNode.left = new Node(Integer.parseInt(currVal));
                  q.add(currNode.left);
              }
        
              i++;
              if(i >= s.length)
                  break;
              currVal = s[i];
        
              if(!currVal.equals("N")) 
              {
                  currNode.right = new Node(Integer.parseInt(currVal));
                  q.add(currNode.right);
              }
              
              i++;
        }
    
        return root;
    }
    
    public static void main(String args[]) throws IOException
    {
    
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t>0)
        {
            String s = br.readLine().trim();
            Node root = buildTree(s);
            Solution T = new Solution();
            int target = Integer.parseInt(br.readLine().trim());
            int k = Integer.parseInt(br.readLine().trim());
            ArrayList<Integer> res = new ArrayList<Integer>();
            res = T.KDistanceNodes(root,target,k);
            for(int i = 0;i<res.size();i++)
                System.out.print(res.get(i) + " ");
            System.out.println();    
            t--;
        }
    }
}
class Solution
{
    public static ArrayList<Integer> KDistanceNodes(Node root, int target , int k)
    {
        ArrayList<Integer> al=new ArrayList<>();
        
       NodeAtKDist(root,target,k,al);
       
       Collections.sort(al);
       return al;
    }
    public static int NodeAtKDist(Node root,int target,int k, ArrayList<Integer> al)
    {
        if(root==null||k<0)
        return -1;
        
        if(root.data==target)
        {
            printNodeAtKLevelFromParent(root,k,al);
            
            return 0;
        }
        
        int leftDist=NodeAtKDist(root.left,target,k,al);
        
        if(leftDist!=-1)
        {
            if(leftDist+1==k)
              al.add(root.data);
            
            else
             printNodeAtKLevelFromParent(root.right,k-2-leftDist,al);
             
            return 1+leftDist;
        }
        
        int rightDist=NodeAtKDist(root.right,target,k,al);
        
        if(rightDist!=-1)
        {
            if(rightDist+1==k)
              al.add(root.data);
            
            else
              printNodeAtKLevelFromParent(root.left,k-2-rightDist,al);
            
            return 1+rightDist;
        }
        return -1;
    }
    public static void printNodeAtKLevelFromParent(Node root,int k,ArrayList<Integer> al)
    {
        if(root==null)
        return;
        
        if(k==0)
          al.add(root.data);
          
        printNodeAtKLevelFromParent(root.left,k-1,al);
        printNodeAtKLevelFromParent(root.right,k-1,al);
    }
}