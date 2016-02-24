package hackerrank;

public class ShortestPalindrome {

	public static void main(String[] args) {
		String str = "geeks";
	    System.out.println(findMinInsertions(str, 0, str.length()-1));

	}



int min(int a, int b)
{  return a < b ? a : b; }
 
// Recursive function to find minimum number of insersions
int findMinInsertions(char str[], int l, int h)
{
    // Base Cases
    if (l > h) return Integer.MAX_VALUE;
    if (l == h) return 0;
    if (l == h - 1) return (str[l] == str[h])? 0 : 1;
 
    // Check if the first and last characters are same. On the basis of the
    // comparison result, decide which subrpoblem(s) to call
    return (str[l] == str[h])? findMinInsertions(str, l + 1, h - 1):
                               (min(findMinInsertions(str, l, h - 1),
                                   findMinInsertions(str, l + 1, h)) + 1);
}
 
// Driver program to test above functions
int main()
{
    
}
}