public class Utils {
    public static boolean isInArray(Object[] array, Object objectToFind) {
	if (array == null) {
	    return false;
	}

	for (int size = array.length, i = 0; i < size; ++i) {
	    if (array[i].equals(objectToFind)) {
		return true;
	    }
	}
	return false;
    }
}
