#include <iostream>

//inline void swap(int & a, int & b) {
//	int temp = a;
//	a = b;
//	b = temp;
//}
//
//void bubbleSort(int * data, int N) {
//	for (int i = N - 1; i > 0; --i) {
//		for (int j = 0; j < i; ++j) {
//			if (data[j] > data[j + 1]) swap(data[j], data[j + 1]);
//		}
//	}
//}

inline void bubbleSort3(int * data) {
	int temp;
	if (data[0] > data[1]) {
		temp = data[0];
		data[0] = data[1];
		data[1] = temp;
	}
	if (data[1] > data[2]) {
		temp = data[1];
		data[1] = data[2];
		data[2] = temp;
	}
	if (data[0] > data[1]) {
		temp = data[0];
		data[0] = data[1];
		data[1] = temp;
	}
}

//void bubbleSort(int * data, int N) {
//	for (int j = 0; j < N - 1; ++j) {
//		if (data[j] > data[j + 1]) swap(data[j], data[j + 1]);
//	}
//	if (N > 2) bubbleSort(data, N - 1);
//}

inline void swap(int & a, int & b) {
	int temp = a;
	a = b;
	b = temp;
}

template < int I, int J >
class IntSwap {
public: static inline void compareAndSwap(int * data) {
	if (data[I] > data[J]) swap(data[I], data[J]);
}
};

template < int I, int J >
class IntBubbleSortLoop {
private: enum {
	go = (J <= I - 2)
};
public: static inline void loop(int * data) {
	IntSwap < J, J + 1 > ::compareAndSwap(data);
	IntBubbleSortLoop < go ? I : 0, go ? (J + 1) : 0 > ::loop(data);
}
};
template < >
class IntBubbleSortLoop < 0, 0 > {
public: static inline void loop(int *) {}
};

template < int N > 
class IntBubbleSort {
	public: static inline void sort(int * data) {
		IntBubbleSortLoop < N - 1, 0 > ::loop(data);
		IntBubbleSort < N - 1 > ::sort(data);
	}
};
template < > 
class IntBubbleSort < 1 > {
	public: static inline void sort(int * data) {}
};



//generic metafunction calls (inherits from) it self
template < int IIn, int ISum = 1 > 
struct Factorial: Factorial < IIn - 1, IIn * ISum >{};

template<int ISum>
struct Factorial<1, ISum> {
	enum { value = ISum };
};

template<int N> 
class countBits { 
	enum {	bit3 = (N & 0x08) ? 1 : 0, 
			bit2 = (N & 0x04) ? 1 : 0,
			bit1 = (N & 0x02) ? 1 : 0, 
			bit0 = (N & 0x01) ? 1 : 0 
	}; 
	public:     
		enum { nbits = bit0 + bit1 + bit2 + bit3 }; 
};

#define _USE_MATH_DEFINES 
#include <math.h>

// Compute J terms in the series expansion.  K is the loop variable.
template < int N, int I, int J, int K > class SineSeries {
public: enum {
	go = (K + 1 != J)
};
static inline float accumulate() {
	return 1 - (I * 2 * M_PI / N) * (I * 2 * M_PI / N) / (2 * K + 2) / (2 * K + 3) * 
		SineSeries < N * go, I * go, J * go, (K + 1) * go > ::accumulate();
}
};

// Specialization to terminate loop 
template < > class SineSeries < 0, 0, 0, 0 > {
public: static inline float accumulate() {
	return 1;
}
};
template < int N, int I > class Sine {
public: static inline float sin() {
	return (I * 2 * M_PI / N) * SineSeries < N, I, 10, 0 > ::accumulate();
}
};

template < size_t N, class T > class DotProduct {
public: static T eval(T * a, T * b) {
	return DotProduct < 1, T > ::eval(a, b) + DotProduct < N - 1, T > ::eval(a + 1, b + 1);
}
};
template < class T > class DotProduct < 1, T > {
public: static T eval(T * a, T * b) {
	return (*a) * (*b);
}
};

template < size_t N, class T > inline T dot(T * a, T * b) {
	return DotProduct < N, T > ::eval(a, b);
}

#include <ctime>
#include <windows.h>

int factorial(int n) {
	if ((n == 0) || (n == 1))
		return 1;
	else
		return n * factorial(n - 1);
}

int main()
{
	clock_t start_t1 = clock();
	std::cout << Factorial<16>::value;
	clock_t end_t1 = clock();

	std::cout << std::endl << float (end_t1 - start_t1) / 1000 << " sec";
	std::cout << std::endl;

	clock_t start_t2 = clock();
	std::cout << factorial(16);
	clock_t end_t2 = clock();

	std::cout << std::endl << float(end_t2 - start_t2) / 1000 << " sec";

}