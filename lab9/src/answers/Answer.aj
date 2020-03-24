package answers;

import figures.*;
import support.Log;

aspect Answer {
	// 1.a - works
	 declare error: get(java.io.PrintStream System.out) && within(figures..*): "illegal access to System.out";
 	
	
	// 1.b - 11 warnings
	declare warning: set(private * *) && !withincode(* set*(..)) && within(figures.*) : "bad field set";
	
	
	// 1.c - 2 errors, fixed by changing += to set method (Point.java, lines 37-38)
	declare error: set(private * *) && !(withincode(* set*(..)) || withincode(new(..)))  && within(figures.*) : "bad field set";
	
	
	
	// 2.a
	 * Throws: java.lang.IllegalArgumentException: too small
	 * Where: testMoveLine2()
	before(int newValue): set(int Point.*) && args(newValue) {
		if (newValue < 0) {
			throw new IllegalArgumentException("too small");
		}
	}
	
	
	// 2.b - works
	before(FigureElement value): call(void Group.add(FigureElement)) && args(value){
		if (value == null) {
			throw new IllegalArgumentException("it's null!");
		}
	}
	
	
	// 2.c - works
	before(FigureElement value, Group g): call(void Group.add(FigureElement)) && args(value) && target(g){
		if (value == null) {
            throw new IllegalArgumentException("it's null!");
        }
        if (value == g) {
            throw new IllegalArgumentException("it points to itself!");
        }
	}
	
	
	// 2.d - works
	void around(int val): (set(int Point._x) || set(int Point._y)) && args(val) {
        if (val < 0) val = 0;
        proceed(val);
    }
	
	
	// 2.e - works
	 void around(Point p, int dx, int dy): target(p) && call(void move(int, int)) && args(dx, dy) {
        int preX = p.getX();
        int preY = p.getY();

        proceed(p, dx, dy);

        int postX = p.getX();
        int postY = p.getY();

        if ((postX != preX + dx) || (postY != preY + dy)) {
            throw new IllegalStateException("point didn't move properly");
        }
    }
	
	
	// 2.f - works
	void around(FigureElement fe, int dx, int dy): target(fe) && call(void move(int, int)) && args(dx, dy) {
        Rectangle preBounds = new Rectangle(fe.getBounds());
        proceed(fe, dx, dy);

        preBounds.translate(dx, dy);

        if (!preBounds.equals(fe.getBounds())) {
            throw new IllegalStateException("bounds don't match move");
        }
    }
	
	
	// 3.a - works
	before(): execution(public * *(..)) && within(figures.*) {
        Log.write(thisJoinPoint);
    }
	
	
	// 3.b - works
	before(Object o):execution(public * *(..))&& !execution(public String toString(..)) && within(figures.*) && target(o) {
        Log.write(thisJoinPoint + " at " + o);
    }
	
	
	// 3.c - works
	before(): execution(void Group.add(FigureElement)) && args(Point) {
        Log.write("adding Point");
    }
	
	
	// 3.d - works
	private boolean Point.inGroup = false;

    before(Point p): execution(void Group.add(FigureElement)) && args(p) {
        if (p.inGroup) {
            throw new IllegalStateException();
        } else {
            p.inGroup = true;
        }
    }
	
	
	// 3.e - works
	private Group Point.containingGroup = null;

    before(Group g, Point p): execution(void Group.add(FigureElement)) && this(g) && args(p) {
        if (p.containingGroup != null) {
            throw new IllegalStateException(p.containingGroup.toString());
        } else {
            p.containingGroup = g;
        }
    }
	
	
	// 4.a - works
	Rectangle around(): execution(Rectangle Group.getBounds()) {
        return FigureElement.MAX_BOUNDS;
    }
	
	
	// 4.b - works
	private Rectangle Group.cache = null;

    Rectangle around(Group g): execution(Rectangle Group.getBounds()) && this(g) {
        if (g.cache == null) {
            g.cache = proceed(g);
        }
        return g.cache;
    }
	
	
	// 4.c - works
	private Rectangle Group.cache = null;

    Rectangle around(Group g): execution(Rectangle Group.getBounds()) && this(g) {
        if (g.cache == null) {
            g.cache = proceed(g);
        }
        return g.cache;
    }

    before(Group g): call(void move(int, int)) && target(g) {
        g.cache = null;
    }
	
} 