/*
 * Given a set of 2D points and a number k find k points closest to the origin
 */

class Point {
	int x, y, dist;

	Point(int x, int y, Point origin) {
		this.x = x;
		this.y = y;
		this.dist = (int)(Math.pow(x - origin.x, 2) + Math.pow(y - origin.y, 2));
	}

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int compareTo(Point that) {
		return Integer.valueOf(that.dist).compareTo(dist);
	}

	public String toString() {
		return "x: " + x + " y: " + y + "  dist: " + dist;
	}
}
 
class ClosestPoints {	
	static void closestPoints(Point[] points, int left, int right, int n) {
		if (left >= right){
			return;
		}
   
		int p = partition(points, left, right);
		int c = p - left;       

		if (c > n) closestPoints(points, left, p - 1, n);
		if (c + 1 < n) closestPoints(points, p + 1, right, n - c -1);
    }
    
	static int partition(Point[] a, int left, int right){
		Point pivot = a[right];
		int p = right;
        
		while (left < right) {
			while (pivot.compareTo(a[left]) < 0 ){
				left++;
			}
			while (pivot.compareTo(a[right]) >= 0 && left < right) {
				right--;
			}

			if (left < right){
				swap(a, left, right);
			}
		}

		swap(a, left, p);
		return left;
    }

	static void swap(Point[] a, int i, int j) {
        Point t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
   
   public static void main(String[] args) {
       Point origin = new Point(0, 0);
       Point[] points = {
       		new Point(1, 1, origin), 
       		new Point(1, 3, origin),
       		new Point(-1, 1, origin),
       		new Point(-1, 3, origin),
               new Point(1, -1, origin),
               new Point(3, -1, origin),
               new Point(-1, -1, origin),
               new Point(-1, 3, origin),
               new Point(2, 2, origin),
               new Point(5, -1, origin)
       };
       
       closestPoints(points, 0, 9, 5);
       for(int i=0; i < 5; i++){
           System.out.println(points[i]);
       }
   }
}