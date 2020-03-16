#include <iostream>

// Task 1
struct RuntimeAdd {
	int i_;
	RuntimeAdd(int i) :i_{ i } {}
	int operator()(int i) {
		return i + i_;
	}
};

// Task 2
template<typename T, T V_Value>
struct Value {
	using Type = Value < T, V_Value >;
	static const T value = V_Value;
};
template<int I>
using Int = Value < int, I >;
template<typename T_Fixed>
struct Add {
	template<typename T>
	struct Apply : Int<T_Fixed::value + T::value> {};
};

// Task 3
template<typename T>
typename T::RetType f(T in) {
	return in();
}
long f(long i) {
	return i + 1;
}
struct MyFunctor {
	using RetType = int;
	int operator()() {
		return 42;
	}
};


int main() {
	// Task 1
	//construct a functor which adds 4 to its argument
	RuntimeAdd add4{ 4 };
	int result = add4(3);
	std::cout << result << std::endl;

	// Task 2
	//create a metafunctor which adds 4 to its argument
	using Add4 = Add<Int<4>>;
	using Result = typename Add4::template Apply<Int<3>>::Type;
	std::cout << Result::value << std::endl;

	// Task 3
	std::cout << f(MyFunctor{}) << std::endl;
	std::cout << f(3) << std::endl;
	return 0;
}